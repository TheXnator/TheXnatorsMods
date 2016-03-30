package thexnator.nohitdelay;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.nohitdelay.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class NoHitDelay extends CooldownTracker{
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerRenders();
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
	
	@EventHandler
    public void Attack(CooldownTracker event, Item itemIn)
    {
		event.setCooldown(itemIn, 0);
		event.removeCooldown(itemIn);
    }
	
	@Override
	public boolean hasCooldown(Item itemIn)
    {
		this.removeCooldown(itemIn);
        return this.getCooldown(itemIn, 0.0F) > 0.0F;
    }
	
	@Override
	public float getCooldown(Item itemIn, float p_185143_2_)
    {
		this.removeCooldown(itemIn);
        return 0.0F;
    }
	
	@Override
	public void tick()
    {
		this.removeCooldown(null);
    }
	
	@Override
	public void setCooldown(Item itemIn, int ticksIn)
    {
		this.removeCooldown(itemIn);
    }
	
	public class Overrider extends Item {
		
		 private final IItemPropertyGetter COOLDOWN_GETTER = new IItemPropertyGetter()
		 	{
			 	@Override
		        @SideOnly(Side.CLIENT)
		        public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn)
		        {
		            return maxStackSize ;
		        }
		    };
	}
	
}
