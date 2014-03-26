package com.spiteful.forbidden.items.wands;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Compat;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import vazkii.botania.api.mana.ManaItemHandler;

public class YandereWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);
	
	public void onUpdate(ItemStack itemstack, EntityPlayer player)
	{
		if(Compat.botan && player.ticksExisted % 30 == 0)
		{
			try
			{
				int cost;
				if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("manasteel"))
					cost = 600;
				else
					cost = 800;
				
				ArrayList unfilled = new ArrayList();
				for(int x = 0;x < primals.length;x++)
				{
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
					{
						unfilled.add(primals[x]);
					}
				}
				if(unfilled.size() > 0 && ManaItemHandler.requestManaExact(itemstack, player, cost, true))
						((ItemWandCasting)itemstack.getItem()).addVis(itemstack, (Aspect)(unfilled.get(player.worldObj.rand.nextInt(unfilled.size()))), 1, true);
			
			}
			catch(Throwable e)
			{
				return;
			}
		}
	
	}
	
}
