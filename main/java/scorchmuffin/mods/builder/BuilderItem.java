package scorchmuffin.mods.builder;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BuilderItem extends Item {

	public static final String NAME = "BuildingBuilder";

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {

		int maxX = x + 5;
		int maxY = y + 8;
		int maxZ = z + 10;

		for (int i = x; i < maxX; i++) {
			for (int j = y; j < maxY; j++) {
				world.setBlock(i, j, z, Blocks.cobblestone);
				world.setBlock(i, j, maxZ, Blocks.cobblestone);
			}
		}

		for (int j = y; j < maxY; j++) {
			for (int k = z; k < maxZ; k++) {
				world.setBlock(x, j, k, Blocks.cobblestone);
				world.setBlock(maxX, j, k, Blocks.cobblestone);
			}
		}

		for (int i = x; i < maxX; i++) {
			for (int k = z; k < maxZ; k++) {
				world.setBlock(i, y, k, getBlock(i, y, k));
				world.setBlock(i, maxY, k, getBlock(i, y, k));
			}
		}
		return true;
	}

	private Block getBlock(int x, int y, int z) {
		if (x % 2 == 0)
			return Blocks.cobblestone;
		else
			return Blocks.red_mushroom_block;
	}

}
