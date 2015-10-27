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
	private BuilderUtils builder;

	public Room(int x, int y, int z, World world, Direction direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
		this.direction = direction;
		this.builder = new BuilderUtils(x, y, z, world, direction);
	}

	public void setSize(int width, int height, int length) {
		this.height = height;
		this.length = length;
		this.width = width;
	}

	public void build() {
		Floor floor = new Floor(x, y, z, world, direction);
		floor.setSize(width, length);
		floor.build();
		Floor ceiling = new Floor(x, y + height - 1, z, world, direction);
		ceiling.setSize(width, length);
		ceiling.setBlock(Blocks.glowstone);
		ceiling.build();
		
		BuilderUtils b = builder;
		
		WallX l = new WallX(x, b.addY(y, 1), z, world, direction);
		l.setSize(width, height - 2);
		l.build();
		int nextZ = b.subZ(b.addZ(z, length), 1);
		WallX r = new WallX(x, b.addY(y, 1), nextZ, world, direction);
		r.setSize(width, height - 2);
		r.build();
		WallZ f = new WallZ(x, b.addY(y, 1), z, world, direction);
		f.setSize(length, height - 2);
		f.build();
		int nextX = b.subX(b.addX(x, width), 1);
		WallZ zb = new WallZ(nextX, b.addY(y, 1), z, world, direction);
		zb.setSize(length, height - 2);
		zb.build();
		
		Space s = new Space(b.addX(x, 1), b.addY(y, 1), b.addZ(z, 1), world, direction);
		s.setSize(width - 2, height - 2, length - 2);
		s.build();
	}
}
