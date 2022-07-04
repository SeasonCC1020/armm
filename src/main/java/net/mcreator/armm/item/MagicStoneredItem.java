
package net.mcreator.armm.item;

import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.armm.init.ArmmModTabs;

public class MagicStoneredItem extends Item {
	public MagicStoneredItem() {
		super(new Item.Properties().tab(ArmmModTabs.TAB_ARMM).stacksTo(1).rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 32;
	}
}
