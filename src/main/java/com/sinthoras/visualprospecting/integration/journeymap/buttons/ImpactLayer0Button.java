package com.sinthoras.visualprospecting.integration.journeymap.buttons;

import com.sinthoras.visualprospecting.integration.model.buttons.ImpactLayer0ButtonManager;

public class ImpactLayer0Button extends LayerButton {

    public static final ImpactLayer0Button instance = new ImpactLayer0Button();

    public ImpactLayer0Button() {
        super(ImpactLayer0ButtonManager.instance);
    }
}
