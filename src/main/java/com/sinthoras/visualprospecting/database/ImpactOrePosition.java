package com.sinthoras.visualprospecting.database;

import java.util.Arrays;

import com.impact.common.oregeneration.OreVein;
import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;

public class ImpactOrePosition {

    public final int dimensionId;
    public final int chunkX;
    public final int chunkZ;
    public final OreVein veinLayer0;
    public final OreVein veinLayer1;
    public final int[][] chunksLayer0;
    public final int[][] chunksLayer1;

    public static ImpactOrePosition getNotProspected(int dimensionId, int chunkX, int chunkZ) {
        return new ImpactOrePosition(dimensionId, chunkX, chunkZ, null, null, null, null);
    }

    public ImpactOrePosition(int dimensionId, int chunkX, int chunkZ, OreVein veinLayer0, OreVein veinLayer1,
            int[][] chunksLayer0, int[][] chunksLayer1) {
        this.dimensionId = dimensionId;
        this.chunkX = Utils.mapToCornerImpactOreChunkCoord(chunkX);
        this.chunkZ = Utils.mapToCornerImpactOreChunkCoord(chunkZ);
        this.veinLayer0 = veinLayer0;
        this.veinLayer1 = veinLayer1;
        this.chunksLayer0 = chunksLayer0;
        this.chunksLayer1 = chunksLayer1;
    }

    public int getBlockX() {
        return Utils.coordChunkToBlock(chunkX);
    }

    public int getBlockZ() {
        return Utils.coordChunkToBlock(chunkZ);
    }

    public int getMinProduction(int layer) {
        int smallest = Integer.MAX_VALUE;
        for (int chunkX = 0; chunkX < VP.impactOreSizeChunkX; chunkX++) {
            for (int chunkZ = 0; chunkZ < VP.impactOreSizeChunkZ; chunkZ++) {
                if (layer == 0 && chunksLayer0 != null && chunksLayer0[chunkX][chunkZ] < smallest) {
                    smallest = chunksLayer0[chunkX][chunkZ];
                } else if (layer == 1 && chunksLayer1 != null && chunksLayer1[chunkX][chunkZ] < smallest) {
                    smallest = chunksLayer1[chunkX][chunkZ];
                }
            }
        }
        return smallest;
    }

    public int getMaxProduction(int layer) {
        int largest = Integer.MIN_VALUE;
        for (int chunkX = 0; chunkX < VP.impactOreSizeChunkX; chunkX++) {
            for (int chunkZ = 0; chunkZ < VP.impactOreSizeChunkZ; chunkZ++) {
                if (layer == 0 && chunksLayer0 != null && chunksLayer0[chunkX][chunkZ] > largest) {
                    largest = chunksLayer0[chunkX][chunkZ];
                } else if (layer == 1 && chunksLayer1 != null && chunksLayer1[chunkX][chunkZ] > largest) {
                    largest = chunksLayer1[chunkX][chunkZ];
                }
            }
        }
        return largest;
    }

    public boolean isProspected(int layer) {
        return layer == 0 ? veinLayer0 != null : veinLayer1 != null;
    }

    public boolean equals(ImpactOrePosition other) {
        return dimensionId == other.dimensionId && chunkX == other.chunkX
                && chunkZ == other.chunkZ
                && veinLayer0 == other.veinLayer0
                && veinLayer1 == other.veinLayer1
                && Arrays.deepEquals(chunksLayer0, other.chunksLayer0)
                && Arrays.deepEquals(chunksLayer1, other.chunksLayer1);
    }
}
