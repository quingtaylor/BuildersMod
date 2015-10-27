package scorchmuffin.mods.builder;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ScorchUtils {

	public enum Direction {
		SOUTH, NORTH, EAST, WEST
	};

	/**
	 * gets the way this piston should face for that entity that placed it.
	 */
	public static int determineOrientation(World world, int xPlace, int yPlace,
			int zPlace, EntityLivingBase player) {
		if (MathHelper.abs((float) player.posX - (float) xPlace) < 2.0F
				&& MathHelper.abs((float) player.posZ - (float) zPlace) < 2.0F) {
			double d0 = player.posY + 1.82D - (double) player.yOffset;

			if (d0 - (double) yPlace > 2.0D) {
				return 1;
			}

			if ((double) yPlace - d0 > 0.0D) {
				return 0;
			}
		}

		int l = MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
	}

	public static Direction getPlayerDirection(EntityPlayer p) {
		int var24 = MathHelper
				.floor_double((double) (p.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		switch (var24) {
		case 0:
			return Direction.SOUTH;
		case 1:
			return Direction.WEST;
		case 2:
			return Direction.NORTH;
		case 3:
			return Direction.EAST;
		default:
			return Direction.NORTH;
		}
	}

}
