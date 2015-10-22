package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Floor {

	private int currentX, currentY, currentZ;
	private World world;
	private int length;
	private int width;
	private Block floorBlock;
	private boolean positive;

	public Floor(int x, int y, int z, World world, boolean positive) {
		currentX = x;
		currentY = y;
		currentZ = z;
		this.world = world;
		floorBlock = Blocks.crafting_table;
		this.positive = positive;
	}

	public void setBlock(Block floorBlock) {
		this.floorBlock = floorBlock;
	}
	
	public void setSize(int width, int length) {
		this.length = length;
		this.width = width;
	}

	public void build() {
		
		for (int x = 0; x < width; x++) {
			for (int z = 0; z < length; z++) {
				if (positive)
					world.setBlock(currentX + x, currentY, currentZ + z, floorBlock);
				else
					world.setBlock(currentX - x, currentY, currentZ - z, floorBlock);
			}
		}
	}

}
