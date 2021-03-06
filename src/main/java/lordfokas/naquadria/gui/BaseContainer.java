package lordfokas.naquadria.gui;

import gnu.trove.map.hash.TIntIntHashMap;
import lordfokas.naquadria.tileentity.BaseTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseContainer extends Container {
	public final BaseTileEntity te;
	private TIntIntHashMap lastValues;
	
	//TODO remove this
	/** @deprecated THIS WILL CRASH!! */
	public BaseContainer(){
		throw new RuntimeException("UPDATE 'YER MACHINES BIATCH!!");
	}
	
	public BaseContainer(BaseTileEntity te){
		this.te = te;
		int[] values = te.getKeyArray();
		if(values.length > 0){
			lastValues = new TIntIntHashMap();
			for(int i : values){
				lastValues.put(i, Integer.MIN_VALUE);
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	protected void bindInventory(EntityPlayer player, int xOffset, int yOffset){
		IInventory inventoryPlayer = player.inventory;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, (j + (i * 9)) + 9, xOffset + j * 18, yOffset + i * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, xOffset + i * 18, 58 + yOffset));
		}
	}
	
	protected void sendUpdate(int key, int value){
		for(IContainerListener listener : listeners){
			listener.sendProgressBarUpdate(this, key, value);
		}
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if(lastValues != null){
			for(int i : te.getKeyArray()){
				int val = te.getValue(i);
				if(val != lastValues.get(i)){
					sendUpdate(i, val);
					lastValues.put(i, val);
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int key, int value){
		te.setValue(key, value);
	}
}