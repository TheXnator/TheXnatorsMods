package thexnator.computermod.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.computermod.tileentity.TileEntityLaptop;

public class ComputerTileEntities {
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityLaptop.class, "cmLaptop");
	}
}
