
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.armm.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class ArmmModTabs {
	public static CreativeModeTab TAB_ARMM;

	public static void load() {
		TAB_ARMM = new CreativeModeTab("tabarmm") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(ArmmModItems.MAGIC_STONEBLUE.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
