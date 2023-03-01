package com.sinthoras.visualprospecting.integration.model.layers;

import com.sinthoras.visualprospecting.database.ImpactOrePosition;
import com.sinthoras.visualprospecting.integration.model.buttons.ImpactLayer0ButtonManager;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreLocation;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer0Location;

public class ImpactOreLayer0Manager extends AbstractImpactOreLayerManager {

    public static final ImpactOreLayer0Manager instance = new ImpactOreLayer0Manager();

    public ImpactOreLayer0Manager() {
        super(ImpactLayer0ButtonManager.instance);
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    protected AbstractImpactOreLocation createLocation(ImpactOrePosition impactOre) {
        return new ImpactOreLayer0Location(impactOre);
    }
}
