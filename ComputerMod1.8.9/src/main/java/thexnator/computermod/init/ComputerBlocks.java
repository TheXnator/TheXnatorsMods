package thexnator.computermod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.Reference;
import thexnator.computermod.blocks.BlockLaptop;

public class ComputerBlocks {
	
	public static Block laptop;

	public static void init()
	{
		laptop = new BlockLaptop(Material.iron).setUnlocalizedName("laptop").setCreativeTab(ComputerMod.tabComputer);
	}
	
	public static void register()
	{
		GameRegistry.registerBlock(laptop, laptop.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
		registerRender(laptop);
	}
	
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
