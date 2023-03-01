package com.sinthoras.visualprospecting.integration.xaeroworldmap.renderers;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreLayer1Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer1Location;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps.ImpactOreRenderStep;

public class ImpactLayer1Renderer extends LayerRenderer {

    public static ImpactLayer1Renderer instance = new ImpactLayer1Renderer();

    public ImpactLayer1Renderer() {
        super(ImpactOreLayer1Manager.instance);
    }

    @Override
    protected List<ImpactOreRenderStep> generateRenderSteps(List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreRenderStep> renderSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer1Location) element)
                .forEach(location -> renderSteps.add(new ImpactOreRenderStep(location)));
        return renderSteps;
    }
}
