package com.sinthoras.visualprospecting.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;

import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.database.ClientCache;
import com.sinthoras.visualprospecting.database.ServerCache;
import com.sinthoras.visualprospecting.database.WorldIdHandler;
import com.sinthoras.visualprospecting.network.WorldIdNotification;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HooksEventBus {

    @SubscribeEvent
    public void onEvent(WorldEvent.Unload event) {
        if (Utils.isLogicalClient()) {
            ClientCache.instance.saveVeinCache();
        }
    }

    @SubscribeEvent
    public void onEvent(WorldEvent.Save event) {
        ServerCache.instance.saveVeinCache();
    }

    @SubscribeEvent
    public void onEvent(EntityJoinWorldEvent event) {
        if (!event.world.isRemote) {
            if (event.entity instanceof EntityPlayerMP) {
                VP.network.sendTo(new WorldIdNotification(WorldIdHandler.getWorldId()), (EntityPlayerMP) event.entity);
            } else if (event.entity instanceof EntityPlayer) {
                ClientCache.instance.loadVeinCache(WorldIdHandler.getWorldId());
            }
        }
    }
}
