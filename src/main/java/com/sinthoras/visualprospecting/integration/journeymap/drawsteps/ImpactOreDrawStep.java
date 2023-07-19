package com.sinthoras.visualprospecting.integration.journeymap.drawsteps;

import java.awt.geom.Point2D;

import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.integration.DrawUtils;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreLocation;

import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;

public class ImpactOreDrawStep implements DrawStep {

    private final AbstractImpactOreLocation impactOreLocation;

    public ImpactOreDrawStep(AbstractImpactOreLocation impactOreLocation) {
        this.impactOreLocation = impactOreLocation;
    }

    @Override
    public void draw(double draggedPixelX, double draggedPixelY, GridRenderer gridRenderer, float drawScale,
            double fontScale, double rotation) {
        final int maxAmountInField = impactOreLocation.getMaxProduction();
        if (maxAmountInField > 0) {
            final double blockSize = Math.pow(2, gridRenderer.getZoom());
            final Point2D.Double blockAsPixel = gridRenderer
                    .getBlockPixelInGrid(impactOreLocation.getBlockX(), impactOreLocation.getBlockZ());
            final Point2D.Double pixel = new Point2D.Double(
                    blockAsPixel.getX() + draggedPixelX,
                    blockAsPixel.getY() + draggedPixelY);

            final int borderColor = DrawUtils.intColor(impactOreLocation.getVein().colorVein);
            final int borderAlpha = 204;
            DrawUtil.drawRectangle(
                    pixel.getX(),
                    pixel.getY(),
                    VP.impactOreSizeChunkX * VP.chunkWidth * blockSize,
                    2 * blockSize,
                    borderColor,
                    borderAlpha);
            DrawUtil.drawRectangle(
                    pixel.getX() + VP.impactOreSizeChunkX * VP.chunkWidth * blockSize,
                    pixel.getY(),
                    2 * blockSize,
                    VP.impactOreSizeChunkZ * VP.chunkDepth * blockSize,
                    borderColor,
                    borderAlpha);
            DrawUtil.drawRectangle(
                    pixel.getX() + 2 * blockSize,
                    pixel.getY() + VP.impactOreSizeChunkZ * VP.chunkDepth * blockSize,
                    VP.impactOreSizeChunkX * VP.chunkWidth * blockSize,
                    2 * blockSize,
                    borderColor,
                    borderAlpha);
            DrawUtil.drawRectangle(
                    pixel.getX(),
                    pixel.getY() + 2 * blockSize,
                    2 * blockSize,
                    VP.impactOreSizeChunkZ * VP.chunkDepth * blockSize,
                    borderColor,
                    borderAlpha);

            final String label = impactOreLocation.getMinProduction() + "k - "
                    + maxAmountInField
                    + "k  "
                    + impactOreLocation.getVein().nameVein;
            DrawUtil.drawLabel(
                    label,
                    pixel.getX() + VP.chunkWidth * blockSize,
                    pixel.getY(),
                    DrawUtil.HAlign.Right,
                    DrawUtil.VAlign.Below,
                    0,
                    180,
                    0x00FFFFFF,
                    255,
                    fontScale,
                    false,
                    rotation);
        }
    }
}
