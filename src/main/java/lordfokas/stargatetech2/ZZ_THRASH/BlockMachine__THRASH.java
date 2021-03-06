package lordfokas.stargatetech2.ZZ_THRASH;

@Deprecated
public abstract class BlockMachine__THRASH /* extends BaseBlockContainer */{
	/*private boolean useVertical = false;
	private Screen screen;
	
	public BlockMachine__THRASH(String uName, boolean owned){
		this(uName, owned, null);
	}
	
	public BlockMachine__THRASH(String uName, boolean owned, Screen screen) {
		super(uName, !owned, true);
		super.setRenderer(RenderBlockMachine__THRASH.instance());
		this.screen = screen;
		if(!owned){
			this.setResistance(80000F);
			this.setHardness(4.0F);
			setHarvestLevel("pickaxe", 0);
		}
	}
	
	protected void setUseVertical(){
		useVertical = true;
	}
	
	public IIcon getFaceForMeta(int meta){
		return getBaseIcon(3, 0);
	}
	
	@Override
	public IIcon getBaseIcon(int side, int meta){
		switch(side){
			case 0: return IconRegistry.blockIcons.get(TextureReference.MACHINE_BOTTOM);
			case 1: return IconRegistry.blockIcons.get(TextureReference.MACHINE_TOP);
			case 3: return blockIcon;
			default: return IconRegistry.blockIcons.get(TextureReference.MACHINE_SIDE);
		}
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int s, float hx, float hy, float hz){
		ItemStack stack = p.inventory.getCurrentItem();
		Item item = stack != null ? stack.getItem() : null;
		if(item instanceof IToolWrench && canPlayerAccess(p, w, x, y, z) && p.isSneaking()){
			IToolWrench wrench = (IToolWrench) item;
			if(wrench.canWrench(p, x, y, z)){
				dropBlockAsItem(w, x, y, z, 0, 0);
				w.setBlock(x, y, z, (Block) Block.blockRegistry.getObjectById(0), 0, 3);
				wrench.wrenchUsed(p, x, y, z);
				return true;
			}
		}else if(!p.isSneaking() && screen != null && canPlayerAccess(p, w, x, y, z)){
			p.openGui(StargateTech2.instance, screen.ordinal(), w, x, y, z);
			return true;
		}
		return false;
	}
	
	protected final boolean canPlayerAccess(EntityPlayer player, World world, int x, int y, int z){
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof IOwnedMachine){
			return ((IOwnedMachine)te).hasAccess(player.getDisplayName());
		}else{
			return true;
		}
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase living, ItemStack stack){
		ForgeDirection dir = Helper.yaw2dir(living.rotationYaw, living.rotationPitch, useVertical);
		w.setBlockMetadataWithNotify(x, y, z, dir.ordinal(), 2);
		if(living instanceof EntityPlayer){
			TileEntity te = w.getTileEntity(x, y, z);
			if(te instanceof IOwnedMachine){
				((IOwnedMachine)te).setOwner(((EntityPlayer)living).getDisplayName());
			}
			onPlacedBy(w, x, y, z, (EntityPlayer)living, dir);
		}
	}
	
	protected void onPlacedBy(World w, int x, int y, int z, EntityPlayer player, ForgeDirection facing){}
	
	public final FaceColor[] getTextureMap(IBlockAccess w, int x, int y, int z){
		TileEntity te = w.getTileEntity(x, y, z);
		FaceColor[] map = new FaceColor[]{FaceColor.VOID, FaceColor.VOID, FaceColor.VOID, FaceColor.VOID, FaceColor.VOID, FaceColor.VOID};
		if(te instanceof TileMachine__THRASH){
			TileMachine__THRASH machine = (TileMachine__THRASH) te;
			for(int i = 0; i < 6; i++){
				map[i] = machine.getColor(i);
			}
		}
		return map;
	}*/
}
