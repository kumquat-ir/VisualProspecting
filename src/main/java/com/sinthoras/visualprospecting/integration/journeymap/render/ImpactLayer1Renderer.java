package com.sinthoras.visualprospecting.integration.journeymap.render;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.journeymap.drawsteps.ImpactOreDrawStep;
import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreLayer1Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer1Location;

public class ImpactLayer1Renderer extends LayerRenderer {

    public static ImpactLayer1Renderer instance = new ImpactLayer1Renderer();

    public ImpactLayer1Renderer() {
        super(ImpactOreLayer1Manager.instance);
    }

    @Override
    protected List<ImpactOreDrawStep> mapLocationProviderToDrawStep(List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreDrawStep> drawSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer1Location) element)
                .forEach(location -> drawSteps.add(new ImpactOreDrawStep(location)));
        return drawSteps;
    }
}
