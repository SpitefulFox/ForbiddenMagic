package fox.spiteful.forbidden.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ProfaneWandUpdate implements IWandRodOnUpdate {

    Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {

        if(player.ticksExisted % 20 == 0){

            NBTTagCompound tag = itemstack.getTagCompound();
            if(!tag.hasKey("contract")){
                Thaumcraft.proxy.getResearchManager().completeResearch(player, "ROD_profane");
                tag.setInteger("contract", 25000);
            }
            else if(tag.getInteger("contract") <= 0)
                return;

            for(int x = 0;x < primals.length && tag.getInteger("contract") > 0;x++){
                int deficit = ((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack) - ((ItemWandCasting)itemstack.getItem()).getVis(itemstack, primals[x]);
                if(deficit > 0) {
                    int restore = Math.min(tag.getInteger("contract"), deficit);
                    ((ItemWandCasting)itemstack.getItem()).addRealVis(itemstack, primals[x], restore, true);
                    tag.setInteger("contract", tag.getInteger("contract") - restore);
                    if(player.worldObj.rand.nextInt(2501) < restore)
                        Thaumcraft.addStickyWarpToPlayer(player, 1);
                }
            }

            if(tag.getInteger("contract") <= 0) {
                ((ItemWandCasting)itemstack.getItem()).setRod(itemstack, WandRod.rods.get("profaned"));
                Thaumcraft.addStickyWarpToPlayer(player, 1);
                player.worldObj.spawnParticle("largeexplode", player.posX, player.posY + (double)(player.height / 2.0F), player.posZ, 0.0D, 0.0D, 0.0D);
            }
            itemstack.setTagCompound(tag);
        }

    }
}
