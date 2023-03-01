package com.sinthoras.visualprospecting.integration.detravscanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.detrav.net.ProspectingPacket;
import com.impact.common.oregeneration.OreVein;
import com.impact.core.Impact_API;
import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.database.ClientCache;
import com.sinthoras.visualprospecting.database.ImpactOrePosition;
import com.sinthoras.visualprospecting.database.ServerCache;
import com.sinthoras.visualprospecting.database.UndergroundFluidPosition;

public class ProspectingPacketParser {

    public static void parse(ProspectingPacket packet) {
        if (!Utils.isLogicalClient()) {
            VP.error("Prospecting packet received on server???");
            return;
        }
        int wh = packet.getSize();
        int minChunkX = packet.chunkX - packet.size;
        int minChunkZ = packet.chunkZ - packet.size;
        int dimID = Minecraft.getMinecraft().thePlayer.dimension;
        switch (packet.ptype) {
            case 0: // ores
            case 1: // ores + small ores
                // will never be called, since impact has normal oregen disabled, but just in case
                ClientCache.instance.putOreVeins(
                        ServerCache.instance.prospectOreBlockRadius(dimID, packet.posX, packet.posZ, packet.size * 16));
                break;
            case 2: // fluids
                Map<ChunkCoordIntPair, FluidEntry> fluidAmounts = new HashMap<>();
                List<UndergroundFluidPosition> fluidPositions = new ArrayList<>();
                for (int i = 1; i < wh; i += 16) {
                    for (int j = 1; j < wh; j += 16) {
                        if (packet.map[i][j] == null) continue;
                        int chunkX = minChunkX + i / 16;
                        int chunkZ = minChunkZ + j / 16;
                        int fluidChunkX = Utils.mapToCornerUndergroundFluidChunkCoord(chunkX);
                        int fluidChunkZ = Utils.mapToCornerUndergroundFluidChunkCoord(chunkZ);
                        int fluidChunkXOffset = chunkX - fluidChunkX;
                        int fluidChunkZOffset = chunkZ - fluidChunkZ;
                        ChunkCoordIntPair amtKey = makeKey(fluidChunkX, fluidChunkZ);
                        if (!fluidAmounts.containsKey(amtKey)) {
                            fluidAmounts.put(amtKey, new FluidEntry(fluidChunkX, fluidChunkZ, dimID));
                        }
                        fluidAmounts.get(amtKey).amounts[fluidChunkXOffset][fluidChunkZOffset] = packet.map[i][j]
                                .get((byte) 2);
                        fluidAmounts.get(amtKey).setFluid(FluidRegistry.getFluid(packet.map[i][j].get((byte) 1)));
                    }
                }
                for (FluidEntry entry : fluidAmounts.values()) {
                    fluidPositions.add(
                            new UndergroundFluidPosition(
                                    dimID,
                                    entry.chunkX,
                                    entry.chunkZ,
                                    entry.fluid,
                                    entry.amounts));
                }
                ClientCache.instance.putUndergroundFluids(fluidPositions);
                break;
            case 3: // pollution
                break;
            case 4: // impact ore layer 0
            case 5: // impact ore layer 1
                int layer = packet.ptype - 4;
                Map<ChunkCoordIntPair, ImpactOreEntry> impactOreAmounts = new HashMap<>();
                List<ImpactOrePosition> impactOrePositions = new ArrayList<>();
                for (int i = 0; i < wh; i += 16) {
                    for (int j = 0; j < wh; j += 16) {
                        if (packet.map[i][j] == null) continue;
                        int chunkX = minChunkX + i / 16;
                        int chunkZ = minChunkZ + j / 16;
                        int impactOreChunkX = Utils.mapToCornerImpactOreChunkCoord(chunkX);
                        int impactOreChunkZ = Utils.mapToCornerImpactOreChunkCoord(chunkZ);
                        int impactOreChunkXOffset = chunkX - impactOreChunkX;
                        int impactOreChunkZOffset = chunkZ - impactOreChunkZ;
                        ChunkCoordIntPair amtKey = makeKey(impactOreChunkX, impactOreChunkZ);
                        if (!impactOreAmounts.containsKey(amtKey)) {
                            impactOreAmounts.put(amtKey, new ImpactOreEntry(impactOreChunkX, impactOreChunkZ, dimID));
                        }
                        if (layer == 0) {
                            impactOreAmounts.get(
                                    amtKey).amounts0[impactOreChunkXOffset][impactOreChunkZOffset] = packet.map[i][j]
                                            .get((byte) 2);
                            impactOreAmounts.get(amtKey)
                                    .setVein0(Impact_API.registerVeins.get((int) packet.map[i][j].get((byte) 1)));
                        } else if (layer == 1) {
                            impactOreAmounts.get(
                                    amtKey).amounts1[impactOreChunkXOffset][impactOreChunkZOffset] = packet.map[i][j]
                                            .get((byte) 2);
                            impactOreAmounts.get(amtKey)
                                    .setVein1(Impact_API.registerVeins.get((int) packet.map[i][j].get((byte) 1)));
                        }
                    }
                }
                for (ImpactOreEntry entry : impactOreAmounts.values()) {
                    impactOrePositions.add(
                            new ImpactOrePosition(
                                    dimID,
                                    entry.chunkX,
                                    entry.chunkZ,
                                    entry.vein0,
                                    entry.vein1,
                                    entry.amounts0,
                                    entry.amounts1));
                }
                ClientCache.instance.putImpactOres(impactOrePositions);
                break;
            default:
                break;
        }
    }

    private static ChunkCoordIntPair makeKey(int chunkX, int chunkZ) {
        return new ChunkCoordIntPair(chunkX, chunkZ);
    }

    private static class FluidEntry {

        int[][] amounts;
        Fluid fluid;
        int chunkX;
        int chunkZ;

        FluidEntry(int chunkX, int chunkZ, int dimID) {
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.amounts = ClientCache.instance.getUndergroundFluid(dimID, chunkX, chunkZ).chunks;
            if (this.amounts == null) {
                this.amounts = new int[VP.undergroundFluidSizeChunkX][VP.undergroundFluidSizeChunkZ];
            }
            // prevent mutation of the cache
            this.amounts = Utils.deeperCopy(this.amounts);
        }

        void setFluid(Fluid fluid) {
            this.fluid = fluid;
        }
    }

    private static class ImpactOreEntry {

        int[][] amounts0;
        int[][] amounts1;
        OreVein vein0;
        OreVein vein1;
        int chunkX;
        int chunkZ;

        ImpactOreEntry(int chunkX, int chunkZ, int dimID) {
            ImpactOrePosition chunkOre = ClientCache.instance.getImpactOre(dimID, chunkX, chunkZ);
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.amounts0 = chunkOre.chunksLayer0;
            this.amounts1 = chunkOre.chunksLayer1;
            this.vein0 = chunkOre.veinLayer0;
            this.vein1 = chunkOre.veinLayer1;
            if (this.amounts0 == null) {
                this.amounts0 = new int[VP.impactOreSizeChunkX][VP.impactOreSizeChunkZ];
            }
            // prevent mutation of the cache
            this.amounts0 = Utils.deeperCopy(this.amounts0);
            if (this.amounts1 == null) {
                this.amounts1 = new int[VP.impactOreSizeChunkX][VP.impactOreSizeChunkZ];
            }
            // prevent mutation of the cache
            this.amounts1 = Utils.deeperCopy(this.amounts1);
        }

        void setVein0(OreVein vein0) {
            this.vein0 = vein0;
        }

        void setVein1(OreVein vein1) {
            this.vein1 = vein1;
        }
    }
}
