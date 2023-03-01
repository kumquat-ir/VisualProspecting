package com.sinthoras.visualprospecting.integration.model.locations;

import com.impact.common.oregeneration.OreVein;
import com.sinthoras.visualprospecting.Utils;

public abstract class AbstractImpactOreChunkLocation implements ILocationProvider {

    private final int blockX;
    private final int blockZ;
    private final int dimensionId;
    private final OreVein vein;
    private final int oreAmount;
    private final int maxAmountInField;
    private final int minAmountInField;

    public AbstractImpactOreChunkLocation(int chunkX, int chunkZ, int dimensionId, OreVein vein, int oreAmount,
            int minAmountInField, int maxAmountInField) {
        blockX = Utils.coordChunkToBlock(chunkX);
        blockZ = Utils.coordChunkToBlock(chunkZ);
        this.dimensionId = dimensionId;
        this.vein = vein;
        this.oreAmount = oreAmount;
        this.maxAmountInField = maxAmountInField;
        this.minAmountInField = minAmountInField;
    }

    public abstract int getLayer();

    public double getBlockX() {
        return blockX + 0.5;
    }

    public double getBlockZ() {
        return blockZ + 0.5;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public String getOreAmountFormatted() {
        return oreAmount + "k";
    }

    public int getOreAmount() {
        return oreAmount;
    }

    public OreVein getVein() {
        return vein;
    }

    public int getMaxAmountInField() {
        return maxAmountInField;
    }

    public int getMinAmountInField() {
        return minAmountInField;
    }
}
