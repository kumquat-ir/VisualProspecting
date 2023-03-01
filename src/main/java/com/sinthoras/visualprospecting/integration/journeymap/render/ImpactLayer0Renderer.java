package com.sinthoras.visualprospecting.integration.journeymap.render;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.journeymap.drawsteps.ImpactOreDrawStep;
import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreLayer0Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer0Location;

public class ImpactLayer0Renderer extends LayerRenderer {

    public static ImpactLayer0Renderer instance = new ImpactLayer0Renderer();

    public ImpactLayer0Renderer() {
        super(ImpactOreLayer0Manager.instance);
    }

    @Override
    protected List<ImpactOreDrawStep> mapLocationProviderToDrawStep(List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreDrawStep> drawSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer0Location) element)
                .forEach(location -> drawSteps.add(new ImpactOreDrawStep(location)));
        return drawSteps;
    }
}
