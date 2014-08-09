package fox.spiteful.forbidden.items.wands;

import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

public class BloodWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

	public void onUpdate(ItemStack itemstack, EntityPlayer player)
	{
		if(Compat.bm && player.ticksExisted % 100 == 0)
		{
			try
			{
				SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);
				
				int cost;
				if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("alchemical"))
					cost = 900;
				else
					cost = 1000;
				
				for(int x = 0;x < primals.length;x++)
				{
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
					{
						if(player.capabilities.isCreativeMode)
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
						else if(SoulNetworkHandler.syphonFromNetwork(itemstack, cost) > 0)
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
						else if(syphonHealth(player))
						{
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
							return;
						}
						else
							return;
					}
				}
			
			}
			catch(Throwable e)
			{
				return;
			}
		}
		
	}
	
	public boolean syphonHealth(EntityPlayer player)
	{
		if(player.getHealth() > 6)
		{
			player.setHealth(player.getHealth() - 3);
			return true;
		}
		else
			return false;
	}
}
