package com.sinthoras.visualprospecting.database.cachebuilder;

import static com.sinthoras.visualprospecting.Utils.isSmallOreId;
import static com.sinthoras.visualprospecting.Utils.oreIdToMaterialId;

import io.xol.enklume.nbt.NBTCompound;
import io.xol.enklume.nbt.NBTInt;
import io.xol.enklume.nbt.NBTShort;
import io.xol.enklume.nbt.NBTString;

public class GregTechOre {

    public final boolean isValidGTOre;
    public final short metaData;
    public final int blockY;

    public GregTechOre(NBTCompound tileEntity) {
        final NBTString tagId = (NBTString) tileEntity.getTag("id");
        final NBTShort tagMeta = (NBTShort) tileEntity.getTag("m");
        final NBTInt tagBlockY = (NBTInt) tileEntity.getTag("y");

        if (tagId != null && tagId.getText().equals("GT_TileEntity_Ores")
                && tagBlockY != null
                && tagMeta != null
                && !isSmallOreId(tagMeta.data)) {
            metaData = oreIdToMaterialId(tagMeta.data);
            isValidGTOre = true;
            blockY = tagBlockY.data;
        } else {
            isValidGTOre = false;
            metaData = 0;
            blockY = 0;
        }
    }
}
