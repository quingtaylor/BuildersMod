package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Room {

	private int z;
	private int x;
	private int y;
	private World world;
	private int height;
	private int length;
	private int width;
	private Direction direction;
	private boolean positive;

	public Room(int x, int y, int z, World world, Direction direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
		this.direction = direction;
		this.positive = direction == Direction.NORTH || direction == Direction.EAST;
	}

	public void setSize(int width, int height, int length) {
		this.height = height;
		this.length = length;
		this.width = width;
	}

	public void build() {
		Floor floor = new Floor(x, y, z, world, positive);
		floor.setSize(width, length);
		floor.build();
		Floor ceiling = new Floor(x, y + height - 1, z, world, positive);
		ceiling.setSize(width, length);
		ceiling.setBlock(Blocks.glowstone);
		ceiling.build();
		
		if (positive)
			plus();
		else
			minus();
	}

	private void plus() {

		WallX l = new WallX(x, y + 1, z, world, positive);
		l.setSize(width, height - 2);
		l.build();
		WallX r = new WallX(x, y + 1, z + length - 1, world, positive);
		r.setSize(width, height - 2);
		r.build();
		WallZ f = new WallZ(x, y + 1, z, world, positive);
		f.setSize(length, height - 2);
		f.build();
		WallZ b = new WallZ(x + width - 1, y + 1, z, world, positive);
		b.setSize(length, height - 2);
		b.build();
		Space s = new Space(x + 1, y + 1, z + 1, world, positive);
		s.setSize(width - 2, height - 2, length - 2);
		s.build();
	}

	private void minus() {

		WallX l = new WallX(x, y + 1, z, world, positive);
		l.setSize(width, height - 2);
		l.build();
		WallX r = new WallX(x, y + 1, z - length + 1, world, positive);
		r.setSize(width, height - 2);
		r.build();
		WallZ f = new WallZ(x, y + 1, z, world, positive);
		f.setSize(length, height - 2);
		f.build();
		WallZ b = new WallZ(x - width + 1, y + 1, z, world, positive);
		b.setSize(length, height - 2);
		b.build();
		Space s = new Space(x - 1, y + 1, z - 1, world, positive);
		s.setSize(width - 2, height - 2, length - 2);
		s.build();
	}
}
