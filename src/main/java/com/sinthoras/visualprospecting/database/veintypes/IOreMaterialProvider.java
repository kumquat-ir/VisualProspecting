package com.sinthoras.visualprospecting.database.veintypes;

import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IOreMaterialProvider {

    @SideOnly(Side.CLIENT)
    IIcon getIcon();

    int getColor();
}
