package lordfokas.stargatetech2;

import java.util.ArrayList;
import java.util.List;

import lordfokas.naquadria.render.IVariantProvider;
import lordfokas.stargatetech2.modules.IContentModule.Module;
import lordfokas.stargatetech2.reference.ModReference;
import lordfokas.stargatetech2.util.GUIHandlerClient;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

@SideOnly(Side.CLIENT)
public class ProxyClient implements ISidedProxy{

	@Override
	public void registerRenderers(Module module){
		switch(module){
			case AUTOMATION:
				break;
			case CORE:
				break;
			case ENEMY:
				break;
			case ENERGY:
				break;
			case INTEGRATION:
				break;
			case TRANSPORT: // FIXME whatever is wrong with these generics
				//ClientRegistry.bindTileEntitySpecialRenderer(TileBeaconMatterGrid.class, new RenderBeaconMatterGrid());
				//ClientRegistry.bindTileEntitySpecialRenderer(TileTransportRing.class, new RenderTransportRing());
				//ClientRegistry.bindTileEntitySpecialRenderer(TileStargate.class, new RenderStargateTile());
				break;
			case WORLD:
				break;
		}
	}
	
	@Override
	public void registerHandlers() { // FIXME re-enable when Transport is back.
		NetworkRegistry.INSTANCE.registerGuiHandler(StargateTech2.instance, new GUIHandlerClient());
		//RingKeyHandler.register();
		//RingOverlay.register();
	}

	@Override
	public boolean isLocalPlayerInAABB(World world, AxisAlignedBB aabb){ // FIXME might be wrong class
		List<EntityPlayerMP> players = world.getEntitiesWithinAABB(EntityPlayerMP.class, aabb);
		return players.size() == 1;
	}

    @Override
    public void handleBlockModel(Block block, String name) {
        if (block instanceof IVariantProvider && Item.getItemFromBlock(block) != null) {
            IVariantProvider provider = (IVariantProvider) block;
            List<Pair<Integer, String>> variants = new ArrayList<Pair<Integer, String>>();
            provider.addVariants(variants);
            for (Pair<Integer, String> variant : variants)
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), variant.getLeft(), new ModelResourceLocation(new ResourceLocation(ModReference.MOD_ID, name), variant.getRight()));
        }
    }

    @Override
    public void handleItemModel(Item item, String name) {
        if (item instanceof IVariantProvider) {
            IVariantProvider provider = (IVariantProvider) item;
            List<Pair<Integer, String>> variants = new ArrayList<Pair<Integer, String>>();
            provider.addVariants(variants);
            for (Pair<Integer, String> variant : variants) {
                String location = name;
                if (!variant.getRight().equals("inventory"))
                    location = "items/" + name;
                ModelLoader.setCustomModelResourceLocation(item, variant.getLeft(), new ModelResourceLocation(new ResourceLocation(ModReference.MOD_ID, location), variant.getRight()));
            }
        }
    }
}