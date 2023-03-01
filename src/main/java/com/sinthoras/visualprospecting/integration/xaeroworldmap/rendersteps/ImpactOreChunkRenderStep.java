package com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.sinthoras.visualprospecting.Config;
import com.sinthoras.visualprospecting.Utils;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.integration.DrawUtils;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreChunkLocation;

public class ImpactOreChunkRenderStep implements RenderStep {

    private final AbstractImpactOreChunkLocation impactOreChunkLocation;

    public ImpactOreChunkRenderStep(AbstractImpactOreChunkLocation location) {
        impactOreChunkLocation = location;
    }

    @Override
    public void draw(@Nullable GuiScreen gui, double cameraX, double cameraZ, double scale) {
        if (impactOreChunkLocation.getOreAmount() > 0
                && scale >= Utils.journeyMapScaleToLinear(Config.minZoomLevelForUndergroundFluidDetails)) {
            GL11.glPushMatrix();
            GL11.glTranslated(
                    impactOreChunkLocation.getBlockX() - 0.5 - cameraX,
                    impactOreChunkLocation.getBlockZ() - 0.5 - cameraZ,
                    0);

            float alpha = ((float) (impactOreChunkLocation.getOreAmount()
                    - impactOreChunkLocation.getMinAmountInField()))
                    / (impactOreChunkLocation.getMaxAmountInField() - impactOreChunkLocation.getMinAmountInField() + 1);
            alpha *= alpha * 204;
            int fluidColor = DrawUtils.intColor(impactOreChunkLocation.getVein().colorVein) | (((int) alpha) << 24);
            DrawUtils.drawGradientRect(0, 0, VP.chunkWidth, VP.chunkDepth, 0, fluidColor, fluidColor);

            if (impactOreChunkLocation.getOreAmount() >= impactOreChunkLocation.getMaxAmountInField()) {
                final int borderColor = 0xCCFFD700;
                DrawUtils.drawGradientRect(0, 0, 15, 1, 0, borderColor, borderColor);
                DrawUtils.drawGradientRect(15, 0, 16, 15, 0, borderColor, borderColor);
                DrawUtils.drawGradientRect(1, 15, 16, 16, 0, borderColor, borderColor);
                DrawUtils.drawGradientRect(0, 1, 1, 16, 0, borderColor, borderColor);
            }

            GL11.glScaled(1 / scale, 1 / scale, 1);
            if (gui != null) {
                DrawUtils.drawSimpleLabel(
                        gui,
                        impactOreChunkLocation.getOreAmountFormatted(),
                        13,
                        13,
                        0xFFFFFFFF,
                        0xB4000000,
                        false);
            }
            GL11.glPopMatrix();
        }
    }
}
