package com.sinthoras.visualprospecting.integration.model.locations;

import com.impact.common.oregeneration.OreVein;
import com.sinthoras.visualprospecting.database.ImpactOrePosition;

public abstract class AbstractImpactOreLocation implements ILocationProvider {

    private final ImpactOrePosition impactOrePosition;
    private final int minProduction;
    private final int maxProduction;

    public AbstractImpactOreLocation(ImpactOrePosition impactOrePosition) {
        this.impactOrePosition = impactOrePosition;
        minProduction = impactOrePosition.getMinProduction(getLayer());
        maxProduction = impactOrePosition.getMaxProduction(getLayer());
    }

    public abstract int getLayer();

    @Override
    public int getDimensionId() {
        return impactOrePosition.dimensionId;
    }

    @Override
    public double getBlockX() {
        return impactOrePosition.getBlockX() + 0.5;
    }

    @Override
    public double getBlockZ() {
        return impactOrePosition.getBlockZ() + 0.5;
    }

    public int getMinProduction() {
        return minProduction;
    }

    public int getMaxProduction() {
        return maxProduction;
    }

    public OreVein getVein() {
        if (getLayer() == 0) {
            return impactOrePosition.veinLayer0;
        } else if (getLayer() == 1) {
            return impactOrePosition.veinLayer1;
        }
        return null;
    }
}
