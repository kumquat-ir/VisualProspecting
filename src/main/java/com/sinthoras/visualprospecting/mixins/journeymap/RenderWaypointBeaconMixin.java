package com.sinthoras.visualprospecting.mixins.journeymap;

import com.sinthoras.visualprospecting.gui.journeymap.MapState;
import com.sinthoras.visualprospecting.gui.journeymap.layers.InformationLayer;
import com.sinthoras.visualprospecting.gui.journeymap.layers.WaypointProviderLayer;
import journeymap.client.model.Waypoint;
import journeymap.client.render.ingame.RenderWaypointBeacon;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderWaypointBeacon.class)
public class RenderWaypointBeaconMixin {

    @Shadow(remap = false)
    static Minecraft mc;

    @Shadow(remap = false)
    static void doRender(Waypoint waypoint) {
        throw new IllegalStateException("Mixin failed to shadow doRender()");
    }

    @Inject(method = "renderAll",
            at = @At(value = "INVOKE",
                    target = "Ljourneymap/client/waypoint/WaypointStore;instance()Ljourneymap/client/waypoint/WaypointStore;"),
            remap = false,
            require = 1)
    private static void onRenderAll(CallbackInfo callbackInfo) {
        for(InformationLayer layer : MapState.instance.layers) {
            if(layer instanceof WaypointProviderLayer) {
                final Waypoint waypoint = ((WaypointProviderLayer) layer).getActiveWaypoint();
                if(waypoint != null && waypoint.getDimensions().contains(mc.thePlayer.dimension)) {
                    doRender(waypoint);
                }
            }
        }
    }

}
