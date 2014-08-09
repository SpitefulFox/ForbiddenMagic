package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class InfernalWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = {Aspect.ORDER, Aspect.ENTROPY, Aspect.AIR, Aspect.EARTH, Aspect.WATER};

	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
		if(player.ticksExisted % 100 == 0){
			if(player.worldObj.provider.dimensionId == -1){
				for(int x = 0;x < primals.length;x++){
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack) / 10) {
						((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
					}
				}
			}
			
			if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, Aspect.FIRE) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack) / 5) {
				((ItemWandCasting)itemstack.getItem()).addVis(itemstack, Aspect.FIRE, 1, true);
			}
		}
		
		if(player.isBurning())
			player.extinguish();
			
		if(player.isPotionActive(Potion.wither.id)) {
			if(player.worldObj.isRemote)
				player.removePotionEffectClient(Potion.wither.id);
			else
				player.removePotionEffect(Potion.wither.id);
		}
	}
}
