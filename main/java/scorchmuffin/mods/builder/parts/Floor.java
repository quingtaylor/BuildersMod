package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Floor {

	private int currentX, currentY, currentZ;
	private World world;
	private int length;
	private int width;
	private Block floorBlock;
	private Direction direction;
	private BuilderUtils builder;

	public Floor(int x, int y, int z, World world, Direction direction) {
		builder = new BuilderUtils(x, y, z, world, direction);
		currentX = x;
		currentY = y;
		currentZ = z;
		this.world = world;
		floorBlock = Blocks.crafting_table;
		this.direction = direction;
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
				builder.build(x, 0, z, floorBlock);
			}
		}
	}

}
