package com.sinthoras.visualprospecting.integration.journeymap.render;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.journeymap.drawsteps.ImpactOreChunkDrawStep;
import com.sinthoras.visualprospecting.integration.model.layers.ImpactOreChunkLayer1Manager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer1ChunkLocation;

public class ImpactChunkLayer1Renderer extends LayerRenderer {

    public static ImpactChunkLayer1Renderer instance = new ImpactChunkLayer1Renderer();

    public ImpactChunkLayer1Renderer() {
        super(ImpactOreChunkLayer1Manager.instance);
    }

    @Override
    protected List<ImpactOreChunkDrawStep> mapLocationProviderToDrawStep(
            List<? extends ILocationProvider> visibleElements) {
        final List<ImpactOreChunkDrawStep> drawSteps = new ArrayList<>();
        visibleElements.stream().map(element -> (ImpactOreLayer1ChunkLocation) element)
                .forEach(location -> drawSteps.add(new ImpactOreChunkDrawStep(location)));
        return drawSteps;
    }
}
