package fox.spiteful.forbidden.items.wands;

import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class ManaStaffUpdate implements IWandRodOnUpdate {

    Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if(Compat.am2 && Config.crossWand)
        {
            if(player.ticksExisted % 40 == 0){
            
                //TODO: Readd ArsMagica Support
                try
                { 
                    /* IExtendedProperties prop = ArsMagicaApi.instance.getExtendedProperties(player);
                
                    float cost;
                    if(((ItemWandCasting)itemstack.getItem()).getCap(itemstack).getTag().equals("vinteum"))
                        cost = 50.0F;
                    else
                        cost = 100.0F;
                    
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
                    }*/
                } 
                catch(Throwable e){}
            }
        
        }
        
    }
}
