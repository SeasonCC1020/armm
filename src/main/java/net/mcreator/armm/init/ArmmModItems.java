
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.armm.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.armm.item.XingXingItem;
import net.mcreator.armm.item.MagicStoneredItem;
import net.mcreator.armm.item.MagicStoneblueItem;
import net.mcreator.armm.item.DifferentDimensionStorageBagItem;
import net.mcreator.armm.ArmmMod;

public class ArmmModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ArmmMod.MODID);
	public static final RegistryObject<Item> MAGIC_STONEBLUE = REGISTRY.register("magic_stoneblue", () -> new MagicStoneblueItem());
	public static final RegistryObject<Item> MAGIC_STONERED = REGISTRY.register("magic_stonered", () -> new MagicStoneredItem());
	public static final RegistryObject<Item> DIFFERENT_DIMENSION_STORAGE_BAG = REGISTRY.register("different_dimension_storage_bag",
			() -> new DifferentDimensionStorageBagItem());
	public static final RegistryObject<Item> XING_XING = REGISTRY.register("xing_xing", () -> new XingXingItem());
}
