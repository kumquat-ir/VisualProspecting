package com.sinthoras.visualprospecting.mixins.late.journeymap;

import journeymap.client.ui.waypoint.WaypointManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.sinthoras.visualprospecting.integration.model.MapState;
import com.sinthoras.visualprospecting.integration.model.layers.LayerManager;
import com.sinthoras.visualprospecting.integration.model.layers.WaypointProviderManager;

@Mixin(WaypointManager.class)
public class WaypointManagerMixin {

    @Inject(
            method = "toggleItems",
            at = @At("HEAD"),
            remap = false,
            require = 1,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onToggleAllWaypoints(boolean enable, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (enable == false) {
            for (LayerManager layer : MapState.instance.layers) {
                if (layer instanceof WaypointProviderManager) {
                    ((WaypointProviderManager) layer).clearActiveWaypoint();
                }
            }
        }
    }
}
