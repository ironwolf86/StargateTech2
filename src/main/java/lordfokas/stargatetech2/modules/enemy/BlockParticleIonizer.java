package lordfokas.stargatetech2.modules.enemy;

import lordfokas.stargatetech2.ZZ_THRASH.BlockMachine__THRASH;
import lordfokas.stargatetech2.reference.BlockReference;
import lordfokas.stargatetech2.util.GUIHandler.Screen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockParticleIonizer extends BlockMachine__THRASH{
	public BlockParticleIonizer() {
		super(BlockReference.PARTICLE_IONIZER, false, Screen.PARTICLE_IONIZER);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileParticleIonizer();
	}
}