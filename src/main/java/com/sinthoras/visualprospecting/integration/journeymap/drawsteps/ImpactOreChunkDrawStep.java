package com.sinthoras.visualprospecting.integration.journeymap.drawsteps;

import java.awt.geom.Point2D;

import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;

import com.sinthoras.visualprospecting.Config;
import com.sinthoras.visualprospecting.VP;
import com.sinthoras.visualprospecting.integration.DrawUtils;
import com.sinthoras.visualprospecting.integration.model.locations.AbstractImpactOreChunkLocation;

public class ImpactOreChunkDrawStep implements DrawStep {

    private final AbstractImpactOreChunkLocation impactOreChunkLocation;

    public ImpactOreChunkDrawStep(AbstractImpactOreChunkLocation impactOreChunkLocation) {
        this.impactOreChunkLocation = impactOreChunkLocation;
    }

    @Override
    public void draw(double draggedPixelX, double draggedPixelY, GridRenderer gridRenderer, float drawScale,
            double fontScale, double rotation) {
        final int zoom = gridRenderer.getZoom();
        if (impactOreChunkLocation.getOreAmount() > 0 && zoom >= Config.minZoomLevelForUndergroundFluidDetails) {
            double blockSize = Math.pow(2, zoom);
            final Point2D.Double blockAsPixel = gridRenderer
                    .getBlockPixelInGrid(impactOreChunkLocation.getBlockX(), impactOreChunkLocation.getBlockZ());
            final Point2D.Double pixel = new Point2D.Double(
                    blockAsPixel.getX() + draggedPixelX,
                    blockAsPixel.getY() + draggedPixelY);
            float alpha = ((float) (impactOreChunkLocation.getOreAmount()
                    - impactOreChunkLocation.getMinAmountInField()))
                    / (impactOreChunkLocation.getMaxAmountInField() - impactOreChunkLocation.getMinAmountInField() + 1);
            alpha *= alpha * 204;
            DrawUtil.drawRectangle(
                    pixel.getX(),
                    pixel.getY(),
                    VP.chunkWidth * blockSize,
                    VP.chunkDepth * blockSize,
                    DrawUtils.intColor(impactOreChunkLocation.getVein().colorVein),
                    (int) alpha);

            if (impactOreChunkLocation.getOreAmount() >= impactOreChunkLocation.getMaxAmountInField()) {
                final int borderColor = 0xFFD700;
                final int borderAlpha = 204;
                DrawUtil.drawRectangle(pixel.getX(), pixel.getY(), 15 * blockSize, blockSize, borderColor, borderAlpha);
                DrawUtil.drawRectangle(
                        pixel.getX() + 15 * blockSize,
                        pixel.getY(),
                        blockSize,
                        15 * blockSize,
                        borderColor,
                        borderAlpha);
                DrawUtil.drawRectangle(
                        pixel.getX() + 1 * blockSize,
                        pixel.getY() + 15 * blockSize,
                        15 * blockSize,
                        blockSize,
                        borderColor,
                        borderAlpha);
                DrawUtil.drawRectangle(
                        pixel.getX(),
                        pixel.getY() + 1 * blockSize,
                        blockSize,
                        15 * blockSize,
                        borderColor,
                        borderAlpha);
            }

            DrawUtil.drawLabel(
                    impactOreChunkLocation.getOreAmountFormatted(),
                    pixel.getX() + 13 * blockSize,
                    pixel.getY() + 13 * blockSize,
                    DrawUtil.HAlign.Left,
                    DrawUtil.VAlign.Above,
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
