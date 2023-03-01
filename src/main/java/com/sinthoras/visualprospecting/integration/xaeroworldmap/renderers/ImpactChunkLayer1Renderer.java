package com.sinthoras.visualprospecting.integration.xaeroworldmap.renderers;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreChunkLayer1Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer1ChunkLocation;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps.ImpactOreChunkRenderStep;

public class ImpactChunkLayer1Renderer extends LayerRenderer {

    public static ImpactChunkLayer1Renderer instance = new ImpactChunkLayer1Renderer();

    public ImpactChunkLayer1Renderer() {
        super(ImpactOreChunkLayer1Manager.instance);
    }

    @Override
    protected List<ImpactOreChunkRenderStep> generateRenderSteps(List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreChunkRenderStep> renderSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer1ChunkLocation) element)
                .forEach(location -> renderSteps.add(new ImpactOreChunkRenderStep(location)));
        return renderSteps;
    }
}
