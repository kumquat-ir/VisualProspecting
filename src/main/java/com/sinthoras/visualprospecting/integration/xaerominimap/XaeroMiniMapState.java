package com.sinthoras.visualprospecting.integration.xaerominimap;

import static com.sinthoras.visualprospecting.Utils.isTCNodeTrackerInstalled;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.xaerominimap.waypoints.OreVeinWaypointManager;
import com.sinthoras.visualprospecting.integration.xaerominimap.waypoints.ThaumcraftNodeWaypointManager;
import com.sinthoras.visualprospecting.integration.xaerominimap.waypoints.WaypointManager;

public class XaeroMiniMapState {

    public static XaeroMiniMapState instance = new XaeroMiniMapState();

    public final List<WaypointManager> waypointManagers = new ArrayList<>();

    public XaeroMiniMapState() {
        waypointManagers.add(OreVeinWaypointManager.instance);

        if (isTCNodeTrackerInstalled()) {
            waypointManagers.add(ThaumcraftNodeWaypointManager.instance);
        }
    }
}
