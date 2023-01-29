package com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps;

import net.minecraft.client.gui.GuiScreen;

import com.sinthoras.visualprospecting.integration.model.locations.IWaypointAndLocationProvider;

public interface InteractableRenderStep extends RenderStep {

    boolean isMouseOver(double mouseX, double mouseY, double scale);

    void drawTooltip(GuiScreen gui, double mouseX, double mouseY, double scale, int scaleAdj);

    void onActionButton();

    IWaypointAndLocationProvider getLocationProvider();
}
