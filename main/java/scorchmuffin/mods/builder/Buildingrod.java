package scorchmuffin.mods.builder;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Buildingrod extends Item {
	public static final String NAME = "BuildingRod";

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {

		buildFloor(world, x, y, z);

		player.inventory.consumeInventoryItem(this);
		return true;
	}

	private void buildFloor(World world, int x, int y, int z) {
		int maxX = x + 8;
		int maxY = y;
		int maxZ = z + 8;

		for (int i = x; i <= maxX; i++) {
			for (int k = z; k <= maxZ; k++) {
				world.setBlock(i, y, k, getBlock(i, y, k));
			}
		}
	}

//	private void buildFloor2(World world, int x, int y, int z) {
//		int maxX = x + 8;
//		int maxY = y;
//		int maxZ = z + 8;
//
//		for (int i = x; i <= maxX; i = i + 4) {
//			for (int k = z; k <= maxZ; k++) {
//				world.setBlock(i, y, k, Blocks.glowstone);
//				world.setBlock(i + 1, y, k, Blocks.brick_block);
//				world.setBlock(i + 2, y, k, Blocks.emerald_ore);
//				world.setBlock(i + 3, y, k, Blocks.brick_block);
//			}
//		}
//	}

	private Block getBlock(int x, int y, int z) {
		if (x % 4 == 0)
			return Blocks.glowstone;
		else if (x % 4 == 1 || x % 4 == 3)
			return Blocks.stone;
		else
			return Blocks.stonebrick;
	}
}
