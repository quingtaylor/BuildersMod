package scorchmuffin.mods.builder.parts;

import scorchmuffin.mods.builder.ScorchUtils.Direction;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WallZ extends Wall {

	public WallZ(int x, int y, int z, World world, Direction direction ) {
		super(z, y, x, world, Wall.Axis.AXIS_Z, new BuilderUtils(x, y, z, world, direction));
		setIncludeWindow(true);
	}
}
