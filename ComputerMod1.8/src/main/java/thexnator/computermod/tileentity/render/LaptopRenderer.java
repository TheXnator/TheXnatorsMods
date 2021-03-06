package thexnator.computermod.tileentity.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import thexnator.computermod.blocks.BlockLaptop;
import thexnator.computermod.init.ComputerBlocks;
import thexnator.computermod.tileentity.TileEntityLaptop;

public class LaptopRenderer extends TileEntitySpecialRenderer
{
	private Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double z, double y, float partialTicks, int destroyStage)
	{
		IBlockState state = ComputerBlocks.laptop.getDefaultState().withProperty(BlockLaptop.TYPE, BlockLaptop.Type.SCREEN);
		BlockPos pos = te.getPos();
		
		bindTexture(TextureMap.locationBlocksTexture);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5, 0.5, 0.5);
			GlStateManager.translate(-0.5, 0, -0.5);
			
			GlStateManager.disableLighting();
			
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer renderer = tessellator.getWorldRenderer();
			// THIS IS BROKEN >> renderer.begin(7, renderer.getVertexFormat());
			renderer.startDrawing(7);
			renderer.setTranslation(-pos.getX(), -pos.getY(), -pos.getZ());
			
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			IBakedModel ibakedmodel = mc.getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state);
			blockrendererdispatcher.getBlockModelRenderer().renderModel(getWorld(), ibakedmodel, state, pos, renderer, false);
			
			renderer.setTranslation(0.0D, 0.0D, 0.0D);
			tessellator.draw();
			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
	}
	
}