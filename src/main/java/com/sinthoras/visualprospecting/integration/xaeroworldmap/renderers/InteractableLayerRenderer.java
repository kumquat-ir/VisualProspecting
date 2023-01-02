package com.sinthoras.visualprospecting.integration.xaeroworldmap.renderers;

import com.sinthoras.visualprospecting.integration.model.layers.WaypointProviderManager;
import com.sinthoras.visualprospecting.integration.model.locations.ILocationProvider;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps.InteractableRenderStep;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps.RenderStep;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public abstract class InteractableLayerRenderer extends LayerRenderer {
    private double mouseX;
    private double mouseY;
    protected WaypointProviderManager manager;
    protected InteractableRenderStep hovered;

    public InteractableLayerRenderer(WaypointProviderManager manager) {
        super(manager);
        this.manager = manager;
        hovered = null;
    }

    @Override
    protected abstract List<? extends InteractableRenderStep> generateRenderSteps(
            List<? extends ILocationProvider> visibleElements);

    public void updateHovered(double mouseX, double mouseY, double scale) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        for (RenderStep step : renderStepsReversed) {
            if (step instanceof InteractableRenderStep
                    && ((InteractableRenderStep) step).isMouseOver(mouseX, mouseY, scale)) {
                hovered = (InteractableRenderStep) step;
                return;
            }
        }
        hovered = null;
    }

    public void drawTooltip(GuiScreen gui, double scale, int scaleAdj) {
        if (hovered != null) {
            hovered.drawTooltip(gui, mouseX, mouseY, scale, scaleAdj);
        }
    }

    public void doActionKeyPress() {
        if (manager.isLayerActive() && hovered != null) {
            hovered.onActionButton();
            manager.forceRefresh();
        }
    }

    public void doDoubleClick() {
        if (hovered != null) {
            if (hovered.getLocationProvider().isActiveAsWaypoint()) {
                manager.clearActiveWaypoint();
            } else {
                manager.setActiveWaypoint(hovered.getLocationProvider().toWaypoint());
            }
        }
    }
}
