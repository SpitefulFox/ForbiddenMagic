package fox.spiteful.forbidden.items.wands;

import fox.spiteful.forbidden.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import vazkii.botania.api.mana.ManaItemHandler;

import fox.spiteful.forbidden.compat.Compat;

public class YandereWandUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);
	
	public void onUpdate(ItemStack itemstack, EntityPlayer player)
	{
		if(Compat.botan && Config.crossWand && player.ticksExisted % 40 == 0 && checkHotbar(itemstack, player))
		{
			try
			{
				int cost;
				if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("manasteel"))
					cost = 900;
				else
					cost = 1100;
				
				for(int x = 0;x < primals.length;x++)
				{
					if(((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
					{
						if(ManaItemHandler.requestManaExact(itemstack, player, cost, true))
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
					}
				}
			
			}
			catch(Throwable e)
			{
				return;
			}
		}
	
	}

    private boolean checkHotbar(ItemStack stack, EntityPlayer player)
    {
        for(int x = 0; x < 9; ++x)
        {
            ItemStack item = player.inventory.getStackInSlot(x);
            if(item == stack)
                return true;
        }
        return false;
    }
	
}
