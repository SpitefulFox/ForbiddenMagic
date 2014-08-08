package com.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;

import com.spiteful.forbidden.compat.Compat;

public class SoulWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

	public void onUpdate(ItemStack itemstack, EntityPlayer player)
	{
		if(Compat.pb && player.ticksExisted % 150 == 0)
		{
			try
			{
				//TODO: Readd Player Beacons Support
				/* TileEntityPlayerBeacon bacon = BeaconDataHelper.getBeaconForDim(player, player.worldObj.provider.dimensionId);
				if(bacon != null && bacon.hasSkull())
				{
			
					TileEntitySkull skull = (TileEntitySkull) bacon.worldObj.getBlockTileEntity(bacon.xCoord, bacon.yCoord + 1, bacon.zCoord);
					if(skull == null)
						return;
					else if (!skull.getExtraType().equals(bacon.getOwner()))
						return;
					
					int cost;
					if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("soul"))
						cost = 20;
					else
						cost = 50;
					
					int charge = 0;
					for(int j = 0;j < primals.length;j++)
					{
						if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[j]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
						{
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[j], 1, true);
							bacon.setCorruption(bacon.getCorruption() + 50, true);
							bacon.doCorruption(false);
							charge++;
						}
					}
					if(player.worldObj.rand.nextInt(100) < charge)
						bacon.doCorruption(true);
				
				}*/
			} 
			catch(Exception e)
			{
				return;
			}
			catch(NoClassDefFoundError e)
			{
				return;
			}
		}
	}
}
