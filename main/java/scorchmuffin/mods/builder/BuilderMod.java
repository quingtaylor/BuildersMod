package scorchmuffin.mods.builder;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = BuilderMod.MODID, version = BuilderMod.VERSION, name = BuilderMod.NAME)
public class BuilderMod {
	public static final String MODID = "ScorchMod";
	public static final String NAME = "ScorchMod";
	public static final String VERSION = "1.0";

	@Mod.Instance(MODID)
	public static BuilderMod instance;

	private static final BuilderItem BUILDER_ITEM = new BuilderItem();
	private static final Buildingrod Building_Rod = new Buildingrod();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		/*
		 * Item/Block init and registering, plus Config handling
		 */
		BUILDER_ITEM.setUnlocalizedName(BuilderItem.NAME).setCreativeTab(tabBuildermod);
		Building_Rod.setUnlocalizedName(Buildingrod.NAME).setCreativeTab(tabBuildermod);
		GameRegistry.registerItem(BUILDER_ITEM, BuilderItem.NAME);
		GameRegistry.registerItem(Building_Rod, Buildingrod.NAME);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(BUILDER_ITEM),
				new Object[] { "WWW", "WSW", "WWW", 'W', Blocks.planks, 'S', Items.stick });
		GameRegistry.addRecipe(new ItemStack(Building_Rod), new Object[] { "BBB", "SSS", "CCC", 'B', Blocks.brick_block,
				'S', Blocks.stonebrick, 'C', Blocks.cobblestone });
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		/*
		 * Proxy, TileEntity, entity, GUI and Packet Registering
		 */
	}

	public static CreativeTabs tabBuildermod = new CreativeTabs("tabBuildermod") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(BUILDER_ITEM).getItem();
		}
	};
}
