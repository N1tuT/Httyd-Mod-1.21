package net.nitu.httydmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HttydMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem((ModItems.NIGHT_FURY_SCALE.get()));
    }
}
