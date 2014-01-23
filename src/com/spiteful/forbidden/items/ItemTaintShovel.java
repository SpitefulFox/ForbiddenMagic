package com.spiteful.forbidden.items;

import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSpade;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ItemApi;
import net.minecraftforge.common.ForgeHooks;

public class ItemTaintShovel extends ItemSpade implements IRepairable
{
    public Icon icon;

    public ItemTaintShovel(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, enumtoolmaterial);
        this.setCreativeTab(Forbidden.tab);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        this.icon = ir.registerIcon("forbidden:taintshovel");
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return this.icon;
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.isItemEqual(new ItemStack(Config.thaumcraftResourceID, 1, 2)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        if (ForgeHooks.isToolEffective(stack, block, meta) || block.blockMaterial == Config.taintMaterial)
        {
            return efficiencyOnProperMaterial;
        }

        return getStrVsBlock(stack, block);
    }
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
		if(player != null && player.isPotionActive(Config.thaumcraftTaintPotionID)) {
			itemstack.damageItem(5, player);
			player.swingItem();
			if(player.worldObj.isRemote)
				player.removePotionEffectClient(Config.thaumcraftTaintPotionID);
			else {
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
				player.removePotionEffect(Config.thaumcraftTaintPotionID);
			}
			return true;
		}
		return super.onItemUse(itemstack, player, world, x, y, z, side, par8, par9, par10);
	}
}
