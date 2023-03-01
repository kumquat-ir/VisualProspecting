package com.sinthoras.visualprospecting.integration.model.locations;

import com.impact.common.oregeneration.OreVein;

public class ImpactOreLayer0ChunkLocation extends AbstractImpactOreChunkLocation {

    public ImpactOreLayer0ChunkLocation(int chunkX, int chunkZ, int dimensionId, OreVein vein, int oreAmount,
            int minAmountInField, int maxAmountInField) {
        super(chunkX, chunkZ, dimensionId, vein, oreAmount, minAmountInField, maxAmountInField);
    }

    @Override
    public int getLayer() {
        return 0;
    }
}
