package com.sinthoras.visualprospecting.integration.xaeroworldmap.rendersteps;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.integration.DrawUtils;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreLocation;

public class ImpactOreRenderStep implements RenderStep {

    private final AbstractImpactOreLocation impactOreLocation;

    public ImpactOreRenderStep(AbstractImpactOreLocation impactOre) {
        impactOreLocation = impactOre;
    }

    @Override
    public void draw(@Nullable GuiScreen gui, double cameraX, double cameraZ, double scale) {
        final int maxAmountInField = impactOreLocation.getMaxProduction();
        // < 0.5 scale is when scaling issues show up
        if (maxAmountInField > 0 && scale >= 0.5) {
            GL11.glPushMatrix();
            GL11.glTranslated(
                    impactOreLocation.getBlockX() - 0.5 - cameraX,
                    impactOreLocation.getBlockZ() - 0.5 - cameraZ,
                    0);

            final int borderColor = DrawUtils.intColor(impactOreLocation.getVein().colorVein) | 0xCC000000;
            final double lenX = VP.impactOreSizeChunkX * VP.chunkWidth;
            final double lenZ = VP.impactOreSizeChunkZ * VP.chunkDepth;
            DrawUtils.drawGradientRect(0, 0, lenX - 1, 1, 0, borderColor, borderColor);
            DrawUtils.drawGradientRect(lenX - 1, 0, lenX, lenZ - 1, 0, borderColor, borderColor);
            DrawUtils.drawGradientRect(1, lenZ - 1, lenX, lenZ, 0, borderColor, borderColor);
            DrawUtils.drawGradientRect(0, 1, 1, lenZ, 0, borderColor, borderColor);

            // min scale that journeymap can go to
            if (scale >= 1 && gui != null) {
                GL11.glScaled(1 / scale, 1 / scale, 1);
                final String label = impactOreLocation.getMinProduction() + "k - "
                        + maxAmountInField
                        + "k  "
                        + impactOreLocation.getVein().nameVein;
                DrawUtils.drawSimpleLabel(gui, label, VP.chunkWidth * scale, 0, 0xFFFFFFFF, 0xB4000000, false);
            }

            GL11.glPopMatrix();
        }
    }
}
