package com.sinthoras.visualprospecting.integration.xaeroworldmap;

import static com.sinthoras.visualprospecting.Utils.isImpact;
import static com.sinthoras.visualprospecting.Utils.isTCNodeTrackerInstalled;

import java.util.ArrayList;
import java.util.List;

import com.sinthoras.visualprospecting.integration.xaeroworldmap.buttons.*;
import com.sinthoras.visualprospecting.integration.xaeroworldmap.renderers.*;

public class XaeroWorldMapState {

    public static XaeroWorldMapState instance = new XaeroWorldMapState();

    public final List<LayerButton> buttons = new ArrayList<>();
    public final List<LayerRenderer> renderers = new ArrayList<>();

    public XaeroWorldMapState() {
        if (!isImpact()) {
            buttons.add(OreVeinButton.instance);
            renderers.add(OreVeinRenderer.instance);
        } else {
            buttons.add(ImpactLayer1Button.instance);
            buttons.add(ImpactLayer0Button.instance);
            renderers.add(ImpactChunkLayer1Renderer.instance);
            renderers.add(ImpactLayer1Renderer.instance);
            renderers.add(ImpactChunkLayer0Renderer.instance);
            renderers.add(ImpactLayer0Renderer.instance);
        }

        buttons.add(UndergroundFluidButton.instance);
        renderers.add(UndergroundFluidChunkRenderer.instance);
        renderers.add(UndergroundFluidRenderer.instance);

        if (isTCNodeTrackerInstalled()) {
            buttons.add(ThaumcraftNodeButton.instance);
            renderers.add(ThaumcraftNodeRenderer.instance);
        }
    }
}
