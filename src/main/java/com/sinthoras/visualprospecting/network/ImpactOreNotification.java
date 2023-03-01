package com.sinthoras.visualprospecting.network;

import java.util.ArrayList;
import java.util.Collections;

import com.impact.core.Impact_API;
import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.database.ClientCache;
import com.sinthoras.visualprospecting.database.ImpactOrePosition;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class ImpactOreNotification implements IMessage {

    private int chunkX;
    private int chunkZ;
    private int dimID;
    private int veinID;
    private int oreAmount;

    public ImpactOreNotification() {}

    public ImpactOreNotification(int chunkX, int chunkZ, int dimID, int veinID, int oreAmount) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.dimID = dimID;
        this.veinID = veinID;
        this.oreAmount = oreAmount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
        dimID = buf.readInt();
        veinID = buf.readInt();
        oreAmount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeInt(dimID);
        buf.writeInt(veinID);
        buf.writeInt(oreAmount);
    }

    public static class Handler implements IMessageHandler<ImpactOreNotification, IMessage> {

        @Override
        public IMessage onMessage(ImpactOreNotification message, MessageContext ctx) {
            int impactOreChunkX = Utils.mapToCornerImpactOreChunkCoord(message.chunkX);
            int impactOreChunkZ = Utils.mapToCornerImpactOreChunkCoord(message.chunkZ);
            int impactOreChunkXOffset = message.chunkX - impactOreChunkX;
            int impactOreChunkZOffset = message.chunkZ - impactOreChunkZ;

            ImpactOrePosition existingOre = ClientCache.instance
                    .getImpactOre(message.dimID, impactOreChunkX, impactOreChunkZ);
            int[][] amounts = existingOre.chunksLayer0;
            if (amounts == null) {
                amounts = new int[VP.impactOreSizeChunkX][VP.impactOreSizeChunkZ];
            }
            // avoid mutating the cache
            amounts = Utils.deeperCopy(amounts);
            amounts[impactOreChunkXOffset][impactOreChunkZOffset] = message.oreAmount;

            ClientCache.instance.putImpactOres(
                    new ArrayList<>(
                            Collections.singleton(
                                    new ImpactOrePosition(
                                            message.dimID,
                                            impactOreChunkX,
                                            impactOreChunkZ,
                                            Impact_API.registerVeins.get(message.veinID),
                                            existingOre.veinLayer1,
                                            amounts,
                                            existingOre.chunksLayer1))));

            return null;
        }
    }
}
