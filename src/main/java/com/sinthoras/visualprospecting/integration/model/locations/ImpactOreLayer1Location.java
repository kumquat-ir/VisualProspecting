package com.sinthoras.visualprospecting.integration.model.locations;

import com.sinthoras.visualprospecting.database.ImpactOrePosition;

public class ImpactOreLayer1Location extends AbstractImpactOreLocation {

    public ImpactOreLayer1Location(ImpactOrePosition impactOrePosition) {
        super(impactOrePosition);
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
