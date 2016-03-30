package thexnator.computermod.blocks;

import java.util.List;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.computermod.util.NBTHelper;
import thexnator.computermod.ComputerMod;
import thexnator.computermod.init.ComputerBlocks;
import thexnator.computermod.util.CollisionHelper;
import thexnator.computermod.util.StateHelper;
import thexnator.computermod.tileentity.TileEntityLaptop;

public class BlockLaptop extends BlockDirectional implements ITileEntityProvider{

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum TYPE = PropertyEnum.create("type", Type.class);
	
	public BlockLaptop() {
		super(Material.iron);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, Type.BASE));
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, BlockPos pos)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.setBlockBoundsBasedOnState(blockAccess, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		int rotation = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		float[] bounds = CollisionHelper.fixRotation(rotation, 0.875F, 0.0F, 1.0F, 1.0F);
		setBlockBounds(0F, 0f, 0F, 1F, 0.6F, 1F);
		super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		return state.withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(TYPE, Type.BASE);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, FACING, TYPE);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote){
			if(player.isSneaking())
			{
				TileEntity tileEntity = world.getTileEntity(pos);
				if(tileEntity instanceof TileEntityLaptop)
				{
					TileEntityLaptop laptop = (TileEntityLaptop) tileEntity;
					laptop.openClose();
				}
			}
		}
//			if (world.isRemote) {
//	            player.openGui(ComputerMod.instance, 0, world, (int) player.posX, (int) player.posY, (int) player.posZ);
//	        }
	        return true;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(TYPE, Type.BASE);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}
	
	public static enum Type implements IStringSerializable
	{
		BASE, SCREEN;

		@Override
		public String getName()
		{
			return name().toLowerCase();
		}
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLaptop();
	}

}
