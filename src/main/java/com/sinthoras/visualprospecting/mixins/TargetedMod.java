package com.sinthoras.visualprospecting.mixins;

public enum TargetedMod {

    //
    // IMPORTANT: Do not make any references to any mod from this file. This file is loaded quite early on and if
    // you refer to other mods you load them as well. The consequence is: You can't inject any previously loaded
    // classes!
    // Exception: Tags.java, as long as it is used for Strings only!
    //

    // Replace with your injected mods here, but always keep VANILLA:
    VANILLA("Minecraft", null),
    GREGTECH("GregTech", null, "gregtech"),
    JOURNEYMAP("JourneyMap", null, "journeymap"),
    XAEROWORLDMAP("Xaero's World Map", "xaero.map.core.XaeroWorldMapPlugin", "XaeroWorldMap"),
    XAEROMINIMAP("Xaero's Minimap", "xaero.common.core.XaeroMinimapPlugin", "XaeroMinimap"),
    TCNODETRACKER("TCNodeTracker", null, "tcnodetracker"),
    BARTWORKS("Bartworks", null, "bartworks"),
    GALACTICGREG("GalacticGreg", null, "galacticgreg");

    public final String modName;
    /** Class that implements the IFMLLoadingPlugin interface */
    public final String coreModClass;
    /** The "modid" in the @Mod annotation */
    public final String modId;

    TargetedMod(String modName, String coreModClass) {
        this(modName, coreModClass, null);
    }

    TargetedMod(String modName, String coreModClass, String modId) {
        this.modName = modName;
        this.coreModClass = coreModClass;
        this.modId = modId;
    }

    @Override
    public String toString() {
        return "TargetedMod{modName='" + modName + "', coreModClass='" + coreModClass + "', modId='" + modId + "'}";
    }
}
