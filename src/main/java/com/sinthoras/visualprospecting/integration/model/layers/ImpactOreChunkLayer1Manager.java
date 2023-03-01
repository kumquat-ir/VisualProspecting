package com.sinthoras.visualprospecting.integration.model.layers;

import com.sinthoras.visualprospecting.database.ImpactOrePosition;
import com.sinthoras.visualprospecting.integration.model.buttons.ImpactLayer1ButtonManager;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreChunkLocation;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer1ChunkLocation;

public class ImpactOreChunkLayer1Manager extends AbstractImpactOreChunkLayerManager {

    public static final ImpactOreChunkLayer1Manager instance = new ImpactOreChunkLayer1Manager();

    public ImpactOreChunkLayer1Manager() {
        super(ImpactLayer1ButtonManager.instance);
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    protected AbstractImpactOreChunkLocation getLocation(int chunkX, int chunkZ, int offsetChunkX, int offsetChunkZ,
            int playerDimensionId, ImpactOrePosition impactOre, int minAmountInField, int maxAmountInField) {
        return new ImpactOreLayer1ChunkLocation(
                chunkX + offsetChunkX,
                chunkZ + offsetChunkZ,
                playerDimensionId,
                impactOre.veinLayer1,
                impactOre.chunksLayer1[offsetChunkX][offsetChunkZ],
                minAmountInField,
                maxAmountInField);
    }
}
