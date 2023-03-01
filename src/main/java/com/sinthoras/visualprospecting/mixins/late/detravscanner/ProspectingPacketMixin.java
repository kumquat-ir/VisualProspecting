package com.sinthoras.visualprospecting.mixins.late.detravscanner;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.detrav.net.DetravPacket;
import com.detrav.net.ProspectingPacket;
import com.sinthoras.visualprospecting.integration.detravscanner.ProspectingPacketParser;

@Mixin(value = ProspectingPacket.class, remap = false)
public abstract class ProspectingPacketMixin extends DetravPacket {

    @Inject(method = "process", at = @At("HEAD"))
    private void vp$injectProcess(CallbackInfo ci) {
        ProspectingPacketParser.parse((ProspectingPacket) (Object) this);
    }
}
