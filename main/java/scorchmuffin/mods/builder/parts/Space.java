package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
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
	private BuilderUtils builder;

	public Space(int x, int y, int z, World world, Direction direction) {
		builder = new BuilderUtils(x, y, z, world, direction);
		currentX = x;
		currentY = y;
		currentZ = z;
		this.world = world;
		spaceBlock = Blocks.air;
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
					builder.build(x, y, z, spaceBlock);
				}
			}
		}
	}

}
