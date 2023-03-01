package com.sinthoras.visualprospecting.integration.model.layers;

import com.sinthoras.visualprospecting.database.ImpactOrePosition;
import com.sinthoras.visualprospecting.integration.model.buttons.ImpactLayer1ButtonManager;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreLocation;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer1Location;

public class ImpactOreLayer1Manager extends AbstractImpactOreLayerManager {

    public static final ImpactOreLayer1Manager instance = new ImpactOreLayer1Manager();

    public ImpactOreLayer1Manager() {
        super(ImpactLayer1ButtonManager.instance);
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    protected AbstractImpactOreLocation createLocation(ImpactOrePosition impactOre) {
        return new ImpactOreLayer1Location(impactOre);
    }
}
