package com.sinthoras.visualprospecting.integration.model.locations;

import com.impact.common.oregeneration.OreVein;

public class ImpactOreLayer1ChunkLocation extends AbstractImpactOreChunkLocation {

    public ImpactOreLayer1ChunkLocation(int chunkX, int chunkZ, int dimensionId, OreVein vein, int oreAmount,
            int minAmountInField, int maxAmountInField) {
        super(chunkX, chunkZ, dimensionId, vein, oreAmount, minAmountInField, maxAmountInField);
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
