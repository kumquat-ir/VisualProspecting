package com.sinthoras.visualprospecting.integration.model.layers;

import com.sinthoras.visualprospecting.database.ImpactOrePosition;
import com.sinthoras.visualprospecting.integration.model.buttons.ImpactLayer0ButtonManager;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreChunkLocation;
import com.sinthoras.visualprospecting.integration.model.locations.ImpactOreLayer0ChunkLocation;

public class ImpactOreChunkLayer0Manager extends AbstractImpactOreChunkLayerManager {

    public static final ImpactOreChunkLayer0Manager instance = new ImpactOreChunkLayer0Manager();

    public ImpactOreChunkLayer0Manager() {
        super(ImpactLayer0ButtonManager.instance);
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    protected AbstractImpactOreChunkLocation getLocation(int chunkX, int chunkZ, int offsetChunkX, int offsetChunkZ,
            int playerDimensionId, ImpactOrePosition impactOre, int minAmountInField, int maxAmountInField) {
        return new ImpactOreLayer0ChunkLocation(
                chunkX + offsetChunkX,
                chunkZ + offsetChunkZ,
                playerDimensionId,
                impactOre.veinLayer0,
                impactOre.chunksLayer0[offsetChunkX][offsetChunkZ],
                minAmountInField,
                maxAmountInField);
    }
}
