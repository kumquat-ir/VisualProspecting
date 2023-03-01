package com.sinthoras.visualprospecting.integration.xaeroworldmap.renderers;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreLayer0Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer0Location;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps.ImpactOreRenderStep;

public class ImpactLayer0Renderer extends LayerRenderer {

    public static ImpactLayer0Renderer instance = new ImpactLayer0Renderer();

    public ImpactLayer0Renderer() {
        super(ImpactOreLayer0Manager.instance);
    }

    @Override
    protected List<ImpactOreRenderStep> generateRenderSteps(List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreRenderStep> renderSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer0Location) element)
                .forEach(location -> renderSteps.add(new ImpactOreRenderStep(location)));
        return renderSteps;
    }
}
