package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WallZ extends Wall {

	public WallZ(int x, int y, int z, World world) {
		super(z, y, x, world, Wall.Axis.AXIS_Z);
		setIncludeWindow(true);
	}
}
