package scorchmuffin.mods.builder;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = BuilderMod.MODID, version = BuilderMod.VERSION, name = BuilderMod.NAME)
public class BuilderMod {
	public static final String MODID = "ScorchMod";
	public static final String NAME = "ScorchMod";
	public static final String VERSION = "1.0";

	@Mod.Instance(MODID)
	public static BuilderMod instance;

	private static final BuilderItem BUILDER_ITEM = new BuilderItem();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		/*
		 * Item/Block init and registering, plus Config handling
		 */
		BUILDER_ITEM.setUnlocalizedName(BuilderItem.NAME);
		GameRegistry.registerItem(BUILDER_ITEM, BuilderItem.NAME);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		/*
		 * Proxy, TileEntity, entity, GUI and Packet Registering
		 */
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}
}
