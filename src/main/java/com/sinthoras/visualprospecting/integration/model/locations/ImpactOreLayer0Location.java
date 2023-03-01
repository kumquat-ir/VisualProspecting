package com.sinthoras.visualprospecting.integration.model.locations;

import com.sinthoras.visualprospecting.database.ImpactOrePosition;

public class ImpactOreLayer0Location extends AbstractImpactOreLocation {

    public ImpactOreLayer0Location(ImpactOrePosition impactOrePosition) {
        super(impactOrePosition);
    }

    @Override
    public int getLayer() {
        return 0;
    }
}
