package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BuilderUtils {

	private int x, y, z;
	private World world;
	private Direction direction;

	public BuilderUtils(int x, int y, int z, World w, Direction d) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = w;
		this.direction = d;
	}

	/**
	 * If building in the north direction,
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param b
	 * @param world
	 * @param direction
	 */
	void build(int offsetX, int offsetY, int offsetZ, Block b) {
		world.setBlock(addX(x , offsetX), addY(y, offsetY), addZ(z, offsetZ), b);
	}

	int addZ(int z, int offset) {
		switch (direction) {
		case SOUTH:
		case EAST:
			return z + offset;
		case NORTH:
		case WEST:
			return z - offset;
		default:
			return z - offset;
		}
	}

	int subZ(int z, int offset) {
		switch (direction) {
		case SOUTH:
		case EAST:
			return z - offset;
		case NORTH:
		case WEST:
			return z + offset;
		default:
			return z - offset;
		}
	}
	
	int addY(int y, int offset) {
		return y + offset;
	}

	int subY(int y, int offset) {
		return y - offset;
	}

	int addX(int x, int offset) {
		switch (direction) {
		case NORTH:
		case EAST:
			return x + offset;
		case SOUTH:
		case WEST:
			return x - offset;
		default:
			return x + offset;
		}
	}

	int subX(int x, int offset) {
		switch (direction) {
		case NORTH:
		case EAST:
			return x - offset;
		case SOUTH:
		case WEST:
			return x + offset;
		default:
			return x - offset;
		}
	}

}
