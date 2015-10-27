package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WallX extends Wall {

	public WallX(int x, int y, int z, World world, Direction direction) {
		super(x, y, z, world, Wall.Axis.AXIS_X, new BuilderUtils(x, y, z, world, direction));
		setIncludeWindow(true);
	}
}
