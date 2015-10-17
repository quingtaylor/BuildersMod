package scorchmuffin.mods.builder;

import scorchmuffin.mods.builder.parts.Wall;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BuilderItem extends Item {

	public static final String NAME = "BuildingBuilder";

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {

		// buildSquare(world, x, y, z);
		// buildStairs(world, x, y, z);
		Wall l = new Wall(world, x, y, z);
		l.setSize(10, 50);
		l.build();
		Wall r = new Wall(world, x, y, z + 50);
		r.setSize(10, 50);
		r.build();

		player.inventory.consumeInventoryItem(this);
		return true;
	}
	

	private void buildStairs(World world, int x, int y, int z) {
		int maxY = y + 100;
		int i = x;
		int k = z;
		for (int j = y; j <= maxY; j++) {
			world.setBlock(i, j, k, Blocks.brick_block);

			if (j % 4 == 0) {
				i++;
			} else if (j % 4 == 1) {
				k++;
			} else if (j % 4 == 2) {
				i--;
			} else if (j % 4 == 3) {
				world.setBlock(i + 1, j, k, Blocks.torch);
				k--;
			}
			if (j % 36 == 0) {
				world.setBlock(i + 1, j + 1, k, Blocks.lava);
			}
			if (j % 42 == 0) {
				world.setBlock(i + 1, j + 1, k, Blocks.water);
			}
		}
	}

	private void buildSquare(World world, int x, int y, int z) {
		int maxX = x + 9;
		int maxY = y + 15;
		int maxZ = z + 9;

		for (int i = x; i <= maxX; i++) {
			for (int j = y; j <= maxY; j++) {
				world.setBlock(i, j, z, Blocks.stone);
				world.setBlock(i, j, maxZ, Blocks.stone);
			}
		}

		for (int j = y; j <= maxY; j++) {
			for (int k = z; k <= maxZ; k++) {
				world.setBlock(x, j, k, Blocks.stone);
				world.setBlock(maxX, j, k, Blocks.stone);
			}
		}

		for (int i = x; i <= maxX; i++) {
			for (int k = z; k <= maxZ; k++) {
				world.setBlock(i, y, k, getBlock(i, y, k));
				world.setBlock(i, maxY, k, getBlock(i, y, k));
			}
		}
	}

	private Block getBlock(int x, int y, int z) {
		if (x % 5 == 0)
			return Blocks.diamond_block;
		else if (x % 2 == 0)
			return Blocks.stone;
		else
			return Blocks.glowstone;
	}

}
