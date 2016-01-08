package fox.spiteful.forbidden.potions;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.items.tools.ItemDragonslayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

public class PotionDragonwrack extends Potion {

    DamageSource wrack;

    public PotionDragonwrack(int id){
        super(id, false, 0xFFFFAA);
        this.setPotionName("potion.dragonwrack");
        this.setIconIndex(4, 1);
        wrack = new DamageSource("dragonwrack").setDamageBypassesArmor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(DarkPotions.icons);
        return super.getStatusIconIndex();
    }

    @Override
    public void performEffect(EntityLivingBase victim, int level)
    {
        if(!ItemDragonslayer.isDragon(victim) || victim.worldObj.isRemote)
            return;
        ItemDragonslayer.absoluteDamage(victim, wrack, Math.max(1F, victim.getMaxHealth() / 20F));
        if(victim instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)victim;
            for(int x = 0;x < 5;x++){
                if(player.getEquipmentInSlot(x) != null){
                    ItemStack equip = player.getEquipmentInSlot(x);
                    if(equip.getItem() instanceof IEnergyContainerItem){
                        IEnergyContainerItem battery = (IEnergyContainerItem)equip.getItem();
                        battery.extractEnergy(equip, battery.getMaxEnergyStored(equip) / 50, false);
                    }
                }
            }
        }
    }

    public boolean isReady(int tick, int level)
    {
        int k;

        k = 40 >> level;
        return k <= 0 || tick % k == 0;
    }
}
