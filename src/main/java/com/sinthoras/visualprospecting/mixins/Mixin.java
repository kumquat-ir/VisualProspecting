package com.sinthoras.visualprospecting.mixins;

import static com.sinthoras.visualprospecting.mixins.TargetedMod.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cpw.mods.fml.relauncher.FMLLaunchHandler;

public enum Mixin {

    //
    // IMPORTANT: Do not make any references to any mod from this file. This file is loaded quite early on and if
    // you refer to other mods you load them as well. The consequence is: You can't inject any previously loaded
    // classes!
    // Exception: Tags.java, as long as it is used for Strings only!
    //

    WorldGenContainerMixin("bartworks.WorldGenContainerMixin", Phase.LATE, BARTWORKS),

    GT_Worldgenerator_SpaceMixin("galacticgreg.GT_Worldgenerator_SpaceMixin", Phase.LATE, GALACTICGREG),

    GT_Block_Ores_AbstractMixin("gregtech.GT_Block_Ores_AbstractMixin", Phase.LATE, GREGTECH),
    GT_MetaTileEntity_AdvSeismicProspectorMixin("gregtech.GT_MetaTileEntity_AdvSeismicProspectorMixin", Phase.LATE,
            GREGTECH),
    GT_MetaTileEntity_ScannerMixin("gregtech.GT_MetaTileEntity_ScannerMixin", Phase.LATE, GREGTECH),
    GT_WorldGenContainerMixin("gregtech.WorldGenContainerMixin", Phase.LATE, GREGTECH),

    FullscreenMixin("journeymap.FullscreenMixin", Side.CLIENT, Phase.LATE, JOURNEYMAP),
    FullscreenActionsMixin("journeymap.FullscreenActionsMixin", Side.CLIENT, Phase.LATE, JOURNEYMAP),
    MiniMapMixin("journeymap.MiniMapMixin", Side.CLIENT, Phase.LATE, JOURNEYMAP),
    RenderWaypointBeaconMixin("journeymap.RenderWaypointBeaconMixin", Side.CLIENT, Phase.LATE, JOURNEYMAP),
    WaypointManagerMixin("journeymap.WaypointManagerMixin", Side.CLIENT, Phase.LATE, JOURNEYMAP),

    GuiMainMixin("tcnodetracker.GuiMainMixin", Side.CLIENT, Phase.LATE, TCNODETRACKER),

    GuiMapMixin("xaerosworldmap.GuiMapMixin", Side.CLIENT, Phase.LATE, XAEROWORLDMAP),
    WaypointsIngameRendererMixin("xaerosminimap.WaypointsIngameRendererMixin", Side.CLIENT, Phase.LATE, XAEROMINIMAP),
    MinimapRendererMixin("xaerosminimap.MinimapRendererMixin", Side.CLIENT, Phase.LATE, XAEROMINIMAP, XAEROWORLDMAP),
    // used to enable the stencil buffer for on-minimap rendering
    ForgeHooksClientMixin("minecraft.ForgeHooksClientMixin", Side.CLIENT, Phase.EARLY, XAEROMINIMAP, XAEROWORLDMAP),

    ItemEditableBookMixin("minecraft.ItemEditableBookMixin", Phase.EARLY, VANILLA),

    // GTNH's version of DetravScanner has native VP integration, IMPACT's does not
    ProspectingPacketMixin("detravscanner.ProspectingPacketMixin", Phase.LATE, DETRAV_SCANNER, IMPACT_CORE),
    Behaviour_OreProbeMixin("impactcore.Behaviour_OreProbeMixin", Phase.LATE, IMPACT_CORE);

    public final String mixinClass;
    public final List<TargetedMod> targetedMods;
    private final Side side;
    private final Phase phase;

    Mixin(String mixinClass, Side side, Phase phase, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.phase = phase;
        this.targetedMods = Arrays.asList(targetedMods);
        this.side = side;
    }

    Mixin(String mixinClass, Phase phase, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.phase = phase;
        this.targetedMods = Arrays.asList(targetedMods);
        this.side = Side.BOTH;
    }

    public boolean shouldLoad(Set<String> loadedMods, Set<String> loadedCoreMods, Phase phase) {
        return (side == Side.BOTH || side == Side.SERVER && FMLLaunchHandler.side().isServer()
                || side == Side.CLIENT && FMLLaunchHandler.side().isClient())
                && this.phase == phase
                && (targetedMods.contains(VANILLA)
                        || (phase == Phase.LATE && loadedMods
                                .containsAll(targetedMods.stream().map((a) -> a.modId).collect(Collectors.toSet())))
                        || (phase == Phase.EARLY && loadedCoreMods.containsAll(
                                targetedMods.stream().map((a) -> a.coreModClass).collect(Collectors.toSet()))));
    }

    enum Side {
        BOTH,
        CLIENT,
        SERVER
    }

    public enum Phase {
        EARLY,
        LATE
    }
}
