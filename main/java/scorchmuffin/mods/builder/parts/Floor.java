package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Floor extends Wall {

	public Floor(int x, int y, int z, World world) {
		super(x, z, y, world, Wall.Axis.AXIS_Y);
	}
}
