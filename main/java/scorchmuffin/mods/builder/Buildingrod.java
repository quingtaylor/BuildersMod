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

		// buildFloor(world, x, y, z);
		// buildWall(world, x, y, z);
		buildRoom(world, x, y, z);

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

	private void buildWallX(World world, int x, int y, int z) {
		int maxX = x + 8;
		int maxY = y + 5;
		int maxZ = z;

		for (int i = x; i <= maxX; i++) {
			for (int k = y; k <= maxY; k++) {
				world.setBlock(i, k, z, getBlock(i, k, z));
			}
		}
	}

	private void buildWallZ(World world, int x, int y, int z) {
		int maxX = x;
		int maxY = y + 5;
		int maxZ = z + 8;

		for (int i = z; i <= maxZ; i++) {
			for (int k = y; k <= maxY; k++) {
				world.setBlock(x, k, i, getBlock(x, k, i));
			}
		}
	}

	private void buildRoom(World world, int x, int y, int z) {
		buildFloor(world, x, y, z);
		buildWallX(world, x, y, z);
		buildWallX(world, x, y, z + 8);
		buildFloor(world, x, y + 5, z);
		buildWallZ(world, x, y, z);
		buildWallZ(world, x + 8, y, z);
	}

	// private void buildFloor2(World world, int x, int y, int z) {
	// int maxX = x + 8;
	// int maxY = y;
	// int maxZ = z + 8;
	//
	// for (int i = x; i <= maxX; i = i + 4) {
	// for (int k = z; k <= maxZ; k++) {
	// world.setBlock(i, y, k, Blocks.glowstone);
	// world.setBlock(i + 1, y, k, Blocks.brick_block);
	// world.setBlock(i + 2, y, k, Blocks.emerald_ore);
	// world.setBlock(i + 3, y, k, Blocks.brick_block);
	// }
	// }
	// }

	private Block getBlock(int x, int y, int z) {
		if (x % 4 == 0)
			return Blocks.glowstone;
		else if (x % 4 == 1 || x % 4 == 3)
			return Blocks.stone;
		else
			return Blocks.stonebrick;
	}
}