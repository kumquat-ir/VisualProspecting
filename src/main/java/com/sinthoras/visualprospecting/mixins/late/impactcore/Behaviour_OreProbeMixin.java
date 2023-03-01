package com.sinthoras.visualprospecting.mixins.late.impactcore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.impact.common.oregeneration.OreGenerator;
import com.impact.common.oregeneration.OreVein;
import com.impact.mods.gregtech.items.tools.behaviour.Behaviour_OreProbe;
import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.network.ImpactOreNotification;
import gregtech.api.items.GT_MetaBase_Item;

@Mixin(value = Behaviour_OreProbe.class, remap = false)
public class Behaviour_OreProbeMixin {

    @Inject(
            method = "onItemUseFirst(Lgregtech/api/items/GT_MetaBase_Item;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIIIFFF)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/impact/util/Utilits;translateGTItemStack(Lnet/minecraft/item/ItemStack;)Ljava/lang/String;"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void vp$injectSendMessage(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld,
            int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir,
            Chunk ch, OreVein vein) {
        int chunkX = Utils.coordBlockToChunk(aX);
        int chunkZ = Utils.coordBlockToChunk(aZ);
        if (aPlayer instanceof EntityPlayerMP) {
            VP.network.sendTo(
                    new ImpactOreNotification(
                            chunkX,
                            chunkZ,
                            aPlayer.dimension,
                            vein.idVein,
                            OreGenerator.sizeChunk(ch, 0) / 1000),
                    (EntityPlayerMP) aPlayer);
        }
    }
}
