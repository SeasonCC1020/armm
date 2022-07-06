
package net.mcreator.armm.world.inventory;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.armm.init.ArmmModMenus;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class PackGUIMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;

	public PackGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(ArmmModMenus.PACK_GUI, id);
		this.entity = inv.player;
		this.world = inv.player.level;
		this.internal = new ItemStackHandler(220);
		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
		if (pos != null) {
			if (extraData.readableBytes() == 1) { // bound to item
				byte hand = extraData.readByte();
				ItemStack itemstack;
				if (hand == 0)
					itemstack = this.entity.getMainHandItem();
				else
					itemstack = this.entity.getOffhandItem();
				itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					this.internal = capability;
					this.bound = true;
				});
			} else if (extraData.readableBytes() > 1) {
				extraData.readByte(); // drop padding
				Entity entity = world.getEntity(extraData.readVarInt());
				if (entity != null)
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
			} else { // might be bound to block
				BlockEntity ent = inv.player != null ? inv.player.level.getBlockEntity(pos) : null;
				if (ent != null) {
					ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
				}
			}
		}
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 3, 4) {
		}));
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 21, 4) {
		}));
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 39, 4) {
		}));
		this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 57, 4) {
		}));
		this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 75, 4) {
		}));
		this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 93, 4) {
		}));
		this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 111, 4) {
		}));
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 129, 4) {
		}));
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 147, 4) {
		}));
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 165, 4) {
		}));
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 183, 4) {
		}));
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 201, 4) {
		}));
		this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 219, 4) {
		}));
		this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 237, 4) {
		}));
		this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 255, 4) {
		}));
		this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 273, 4) {
		}));
		this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 291, 4) {
		}));
		this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 309, 4) {
		}));
		this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 327, 4) {
		}));
		this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 345, 4) {
		}));
		this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 363, 4) {
		}));
		this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 381, 4) {
		}));
		this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 399, 4) {
		}));
		this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 3, 22) {
		}));
		this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 21, 22) {
		}));
		this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 39, 22) {
		}));
		this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 57, 22) {
		}));
		this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 75, 22) {
		}));
		this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 93, 22) {
		}));
		this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 111, 22) {
		}));
		this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 129, 22) {
		}));
		this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 147, 22) {
		}));
		this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 165, 22) {
		}));
		this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 183, 22) {
		}));
		this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 201, 22) {
		}));
		this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 219, 22) {
		}));
		this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 237, 22) {
		}));
		this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 255, 22) {
		}));
		this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 273, 22) {
		}));
		this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 291, 22) {
		}));
		this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 309, 22) {
		}));
		this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 327, 22) {
		}));
		this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 345, 22) {
		}));
		this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 363, 22) {
		}));
		this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 381, 22) {
		}));
		this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 399, 22) {
		}));
		this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 3, 40) {
		}));
		this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 21, 40) {
		}));
		this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, 39, 40) {
		}));
		this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, 57, 40) {
		}));
		this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, 75, 40) {
		}));
		this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, 93, 40) {
		}));
		this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, 111, 40) {
		}));
		this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, 129, 40) {
		}));
		this.customSlots.put(54, this.addSlot(new SlotItemHandler(internal, 54, 147, 40) {
		}));
		this.customSlots.put(55, this.addSlot(new SlotItemHandler(internal, 55, 165, 40) {
		}));
		this.customSlots.put(56, this.addSlot(new SlotItemHandler(internal, 56, 183, 40) {
		}));
		this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, 201, 40) {
		}));
		this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, 219, 40) {
		}));
		this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, 237, 40) {
		}));
		this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, 255, 40) {
		}));
		this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, 273, 40) {
		}));
		this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, 291, 40) {
		}));
		this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, 309, 40) {
		}));
		this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, 327, 40) {
		}));
		this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, 345, 40) {
		}));
		this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, 363, 40) {
		}));
		this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, 381, 40) {
		}));
		this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, 399, 40) {
		}));
		this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, 3, 58) {
		}));
		this.customSlots.put(70, this.addSlot(new SlotItemHandler(internal, 70, 21, 58) {
		}));
		this.customSlots.put(71, this.addSlot(new SlotItemHandler(internal, 71, 39, 58) {
		}));
		this.customSlots.put(72, this.addSlot(new SlotItemHandler(internal, 72, 57, 58) {
		}));
		this.customSlots.put(73, this.addSlot(new SlotItemHandler(internal, 73, 75, 58) {
		}));
		this.customSlots.put(74, this.addSlot(new SlotItemHandler(internal, 74, 93, 58) {
		}));
		this.customSlots.put(75, this.addSlot(new SlotItemHandler(internal, 75, 111, 58) {
		}));
		this.customSlots.put(76, this.addSlot(new SlotItemHandler(internal, 76, 129, 58) {
		}));
		this.customSlots.put(77, this.addSlot(new SlotItemHandler(internal, 77, 147, 58) {
		}));
		this.customSlots.put(78, this.addSlot(new SlotItemHandler(internal, 78, 165, 58) {
		}));
		this.customSlots.put(79, this.addSlot(new SlotItemHandler(internal, 79, 183, 58) {
		}));
		this.customSlots.put(80, this.addSlot(new SlotItemHandler(internal, 80, 201, 58) {
		}));
		this.customSlots.put(81, this.addSlot(new SlotItemHandler(internal, 81, 219, 58) {
		}));
		this.customSlots.put(82, this.addSlot(new SlotItemHandler(internal, 82, 237, 58) {
		}));
		this.customSlots.put(83, this.addSlot(new SlotItemHandler(internal, 83, 255, 58) {
		}));
		this.customSlots.put(84, this.addSlot(new SlotItemHandler(internal, 84, 273, 58) {
		}));
		this.customSlots.put(85, this.addSlot(new SlotItemHandler(internal, 85, 291, 58) {
		}));
		this.customSlots.put(86, this.addSlot(new SlotItemHandler(internal, 86, 309, 58) {
		}));
		this.customSlots.put(87, this.addSlot(new SlotItemHandler(internal, 87, 327, 58) {
		}));
		this.customSlots.put(88, this.addSlot(new SlotItemHandler(internal, 88, 345, 58) {
		}));
		this.customSlots.put(89, this.addSlot(new SlotItemHandler(internal, 89, 363, 58) {
		}));
		this.customSlots.put(90, this.addSlot(new SlotItemHandler(internal, 90, 381, 58) {
		}));
		this.customSlots.put(91, this.addSlot(new SlotItemHandler(internal, 91, 399, 58) {
		}));
		this.customSlots.put(92, this.addSlot(new SlotItemHandler(internal, 92, 3, 76) {
		}));
		this.customSlots.put(93, this.addSlot(new SlotItemHandler(internal, 93, 21, 76) {
		}));
		this.customSlots.put(94, this.addSlot(new SlotItemHandler(internal, 94, 39, 76) {
		}));
		this.customSlots.put(95, this.addSlot(new SlotItemHandler(internal, 95, 57, 76) {
		}));
		this.customSlots.put(96, this.addSlot(new SlotItemHandler(internal, 96, 75, 76) {
		}));
		this.customSlots.put(97, this.addSlot(new SlotItemHandler(internal, 97, 93, 76) {
		}));
		this.customSlots.put(98, this.addSlot(new SlotItemHandler(internal, 98, 111, 76) {
		}));
		this.customSlots.put(99, this.addSlot(new SlotItemHandler(internal, 99, 129, 76) {
		}));
		this.customSlots.put(100, this.addSlot(new SlotItemHandler(internal, 100, 147, 76) {
		}));
		this.customSlots.put(101, this.addSlot(new SlotItemHandler(internal, 101, 165, 76) {
		}));
		this.customSlots.put(102, this.addSlot(new SlotItemHandler(internal, 102, 183, 76) {
		}));
		this.customSlots.put(103, this.addSlot(new SlotItemHandler(internal, 103, 201, 76) {
		}));
		this.customSlots.put(104, this.addSlot(new SlotItemHandler(internal, 104, 219, 76) {
		}));
		this.customSlots.put(105, this.addSlot(new SlotItemHandler(internal, 105, 237, 76) {
		}));
		this.customSlots.put(106, this.addSlot(new SlotItemHandler(internal, 106, 255, 76) {
		}));
		this.customSlots.put(107, this.addSlot(new SlotItemHandler(internal, 107, 273, 76) {
		}));
		this.customSlots.put(108, this.addSlot(new SlotItemHandler(internal, 108, 291, 76) {
		}));
		this.customSlots.put(109, this.addSlot(new SlotItemHandler(internal, 109, 309, 76) {
		}));
		this.customSlots.put(110, this.addSlot(new SlotItemHandler(internal, 110, 327, 76) {
		}));
		this.customSlots.put(111, this.addSlot(new SlotItemHandler(internal, 111, 345, 76) {
		}));
		this.customSlots.put(112, this.addSlot(new SlotItemHandler(internal, 112, 363, 76) {
		}));
		this.customSlots.put(113, this.addSlot(new SlotItemHandler(internal, 113, 381, 76) {
		}));
		this.customSlots.put(114, this.addSlot(new SlotItemHandler(internal, 114, 399, 76) {
		}));
		this.customSlots.put(115, this.addSlot(new SlotItemHandler(internal, 115, 3, 94) {
		}));
		this.customSlots.put(116, this.addSlot(new SlotItemHandler(internal, 116, 21, 94) {
		}));
		this.customSlots.put(117, this.addSlot(new SlotItemHandler(internal, 117, 39, 94) {
		}));
		this.customSlots.put(118, this.addSlot(new SlotItemHandler(internal, 118, 57, 94) {
		}));
		this.customSlots.put(119, this.addSlot(new SlotItemHandler(internal, 119, 75, 94) {
		}));
		this.customSlots.put(120, this.addSlot(new SlotItemHandler(internal, 120, 93, 94) {
		}));
		this.customSlots.put(121, this.addSlot(new SlotItemHandler(internal, 121, 111, 94) {
		}));
		this.customSlots.put(122, this.addSlot(new SlotItemHandler(internal, 122, 129, 94) {
		}));
		this.customSlots.put(123, this.addSlot(new SlotItemHandler(internal, 123, 147, 94) {
		}));
		this.customSlots.put(124, this.addSlot(new SlotItemHandler(internal, 124, 165, 94) {
		}));
		this.customSlots.put(125, this.addSlot(new SlotItemHandler(internal, 125, 183, 94) {
		}));
		this.customSlots.put(126, this.addSlot(new SlotItemHandler(internal, 126, 201, 94) {
		}));
		this.customSlots.put(127, this.addSlot(new SlotItemHandler(internal, 127, 219, 94) {
		}));
		this.customSlots.put(128, this.addSlot(new SlotItemHandler(internal, 128, 237, 94) {
		}));
		this.customSlots.put(129, this.addSlot(new SlotItemHandler(internal, 129, 255, 94) {
		}));
		this.customSlots.put(130, this.addSlot(new SlotItemHandler(internal, 130, 273, 94) {
		}));
		this.customSlots.put(131, this.addSlot(new SlotItemHandler(internal, 131, 291, 94) {
		}));
		this.customSlots.put(132, this.addSlot(new SlotItemHandler(internal, 132, 309, 94) {
		}));
		this.customSlots.put(133, this.addSlot(new SlotItemHandler(internal, 133, 327, 94) {
		}));
		this.customSlots.put(134, this.addSlot(new SlotItemHandler(internal, 134, 345, 94) {
		}));
		this.customSlots.put(135, this.addSlot(new SlotItemHandler(internal, 135, 363, 94) {
		}));
		this.customSlots.put(136, this.addSlot(new SlotItemHandler(internal, 136, 381, 94) {
		}));
		this.customSlots.put(137, this.addSlot(new SlotItemHandler(internal, 137, 399, 94) {
		}));
		this.customSlots.put(138, this.addSlot(new SlotItemHandler(internal, 138, 3, 112) {
		}));
		this.customSlots.put(139, this.addSlot(new SlotItemHandler(internal, 139, 21, 112) {
		}));
		this.customSlots.put(140, this.addSlot(new SlotItemHandler(internal, 140, 39, 112) {
		}));
		this.customSlots.put(141, this.addSlot(new SlotItemHandler(internal, 141, 57, 112) {
		}));
		this.customSlots.put(142, this.addSlot(new SlotItemHandler(internal, 142, 75, 112) {
		}));
		this.customSlots.put(143, this.addSlot(new SlotItemHandler(internal, 143, 93, 112) {
		}));
		this.customSlots.put(144, this.addSlot(new SlotItemHandler(internal, 144, 111, 112) {
		}));
		this.customSlots.put(145, this.addSlot(new SlotItemHandler(internal, 145, 129, 112) {
		}));
		this.customSlots.put(146, this.addSlot(new SlotItemHandler(internal, 146, 147, 112) {
		}));
		this.customSlots.put(147, this.addSlot(new SlotItemHandler(internal, 147, 165, 112) {
		}));
		this.customSlots.put(148, this.addSlot(new SlotItemHandler(internal, 148, 183, 112) {
		}));
		this.customSlots.put(149, this.addSlot(new SlotItemHandler(internal, 149, 201, 112) {
		}));
		this.customSlots.put(150, this.addSlot(new SlotItemHandler(internal, 150, 219, 112) {
		}));
		this.customSlots.put(151, this.addSlot(new SlotItemHandler(internal, 151, 237, 112) {
		}));
		this.customSlots.put(152, this.addSlot(new SlotItemHandler(internal, 152, 255, 112) {
		}));
		this.customSlots.put(153, this.addSlot(new SlotItemHandler(internal, 153, 273, 112) {
		}));
		this.customSlots.put(154, this.addSlot(new SlotItemHandler(internal, 154, 291, 112) {
		}));
		this.customSlots.put(155, this.addSlot(new SlotItemHandler(internal, 155, 309, 112) {
		}));
		this.customSlots.put(156, this.addSlot(new SlotItemHandler(internal, 156, 327, 112) {
		}));
		this.customSlots.put(157, this.addSlot(new SlotItemHandler(internal, 157, 345, 112) {
		}));
		this.customSlots.put(158, this.addSlot(new SlotItemHandler(internal, 158, 363, 112) {
		}));
		this.customSlots.put(159, this.addSlot(new SlotItemHandler(internal, 159, 381, 112) {
		}));
		this.customSlots.put(160, this.addSlot(new SlotItemHandler(internal, 160, 399, 112) {
		}));
		this.customSlots.put(161, this.addSlot(new SlotItemHandler(internal, 161, 3, 130) {
		}));
		this.customSlots.put(162, this.addSlot(new SlotItemHandler(internal, 162, 21, 130) {
		}));
		this.customSlots.put(163, this.addSlot(new SlotItemHandler(internal, 163, 39, 130) {
		}));
		this.customSlots.put(164, this.addSlot(new SlotItemHandler(internal, 164, 57, 130) {
		}));
		this.customSlots.put(165, this.addSlot(new SlotItemHandler(internal, 165, 75, 130) {
		}));
		this.customSlots.put(166, this.addSlot(new SlotItemHandler(internal, 166, 93, 130) {
		}));
		this.customSlots.put(167, this.addSlot(new SlotItemHandler(internal, 167, 111, 130) {
		}));
		this.customSlots.put(168, this.addSlot(new SlotItemHandler(internal, 168, 129, 130) {
		}));
		this.customSlots.put(169, this.addSlot(new SlotItemHandler(internal, 169, 147, 130) {
		}));
		this.customSlots.put(170, this.addSlot(new SlotItemHandler(internal, 170, 165, 130) {
		}));
		this.customSlots.put(171, this.addSlot(new SlotItemHandler(internal, 171, 183, 130) {
		}));
		this.customSlots.put(172, this.addSlot(new SlotItemHandler(internal, 172, 201, 130) {
		}));
		this.customSlots.put(173, this.addSlot(new SlotItemHandler(internal, 173, 219, 130) {
		}));
		this.customSlots.put(174, this.addSlot(new SlotItemHandler(internal, 174, 237, 130) {
		}));
		this.customSlots.put(175, this.addSlot(new SlotItemHandler(internal, 175, 255, 130) {
		}));
		this.customSlots.put(176, this.addSlot(new SlotItemHandler(internal, 176, 273, 130) {
		}));
		this.customSlots.put(177, this.addSlot(new SlotItemHandler(internal, 177, 291, 130) {
		}));
		this.customSlots.put(178, this.addSlot(new SlotItemHandler(internal, 178, 309, 130) {
		}));
		this.customSlots.put(179, this.addSlot(new SlotItemHandler(internal, 179, 327, 130) {
		}));
		this.customSlots.put(180, this.addSlot(new SlotItemHandler(internal, 180, 345, 130) {
		}));
		this.customSlots.put(181, this.addSlot(new SlotItemHandler(internal, 181, 363, 130) {
		}));
		this.customSlots.put(182, this.addSlot(new SlotItemHandler(internal, 182, 381, 130) {
		}));
		this.customSlots.put(183, this.addSlot(new SlotItemHandler(internal, 183, 399, 130) {
		}));
		this.customSlots.put(184, this.addSlot(new SlotItemHandler(internal, 184, 3, 148) {
		}));
		this.customSlots.put(185, this.addSlot(new SlotItemHandler(internal, 185, 21, 148) {
		}));
		this.customSlots.put(186, this.addSlot(new SlotItemHandler(internal, 186, 39, 148) {
		}));
		this.customSlots.put(187, this.addSlot(new SlotItemHandler(internal, 187, 57, 148) {
		}));
		this.customSlots.put(188, this.addSlot(new SlotItemHandler(internal, 188, 75, 148) {
		}));
		this.customSlots.put(189, this.addSlot(new SlotItemHandler(internal, 189, 93, 148) {
		}));
		this.customSlots.put(190, this.addSlot(new SlotItemHandler(internal, 190, 309, 148) {
		}));
		this.customSlots.put(191, this.addSlot(new SlotItemHandler(internal, 191, 327, 148) {
		}));
		this.customSlots.put(192, this.addSlot(new SlotItemHandler(internal, 192, 345, 148) {
		}));
		this.customSlots.put(193, this.addSlot(new SlotItemHandler(internal, 193, 363, 148) {
		}));
		this.customSlots.put(194, this.addSlot(new SlotItemHandler(internal, 194, 381, 148) {
		}));
		this.customSlots.put(195, this.addSlot(new SlotItemHandler(internal, 195, 399, 148) {
		}));
		this.customSlots.put(196, this.addSlot(new SlotItemHandler(internal, 196, 3, 166) {
		}));
		this.customSlots.put(197, this.addSlot(new SlotItemHandler(internal, 197, 21, 166) {
		}));
		this.customSlots.put(198, this.addSlot(new SlotItemHandler(internal, 198, 39, 166) {
		}));
		this.customSlots.put(199, this.addSlot(new SlotItemHandler(internal, 199, 57, 166) {
		}));
		this.customSlots.put(200, this.addSlot(new SlotItemHandler(internal, 200, 75, 166) {
		}));
		this.customSlots.put(201, this.addSlot(new SlotItemHandler(internal, 201, 93, 166) {
		}));
		this.customSlots.put(202, this.addSlot(new SlotItemHandler(internal, 202, 309, 166) {
		}));
		this.customSlots.put(203, this.addSlot(new SlotItemHandler(internal, 203, 327, 166) {
		}));
		this.customSlots.put(204, this.addSlot(new SlotItemHandler(internal, 204, 345, 166) {
		}));
		this.customSlots.put(205, this.addSlot(new SlotItemHandler(internal, 205, 363, 166) {
		}));
		this.customSlots.put(206, this.addSlot(new SlotItemHandler(internal, 206, 381, 166) {
		}));
		this.customSlots.put(207, this.addSlot(new SlotItemHandler(internal, 207, 399, 166) {
		}));
		this.customSlots.put(208, this.addSlot(new SlotItemHandler(internal, 208, 3, 184) {
		}));
		this.customSlots.put(209, this.addSlot(new SlotItemHandler(internal, 209, 21, 184) {
		}));
		this.customSlots.put(210, this.addSlot(new SlotItemHandler(internal, 210, 39, 184) {
		}));
		this.customSlots.put(211, this.addSlot(new SlotItemHandler(internal, 211, 57, 184) {
		}));
		this.customSlots.put(212, this.addSlot(new SlotItemHandler(internal, 212, 75, 184) {
		}));
		this.customSlots.put(213, this.addSlot(new SlotItemHandler(internal, 213, 93, 184) {
		}));
		this.customSlots.put(214, this.addSlot(new SlotItemHandler(internal, 214, 309, 184) {
		}));
		this.customSlots.put(215, this.addSlot(new SlotItemHandler(internal, 215, 327, 184) {
		}));
		this.customSlots.put(216, this.addSlot(new SlotItemHandler(internal, 216, 345, 184) {
		}));
		this.customSlots.put(217, this.addSlot(new SlotItemHandler(internal, 217, 363, 184) {
		}));
		this.customSlots.put(218, this.addSlot(new SlotItemHandler(internal, 218, 381, 184) {
		}));
		this.customSlots.put(219, this.addSlot(new SlotItemHandler(internal, 219, 399, 184) {
		}));
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 122 + 8 + sj * 18, 68 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 122 + 8 + si * 18, 68 + 142));
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 220) {
				if (!this.moveItemStackTo(itemstack1, 220, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 220, false)) {
				if (index < 220 + 27) {
					if (!this.moveItemStackTo(itemstack1, 220 + 27, this.slots.size(), true)) {
						return ItemStack.EMPTY;
					}
				} else {
					if (!this.moveItemStackTo(itemstack1, 220, 220 + 27, false)) {
						return ItemStack.EMPTY;
					}
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty()) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
					if (j <= maxSize) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < maxSize) {
						p_38904_.shrink(maxSize - itemstack.getCount());
						itemstack.setCount(maxSize);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (true) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					if (p_38904_.getCount() > slot1.getMaxStackSize()) {
						slot1.set(p_38904_.split(slot1.getMaxStackSize()));
					} else {
						slot1.set(p_38904_.split(p_38904_.getCount()));
					}
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					if (j == 0)
						continue;
					if (j == 1)
						continue;
					if (j == 2)
						continue;
					if (j == 3)
						continue;
					if (j == 4)
						continue;
					if (j == 5)
						continue;
					if (j == 6)
						continue;
					if (j == 7)
						continue;
					if (j == 8)
						continue;
					if (j == 9)
						continue;
					if (j == 10)
						continue;
					if (j == 11)
						continue;
					if (j == 12)
						continue;
					if (j == 13)
						continue;
					if (j == 14)
						continue;
					if (j == 15)
						continue;
					if (j == 16)
						continue;
					if (j == 17)
						continue;
					if (j == 18)
						continue;
					if (j == 19)
						continue;
					if (j == 20)
						continue;
					if (j == 21)
						continue;
					if (j == 22)
						continue;
					if (j == 23)
						continue;
					if (j == 24)
						continue;
					if (j == 25)
						continue;
					if (j == 26)
						continue;
					if (j == 27)
						continue;
					if (j == 28)
						continue;
					if (j == 29)
						continue;
					if (j == 30)
						continue;
					if (j == 31)
						continue;
					if (j == 32)
						continue;
					if (j == 33)
						continue;
					if (j == 34)
						continue;
					if (j == 35)
						continue;
					if (j == 36)
						continue;
					if (j == 37)
						continue;
					if (j == 38)
						continue;
					if (j == 39)
						continue;
					if (j == 40)
						continue;
					if (j == 41)
						continue;
					if (j == 42)
						continue;
					if (j == 43)
						continue;
					if (j == 44)
						continue;
					if (j == 45)
						continue;
					if (j == 46)
						continue;
					if (j == 47)
						continue;
					if (j == 48)
						continue;
					if (j == 49)
						continue;
					if (j == 50)
						continue;
					if (j == 51)
						continue;
					if (j == 52)
						continue;
					if (j == 53)
						continue;
					if (j == 54)
						continue;
					if (j == 55)
						continue;
					if (j == 56)
						continue;
					if (j == 57)
						continue;
					if (j == 58)
						continue;
					if (j == 59)
						continue;
					if (j == 60)
						continue;
					if (j == 61)
						continue;
					if (j == 62)
						continue;
					if (j == 63)
						continue;
					if (j == 64)
						continue;
					if (j == 65)
						continue;
					if (j == 66)
						continue;
					if (j == 67)
						continue;
					if (j == 68)
						continue;
					if (j == 69)
						continue;
					if (j == 70)
						continue;
					if (j == 71)
						continue;
					if (j == 72)
						continue;
					if (j == 73)
						continue;
					if (j == 74)
						continue;
					if (j == 75)
						continue;
					if (j == 76)
						continue;
					if (j == 77)
						continue;
					if (j == 78)
						continue;
					if (j == 79)
						continue;
					if (j == 80)
						continue;
					if (j == 81)
						continue;
					if (j == 82)
						continue;
					if (j == 83)
						continue;
					if (j == 84)
						continue;
					if (j == 85)
						continue;
					if (j == 86)
						continue;
					if (j == 87)
						continue;
					if (j == 88)
						continue;
					if (j == 89)
						continue;
					if (j == 90)
						continue;
					if (j == 91)
						continue;
					if (j == 92)
						continue;
					if (j == 93)
						continue;
					if (j == 94)
						continue;
					if (j == 95)
						continue;
					if (j == 96)
						continue;
					if (j == 97)
						continue;
					if (j == 98)
						continue;
					if (j == 99)
						continue;
					if (j == 100)
						continue;
					if (j == 101)
						continue;
					if (j == 102)
						continue;
					if (j == 103)
						continue;
					if (j == 104)
						continue;
					if (j == 105)
						continue;
					if (j == 106)
						continue;
					if (j == 107)
						continue;
					if (j == 108)
						continue;
					if (j == 109)
						continue;
					if (j == 110)
						continue;
					if (j == 111)
						continue;
					if (j == 112)
						continue;
					if (j == 113)
						continue;
					if (j == 114)
						continue;
					if (j == 115)
						continue;
					if (j == 116)
						continue;
					if (j == 117)
						continue;
					if (j == 118)
						continue;
					if (j == 119)
						continue;
					if (j == 120)
						continue;
					if (j == 121)
						continue;
					if (j == 122)
						continue;
					if (j == 123)
						continue;
					if (j == 124)
						continue;
					if (j == 125)
						continue;
					if (j == 126)
						continue;
					if (j == 127)
						continue;
					if (j == 128)
						continue;
					if (j == 129)
						continue;
					if (j == 130)
						continue;
					if (j == 131)
						continue;
					if (j == 132)
						continue;
					if (j == 133)
						continue;
					if (j == 134)
						continue;
					if (j == 135)
						continue;
					if (j == 136)
						continue;
					if (j == 137)
						continue;
					if (j == 138)
						continue;
					if (j == 139)
						continue;
					if (j == 140)
						continue;
					if (j == 141)
						continue;
					if (j == 142)
						continue;
					if (j == 143)
						continue;
					if (j == 144)
						continue;
					if (j == 145)
						continue;
					if (j == 146)
						continue;
					if (j == 147)
						continue;
					if (j == 148)
						continue;
					if (j == 149)
						continue;
					if (j == 150)
						continue;
					if (j == 151)
						continue;
					if (j == 152)
						continue;
					if (j == 153)
						continue;
					if (j == 154)
						continue;
					if (j == 155)
						continue;
					if (j == 156)
						continue;
					if (j == 157)
						continue;
					if (j == 158)
						continue;
					if (j == 159)
						continue;
					if (j == 160)
						continue;
					if (j == 161)
						continue;
					if (j == 162)
						continue;
					if (j == 163)
						continue;
					if (j == 164)
						continue;
					if (j == 165)
						continue;
					if (j == 166)
						continue;
					if (j == 167)
						continue;
					if (j == 168)
						continue;
					if (j == 169)
						continue;
					if (j == 170)
						continue;
					if (j == 171)
						continue;
					if (j == 172)
						continue;
					if (j == 173)
						continue;
					if (j == 174)
						continue;
					if (j == 175)
						continue;
					if (j == 176)
						continue;
					if (j == 177)
						continue;
					if (j == 178)
						continue;
					if (j == 179)
						continue;
					if (j == 180)
						continue;
					if (j == 181)
						continue;
					if (j == 182)
						continue;
					if (j == 183)
						continue;
					if (j == 184)
						continue;
					if (j == 185)
						continue;
					if (j == 186)
						continue;
					if (j == 187)
						continue;
					if (j == 188)
						continue;
					if (j == 189)
						continue;
					if (j == 190)
						continue;
					if (j == 191)
						continue;
					if (j == 192)
						continue;
					if (j == 193)
						continue;
					if (j == 194)
						continue;
					if (j == 195)
						continue;
					if (j == 196)
						continue;
					if (j == 197)
						continue;
					if (j == 198)
						continue;
					if (j == 199)
						continue;
					if (j == 200)
						continue;
					if (j == 201)
						continue;
					if (j == 202)
						continue;
					if (j == 203)
						continue;
					if (j == 204)
						continue;
					if (j == 205)
						continue;
					if (j == 206)
						continue;
					if (j == 207)
						continue;
					if (j == 208)
						continue;
					if (j == 209)
						continue;
					if (j == 210)
						continue;
					if (j == 211)
						continue;
					if (j == 212)
						continue;
					if (j == 213)
						continue;
					if (j == 214)
						continue;
					if (j == 215)
						continue;
					if (j == 216)
						continue;
					if (j == 217)
						continue;
					if (j == 218)
						continue;
					if (j == 219)
						continue;
					playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					if (i == 0)
						continue;
					if (i == 1)
						continue;
					if (i == 2)
						continue;
					if (i == 3)
						continue;
					if (i == 4)
						continue;
					if (i == 5)
						continue;
					if (i == 6)
						continue;
					if (i == 7)
						continue;
					if (i == 8)
						continue;
					if (i == 9)
						continue;
					if (i == 10)
						continue;
					if (i == 11)
						continue;
					if (i == 12)
						continue;
					if (i == 13)
						continue;
					if (i == 14)
						continue;
					if (i == 15)
						continue;
					if (i == 16)
						continue;
					if (i == 17)
						continue;
					if (i == 18)
						continue;
					if (i == 19)
						continue;
					if (i == 20)
						continue;
					if (i == 21)
						continue;
					if (i == 22)
						continue;
					if (i == 23)
						continue;
					if (i == 24)
						continue;
					if (i == 25)
						continue;
					if (i == 26)
						continue;
					if (i == 27)
						continue;
					if (i == 28)
						continue;
					if (i == 29)
						continue;
					if (i == 30)
						continue;
					if (i == 31)
						continue;
					if (i == 32)
						continue;
					if (i == 33)
						continue;
					if (i == 34)
						continue;
					if (i == 35)
						continue;
					if (i == 36)
						continue;
					if (i == 37)
						continue;
					if (i == 38)
						continue;
					if (i == 39)
						continue;
					if (i == 40)
						continue;
					if (i == 41)
						continue;
					if (i == 42)
						continue;
					if (i == 43)
						continue;
					if (i == 44)
						continue;
					if (i == 45)
						continue;
					if (i == 46)
						continue;
					if (i == 47)
						continue;
					if (i == 48)
						continue;
					if (i == 49)
						continue;
					if (i == 50)
						continue;
					if (i == 51)
						continue;
					if (i == 52)
						continue;
					if (i == 53)
						continue;
					if (i == 54)
						continue;
					if (i == 55)
						continue;
					if (i == 56)
						continue;
					if (i == 57)
						continue;
					if (i == 58)
						continue;
					if (i == 59)
						continue;
					if (i == 60)
						continue;
					if (i == 61)
						continue;
					if (i == 62)
						continue;
					if (i == 63)
						continue;
					if (i == 64)
						continue;
					if (i == 65)
						continue;
					if (i == 66)
						continue;
					if (i == 67)
						continue;
					if (i == 68)
						continue;
					if (i == 69)
						continue;
					if (i == 70)
						continue;
					if (i == 71)
						continue;
					if (i == 72)
						continue;
					if (i == 73)
						continue;
					if (i == 74)
						continue;
					if (i == 75)
						continue;
					if (i == 76)
						continue;
					if (i == 77)
						continue;
					if (i == 78)
						continue;
					if (i == 79)
						continue;
					if (i == 80)
						continue;
					if (i == 81)
						continue;
					if (i == 82)
						continue;
					if (i == 83)
						continue;
					if (i == 84)
						continue;
					if (i == 85)
						continue;
					if (i == 86)
						continue;
					if (i == 87)
						continue;
					if (i == 88)
						continue;
					if (i == 89)
						continue;
					if (i == 90)
						continue;
					if (i == 91)
						continue;
					if (i == 92)
						continue;
					if (i == 93)
						continue;
					if (i == 94)
						continue;
					if (i == 95)
						continue;
					if (i == 96)
						continue;
					if (i == 97)
						continue;
					if (i == 98)
						continue;
					if (i == 99)
						continue;
					if (i == 100)
						continue;
					if (i == 101)
						continue;
					if (i == 102)
						continue;
					if (i == 103)
						continue;
					if (i == 104)
						continue;
					if (i == 105)
						continue;
					if (i == 106)
						continue;
					if (i == 107)
						continue;
					if (i == 108)
						continue;
					if (i == 109)
						continue;
					if (i == 110)
						continue;
					if (i == 111)
						continue;
					if (i == 112)
						continue;
					if (i == 113)
						continue;
					if (i == 114)
						continue;
					if (i == 115)
						continue;
					if (i == 116)
						continue;
					if (i == 117)
						continue;
					if (i == 118)
						continue;
					if (i == 119)
						continue;
					if (i == 120)
						continue;
					if (i == 121)
						continue;
					if (i == 122)
						continue;
					if (i == 123)
						continue;
					if (i == 124)
						continue;
					if (i == 125)
						continue;
					if (i == 126)
						continue;
					if (i == 127)
						continue;
					if (i == 128)
						continue;
					if (i == 129)
						continue;
					if (i == 130)
						continue;
					if (i == 131)
						continue;
					if (i == 132)
						continue;
					if (i == 133)
						continue;
					if (i == 134)
						continue;
					if (i == 135)
						continue;
					if (i == 136)
						continue;
					if (i == 137)
						continue;
					if (i == 138)
						continue;
					if (i == 139)
						continue;
					if (i == 140)
						continue;
					if (i == 141)
						continue;
					if (i == 142)
						continue;
					if (i == 143)
						continue;
					if (i == 144)
						continue;
					if (i == 145)
						continue;
					if (i == 146)
						continue;
					if (i == 147)
						continue;
					if (i == 148)
						continue;
					if (i == 149)
						continue;
					if (i == 150)
						continue;
					if (i == 151)
						continue;
					if (i == 152)
						continue;
					if (i == 153)
						continue;
					if (i == 154)
						continue;
					if (i == 155)
						continue;
					if (i == 156)
						continue;
					if (i == 157)
						continue;
					if (i == 158)
						continue;
					if (i == 159)
						continue;
					if (i == 160)
						continue;
					if (i == 161)
						continue;
					if (i == 162)
						continue;
					if (i == 163)
						continue;
					if (i == 164)
						continue;
					if (i == 165)
						continue;
					if (i == 166)
						continue;
					if (i == 167)
						continue;
					if (i == 168)
						continue;
					if (i == 169)
						continue;
					if (i == 170)
						continue;
					if (i == 171)
						continue;
					if (i == 172)
						continue;
					if (i == 173)
						continue;
					if (i == 174)
						continue;
					if (i == 175)
						continue;
					if (i == 176)
						continue;
					if (i == 177)
						continue;
					if (i == 178)
						continue;
					if (i == 179)
						continue;
					if (i == 180)
						continue;
					if (i == 181)
						continue;
					if (i == 182)
						continue;
					if (i == 183)
						continue;
					if (i == 184)
						continue;
					if (i == 185)
						continue;
					if (i == 186)
						continue;
					if (i == 187)
						continue;
					if (i == 188)
						continue;
					if (i == 189)
						continue;
					if (i == 190)
						continue;
					if (i == 191)
						continue;
					if (i == 192)
						continue;
					if (i == 193)
						continue;
					if (i == 194)
						continue;
					if (i == 195)
						continue;
					if (i == 196)
						continue;
					if (i == 197)
						continue;
					if (i == 198)
						continue;
					if (i == 199)
						continue;
					if (i == 200)
						continue;
					if (i == 201)
						continue;
					if (i == 202)
						continue;
					if (i == 203)
						continue;
					if (i == 204)
						continue;
					if (i == 205)
						continue;
					if (i == 206)
						continue;
					if (i == 207)
						continue;
					if (i == 208)
						continue;
					if (i == 209)
						continue;
					if (i == 210)
						continue;
					if (i == 211)
						continue;
					if (i == 212)
						continue;
					if (i == 213)
						continue;
					if (i == 214)
						continue;
					if (i == 215)
						continue;
					if (i == 216)
						continue;
					if (i == 217)
						continue;
					if (i == 218)
						continue;
					if (i == 219)
						continue;
					playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
				}
			}
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}
}
