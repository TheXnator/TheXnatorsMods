package thexnator.computermod.gui;

import java.io.IOException;
import minersbasic.*;
import minersbasic.api.client.gui.GuiRenderer;
import minersbasic.api.client.render.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.network.internal.FMLMessage.OpenGui;
import thexnator.computermod.ComputerMod;

public class GuiLaptop extends GuiScreen
{
	private GuiButton ShutDown;
	private GuiButton Notes;
	public static boolean NotesOpen = false;
	//private GuiScreen NotesGui = new GuiNotes();
	private static GuiRenderer g = GuiRenderer.instance;
	private final static ResourceLocation Desktop = new ResourceLocation("cm", "textures/gui/laptop.png");
	private final static ResourceLocation NotesPage = new ResourceLocation("cm", "textures/gui/notes.png");
	
	public int guiwidth = 384;
    public int guiheight = 230;
    public int width;
    public int height;
    public int scale;
    public int guileft;
    public int guitop;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(NotesOpen = false){
			this.drawDefaultBackground();
			g.zLevel = 0;
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(Desktop);
			g.drawTexturedRect(this.guileft + 45, this.guitop + 15, 490, 316, 0, 0, this.guiwidth, this.guiheight);
			String titleDesktop = "Desktop";
			g.zLevel = 300;
	        g.drawString(titleDesktop, this.guileft + 8, this.guitop + 6, Color.LIGHT_GRAY, true);
	        g.zLevel = 0;
			//this.drawHorizontalLine(int startX, int endX, int y, int color);
			//this.drawVerticalLine(int x, int startY, int endY, int color);
			//this.drawRect(int left, int top, int right, int bottom, int color);
			//this.drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor);
			//this.fontRenderer.getStringWidth(String string);
			//this.fontRenderer.drawSplitString(String str, int x, int y, int wrapWidth, int textColor);
			super.drawScreen(mouseX, mouseY, partialTicks);
			//this.fontRendererObj.drawString("Laptop", 5.0F, 5.0F, 1, true);
			GuiRenderer.drawItemStack(new ItemStack(Items.redstone), 48, 203);
			GuiRenderer.drawItemStack(new ItemStack(Items.paper), 70, 203);
			if(mouseX >= 48 && mouseX <= 65 && mouseY >= 203 && mouseY <= 218){
				this.fontRendererObj.drawString("Shut Down", width / 2 + 50, height / 2 + 195, 1);
			}
		}
			
		
	}
	
	public void initGui() {
	    this.buttonList.add(this.ShutDown = new GuiButton(0, width / 2 + 48, height / 2 + 203, 19, 14, ""));
	    this.buttonList.add(this.Notes = new GuiButton(1, width / 2 + 68, height / 2 + 203, 19, 14, ""));
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
	    if (button == this.ShutDown) {
	        //ComputerMod.packetHandler.sendToServer("Laptop Shut Down");
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	    }
	    
	    if(button == this.Notes) {
	    	setNotesOpen(true);
	    }
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}
	
	public void setNotesOpen(boolean notesOpen) {
		NotesOpen = notesOpen;
	}
}

class GuiNotes extends GuiLaptop {
	private GuiButton ShutDown;
	private GuiButton Notes;
	private GuiScreen NotesGui;
	private static GuiRenderer g = GuiRenderer.instance;
	private final static ResourceLocation Desktop = new ResourceLocation("cm", "textures/gui/laptop.png");
	private final static ResourceLocation NotesPage = new ResourceLocation("cm", "textures/gui/notes.png");
	
	public int guiwidth = 384;
    public int guiheight = 230;
    public int width;
    public int height;
    public int scale;
    public int guileft;
    public int guitop;
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(NotesOpen = true){
			this.drawDefaultBackground();
			g.zLevel = 0;
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(Desktop);
			g.drawTexturedRect(this.guileft + 45, this.guitop + 15, 490, 316, 0, 0, this.guiwidth, this.guiheight);
			String titleNotes = "Notes";
			g.zLevel = 300;
	        g.drawString(titleNotes, this.guileft + 8, this.guitop + 6, Color.LIGHT_GRAY, true);
	        g.zLevel = 0;
	        this.mc.getTextureManager().bindTexture(NotesPage);
	        g.drawTexturedRect(this.guileft, this.guitop, 500, 316, 0, 0, this.guiwidth, this.guiheight);
			//this.drawHorizontalLine(int startX, int endX, int y, int color);
			//this.drawVerticalLine(int x, int startY, int endY, int color);
			//this.drawRect(int left, int top, int right, int bottom, int color);
			//this.drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor);
			//this.fontRenderer.getStringWidth(String string);
			//this.fontRenderer.drawSplitString(String str, int x, int y, int wrapWidth, int textColor);
			super.drawScreen(mouseX, mouseY, partialTicks);
			//this.fontRendererObj.drawString("Laptop", 5.0F, 5.0F, 1, true);
			GuiRenderer.drawItemStack(new ItemStack(Items.redstone), 48, 203);
			GuiRenderer.drawItemStack(new ItemStack(Items.paper), 70, 203);
			if(mouseX >= 48 && mouseX <= 65 && mouseY >= 203 && mouseY <= 218){
				this.fontRendererObj.drawString("Shut Down", width / 2 + 50, height / 2 + 195, 1);
			}
			System.out.println("Notes");
		}	
	}
	
	public void initGui() {
	    this.buttonList.add(this.ShutDown = new GuiButton(0, width / 2 + 48, height / 2 + 203, 19, 14, ""));
	    this.buttonList.add(this.Notes = new GuiButton(0, width / 2 + 68, height / 2 + 203, 19, 14, ""));
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
	    if (button == this.ShutDown) {
	        //ComputerMod.packetHandler.sendToServer("Laptop Shut Down");
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	    }
	    
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}
}
