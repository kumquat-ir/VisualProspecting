package com.sinthoras.visualprospecting.integration.model.layers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.database.ClientCache;
import com.sinthoras.visualprospecting.database.ImpactOrePosition;
import com.sinthoras.visualprospecting.integration.model.buttons.ButtonManager;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreLocation;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;

public abstract class AbstractImpactOreLayerManager extends LayerManager {

    private int oldMinImpactOreX = 0;
    private int oldMaxImpactOreX = 0;
    private int oldMinImpactOreZ = 0;
    private int oldMaxImpactOreZ = 0;

    public AbstractImpactOreLayerManager(ButtonManager buttonManager) {
        super(buttonManager);
    }

    public abstract int getLayer();

    @Override
    protected boolean needsRegenerateVisibleElements(int minBlockX, int minBlockZ, int maxBlockX, int maxBlockZ) {
        final int minImpactOreX = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(minBlockX));
        final int minImpactOreZ = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(minBlockZ));
        final int maxImpactOreX = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(maxBlockX));
        final int maxImpactOreZ = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(maxBlockZ));
        if (minImpactOreX != oldMinImpactOreX || maxImpactOreX != oldMaxImpactOreX
                || minImpactOreZ != oldMinImpactOreZ
                || maxImpactOreZ != oldMaxImpactOreZ) {
            oldMinImpactOreX = minImpactOreX;
            oldMaxImpactOreX = maxImpactOreX;
            oldMinImpactOreZ = minImpactOreZ;
            oldMaxImpactOreZ = maxImpactOreZ;
            return true;
        }
        return false;
    }

    @Override
    protected List<? extends ILocationProvider> generateVisibleElements(int minBlockX, int minBlockZ, int maxBlockX,
            int maxBlockZ) {
        final int minImpactOreX = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(minBlockX));
        final int minImpactOreZ = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(minBlockZ));
        final int maxImpactOreX = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(maxBlockX));
        final int maxImpactOreZ = Utils.mapToCornerImpactOreChunkCoord(Utils.coordBlockToChunk(maxBlockZ));
        final int playerDimensionId = Minecraft.getMinecraft().thePlayer.dimension;

        List<AbstractImpactOreLocation> impactOreLocations = new ArrayList<>();

        for (int chunkX = minImpactOreX; chunkX <= maxImpactOreX; chunkX += VP.impactOreSizeChunkX) {
            for (int chunkZ = minImpactOreZ; chunkZ <= maxImpactOreZ; chunkZ += VP.impactOreSizeChunkZ) {
                final ImpactOrePosition impactOre = ClientCache.instance
                        .getImpactOre(playerDimensionId, chunkX, chunkZ);
                if (impactOre.isProspected(getLayer())) {
                    impactOreLocations.add(createLocation(impactOre));
                }
            }
        }

        return impactOreLocations;
    }

    protected abstract AbstractImpactOreLocation createLocation(ImpactOrePosition impactOre);
}
