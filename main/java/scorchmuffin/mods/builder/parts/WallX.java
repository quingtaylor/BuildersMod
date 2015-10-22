package scorchmuffin.mods.builder.parts;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WallX extends Wall {

	public WallX(int x, int y, int z, World world, boolean positive) {
		super(x, y, z, world, positive ? Wall.Axis.AXIS_X : Wall.Axis.AXIS_M_X);
		setIncludeWindow(true);
	}
}
