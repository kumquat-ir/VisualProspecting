package com.sinthoras.visualprospecting.integration.journeymap.render;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.journeymap.drawsteps.ImpactOreChunkDrawStep;
import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreChunkLayer0Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer0ChunkLocation;

public class ImpactChunkLayer0Renderer extends LayerRenderer {

    public static ImpactChunkLayer0Renderer instance = new ImpactChunkLayer0Renderer();

    public ImpactChunkLayer0Renderer() {
        super(ImpactOreChunkLayer0Manager.instance);
    }

    @Override
    protected List<ImpactOreChunkDrawStep> mapLocationProviderToDrawStep(
            List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreChunkDrawStep> drawSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer0ChunkLocation) element)
                .forEach(location -> drawSteps.add(new ImpactOreChunkDrawStep(location)));
        return drawSteps;
    }
}
