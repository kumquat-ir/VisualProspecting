package com.sinthoras.visualprospecting.integration.journeymap.buttons;

import com.sinthoras.visualprospecting.integration.model.buttons.ImpactLayer1ButtonManager;

public class ImpactLayer1Button extends LayerButton {

    public static final ImpactLayer1Button instance = new ImpactLayer1Button();

    public ImpactLayer1Button() {
        super(ImpactLayer1ButtonManager.instance);
    }
}
