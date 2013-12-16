package stargatetech2.core.worldgen.lists;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import stargatetech2.common.util.Vec3Int;
import stargatetech2.core.ModuleCore;
import stargatetech2.core.block.BlockStargate;
import stargatetech2.core.tileentity.TileStargateRing;

public class StargateBuildList extends BuildList {
	private static final BuildMaterial[] MATERIAL = new BuildMaterial[]{
		new BuildMaterial(ModuleCore.stargate.blockID, BlockStargate.META_BASE),
		new BuildMaterial(ModuleCore.stargate.blockID, BlockStargate.META_RING)
	};
	
	public static StargateBuildList SGX = new StargateBuildList(1, 0);
	public static StargateBuildList SGZ = new StargateBuildList(0, 1);
	
	private StargateBuildList(int x, int z){
		for(int i = 1; i <= 2; i++){
			set( x * i, 0,  z * i, 0);
			set(-x * i, 0, -z * i, 0);
		}
		for(int i = 1; i < 5; i++){
			set( 2 * x, i,  2 * z, 1);
			set(-2 * x, i, -2 * z, 1);
		}
		set( x, 4,  z, 1);
		set(-x, 4, -z, 1);
		set( 0, 4,  0, 1);
	}
	
	public void buildStargate(World w, int x, int y, int z, int sgx, int sgy, int sgz){
		build(w, x, y, z, MATERIAL, new Vec3Int(sgx, sgy, sgz));
	}
	
	@Override
	protected void afterBlock(World w, int x, int y, int z, Object o){
		Vec3Int sg = (Vec3Int) o;
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te instanceof TileStargateRing){
			((TileStargateRing)te).setSGPosition(sg.x, sg.y, sg.z);
		}
	}
}
