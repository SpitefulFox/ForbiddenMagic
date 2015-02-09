package fox.spiteful.forbidden.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRunicArmor;

public class ItemRingNutrition extends Item implements IRunicArmor, IBauble {
    IIcon icon;

    public ItemRingNutrition(){
        super();
        maxStackSize = 1;
        setCreativeTab(Forbidden.tab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon("forbidden:ring_nutrition");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return icon;
    }

    public BaubleType getBaubleType(ItemStack itemstack){
        return BaubleType.RING;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player){}
    public void onEquipped(ItemStack itemstack, EntityLivingBase player){}
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player){}

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player){
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player){
        return true;
    }

    public int getRunicCharge(ItemStack itemstack){
        return 0;
    }
}
