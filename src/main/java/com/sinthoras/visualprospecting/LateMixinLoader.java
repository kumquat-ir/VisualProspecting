package com.sinthoras.visualprospecting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.sinthoras.visualprospecting.mixins.Mixin;

@LateMixin
public class LateMixinLoader implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.visualprospecting.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        final List<String> mixins = new ArrayList<>();
        for (Mixin mixin : Mixin.values()) {
            if (mixin.shouldLoad(loadedMods, Collections.emptySet(), Mixin.Phase.LATE)) {
                mixins.add(mixin.mixinClass);
            }
        }
        return mixins;
    }
}
