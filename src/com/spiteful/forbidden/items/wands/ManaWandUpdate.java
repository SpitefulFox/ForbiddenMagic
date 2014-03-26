package com.spiteful.forbidden.items.wands;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Compat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import am2.api.ArsMagicaApi;
import am2.api.IExtendedProperties;

public class ManaWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
		if(Compat.am2)
		{
			if(player.ticksExisted % 100 == 0){
			
				IExtendedProperties prop = ArsMagicaApi.instance.getExtendedProperties(player);
			
				float cost;
				if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("vinteum"))
					cost = 90.0F;
				else
					cost = 150.0F;
				
				if(prop == null || prop.getCurrentMana() <= 0)
					return;
			
			
				for(int x = 0;x < primals.length;x++){
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack)) {
						if(prop.getCurrentMana() > cost)
						{						
							prop.setCurrentMana(prop.getCurrentMana() - cost);
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
						}
					}
				}
			}
		
		}
		
	}
}
