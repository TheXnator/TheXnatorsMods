package thexnator.computermod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.Reference;
import thexnator.computermod.items.ItemPlacer;

public class ComputerItems {
	
	public static Item laptopitem;

	public static void init() {
		laptopitem = new ItemPlacer(ComputerBlocks.laptop).setUnlocalizedName("laptopitem").setCreativeTab(ComputerMod.tabComputer);
	}
	
	public static void register()
	{
		
	}
	
	public static void registerRenders()
	{
		
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
