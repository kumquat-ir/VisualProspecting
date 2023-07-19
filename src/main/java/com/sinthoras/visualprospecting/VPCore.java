package com.sinthoras.visualprospecting;

import java.util.*;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.sinthoras.visualprospecting.mixins.Mixin;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class VPCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.visualprospecting.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        final List<String> mixins = new ArrayList<>();
        for (Mixin mixin : Mixin.values()) {
            if (mixin.shouldLoad(Collections.emptySet(), loadedCoreMods, Mixin.Phase.EARLY)) {
                mixins.add(mixin.mixinClass);
            }
        }
        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
