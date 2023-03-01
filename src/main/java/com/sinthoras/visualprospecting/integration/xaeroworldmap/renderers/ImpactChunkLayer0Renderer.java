package com.sinthoras.visualprospecting.integration.xaeroworldmap.renderers;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreChunkLayer0Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer0ChunkLocation;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps.ImpactOreChunkRenderStep;

public class ImpactChunkLayer0Renderer extends LayerRenderer {

    public static ImpactChunkLayer0Renderer instance = new ImpactChunkLayer0Renderer();

    public ImpactChunkLayer0Renderer() {
        super(ImpactOreChunkLayer0Manager.instance);
    }

    @Override
    protected List<ImpactOreChunkRenderStep> generateRenderSteps(List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreChunkRenderStep> renderSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer0ChunkLocation) element)
                .forEach(location -> renderSteps.add(new ImpactOreChunkRenderStep(location)));
        return renderSteps;
    }
}
