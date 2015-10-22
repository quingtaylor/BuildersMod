package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Space {

	private int currentX, currentY, currentZ;
	private World world;
	private int height;
	private int width;
	private Block spaceBlock;
	private int length;
	private boolean positive;

	public Space(int x, int y, int z, World world, boolean positive) {
		currentX = x;
		currentY = y;
		currentZ = z;
		this.world = world;
		spaceBlock = Blocks.air;
		this.positive = positive;
	}

	public void setSize(int width, int height, int length) {
		this.height = height;
		this.length = length;
		this.width = width;
	}

	/**
	 * fill the inside of the block with space (remove existing blocks)
	 * 
	 */
	public void build() {

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					if (positive)
						world.setBlock(currentX + x, currentY + y,
								currentZ + z, spaceBlock);
					else
						world.setBlock(currentX - x, currentY + y,
								currentZ - z, spaceBlock);
				}
			}
		}
	}

}
