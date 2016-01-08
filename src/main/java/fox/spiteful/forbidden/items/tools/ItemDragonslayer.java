package fox.spiteful.forbidden.items.tools;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.LogHandler;
import fox.spiteful.forbidden.compat.Compat;
import fox.spiteful.forbidden.potions.DarkPotions;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.apache.logging.log4j.Level;
import thaumcraft.api.IRepairable;

public class ItemDragonslayer extends ItemSword implements IRepairable {
    public IIcon icon;
    public Item repair;

    public ItemDragonslayer() {
        super(ToolMaterial.EMERALD);
        this.setCreativeTab(Forbidden.tab);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("forbidden:dragonslayer");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    @Override
    public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
        return stack2.getItem() == repair || super.getIsRepairable(stack, stack2);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        stack.damageItem(1, player);
        if (!victim.worldObj.isRemote && Compat.dargon & isDragon(victim)) {
            absoluteDamage(victim, new EntityDamageSource("dragonslayer", player).setDamageBypassesArmor(), Math.max(4F, victim.getMaxHealth() / 5F));
            if(victim instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer)victim;
                for(int x = 0;x < 5;x++){
                    if(target.getEquipmentInSlot(x) != null){
                        ItemStack equip = target.getEquipmentInSlot(x);
                        if(equip.getItem() instanceof IEnergyContainerItem){
                            IEnergyContainerItem battery = (IEnergyContainerItem)equip.getItem();
                            battery.extractEnergy(equip, battery.getMaxEnergyStored(equip) / 20, false);
                        }
                    }
                }
            }
            victim.addPotionEffect(new PotionEffect(DarkPotions.dragonwrack.getId(), 300));
        }
        return true;
    }

    public static boolean isDragon(Entity target){
        if(target == null)
            return false;
        if(target instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)target;
            for(int x = 1;x < 5;x++){
                if(player.getEquipmentInSlot(x) != null){
                    UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(player.getEquipmentInSlot(x).getItem());
                    if(id != null && id.modId.equals("DraconicEvolution"))
                        return true;
                }
            }
        }
        else if(target instanceof EntityDragon ||
                target.getCommandSenderName().toLowerCase().contains("dragon") ||
                target.getCommandSenderName().toLowerCase().contains("drake") ||
                target.getCommandSenderName().toLowerCase().contains("dracon"))
            return true;
        return false;
    }

    public static void absoluteDamage(EntityLivingBase target, DamageSource src, float damage){
        target.func_110142_aN().func_94547_a(src, target.getHealth(), damage);
        target.setHealth(target.getHealth() - damage);
        if(target.getHealth() < 1){
            target.onDeath(src);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGetHurt(LivingHurtEvent event) {
        if(event.entityLiving instanceof EntityPlayer && event.source.getEntity() != null){
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack equip = player.getCurrentEquippedItem();
            if(equip != null && equip.getItem() == this && isDragon(event.source.getEntity())){
                event.ammount = Math.min(event.ammount, 3F);
            }
        }
    }

    @SubscribeEvent
    public void onAttacked(LivingAttackEvent event) {
        if(event.entityLiving instanceof EntityPlayer && event.source.getEntity() != null){
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack equip = player.getCurrentEquippedItem();
            if(equip != null && player.isUsingItem() && equip.getItem() == this && isDragon(event.source.getEntity())){
                event.setCanceled(true);
                if(event.source.getEntity() instanceof EntityPlayer) {
                    EntityPlayer other = (EntityPlayer)event.source.getEntity();
                    other.addPotionEffect(new PotionEffect(DarkPotions.dragonwrack.getId(), 300));
                    for(int x = 0;x < 5;x++){
                        if(other.getEquipmentInSlot(x) != null){
                            ItemStack gear = other.getEquipmentInSlot(x);
                            if(gear.getItem() instanceof IEnergyContainerItem){
                                IEnergyContainerItem battery = (IEnergyContainerItem)gear.getItem();
                                battery.extractEnergy(gear, battery.getMaxEnergyStored(gear) / 50, false);
                            }
                        }
                    }
                }
            }
        }
    }

}
