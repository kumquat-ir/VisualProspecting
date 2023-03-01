package com.sinthoras.visualprospecting.integration.journeymap.waypoints;

import java.awt.*;

import com.sinthoras.visualprospecting.integration.model.SupportedMods;
import com.sinthoras.visualprospecting.integration.model.layers.WaypointProviderManager;
import com.sinthoras.visualprospecting.integration.model.waypoints.Waypoint;

public class WaypointManager extends com.sinthoras.visualprospecting.integration.model.waypoints.WaypointManager {

    private journeymap.client.model.Waypoint jmWaypoint;

    public WaypointManager(WaypointProviderManager layerManager) {
        super(layerManager, SupportedMods.JourneyMap);
    }

    @Override
    public void clearActiveWaypoint() {
        jmWaypoint = null;
    }

    public boolean hasWaypoint() {
        return jmWaypoint != null;
    }

    public journeymap.client.model.Waypoint getJmWaypoint() {
        return jmWaypoint;
    }

    @Override
    public void updateActiveWaypoint(Waypoint waypoint) {
        if (!hasWaypoint() || waypoint.blockX != jmWaypoint.getX()
                || waypoint.blockY != jmWaypoint.getY()
                || waypoint.blockZ != jmWaypoint.getZ()
                || !jmWaypoint.getDimensions().contains(waypoint.dimensionId)) {
            jmWaypoint = new journeymap.client.model.Waypoint(
                    waypoint.label,
                    waypoint.blockX,
                    waypoint.blockY,
                    waypoint.blockZ,
                    new Color(waypoint.color),
                    journeymap.client.model.Waypoint.Type.Normal,
                    waypoint.dimensionId);
        }
    }
}
