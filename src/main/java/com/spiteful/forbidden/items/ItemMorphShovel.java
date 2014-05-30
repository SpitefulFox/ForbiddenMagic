package com.spiteful.forbidden.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;

import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.enchantments.DarkEnchantments;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMorphShovel extends ItemSpade implements IRepairable
{
	public IIcon[] icon;

	public ItemMorphShovel(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
		this.setHarvestLevel("shovel", 4);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icon = new IIcon[2];
		this.icon[0] = ir.registerIcon("forbidden:chameleonshovel");
		this.icon[1] = ir.registerIcon("forbidden:eyeshovel");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass) {
		return renderPass != 1?icon[0]:icon[1];
	}

	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.epic;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(ForbiddenItems.deadlyShards, 1, 1)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(player.isSneaking() && itemstack.hasTagCompound() && getMaxDamage() - itemstack.getItemDamage() > 5){
			NBTTagCompound tags = itemstack.getTagCompound();
			byte phase = tags.getByte("phase");
			NBTTagList enchants = itemstack.getEnchantmentTagList();
			if(enchants != null)
				tags.setTag("enchants" + phase, enchants);
			else
				tags.removeTag("enchants" + phase);
			if(tags.hasKey("display"))
			{
				String name = tags.getCompoundTag("display").getString("Name");
				if(name != null && !name.equals(""))
					tags.getCompoundTag("display").setString("Name" + phase, name);
				else
					tags.getCompoundTag("display").removeTag("Name" + phase);
			}
			if(++phase > 2)
				phase = 0;
			tags.setByte("phase", phase);
			enchants = (NBTTagList)(tags.getTag("enchants" + phase));
			if(enchants == null)
				tags.removeTag("ench");
			else
				tags.setTag("ench", enchants);
				
			if(tags.hasKey("display"))
			{
				String name = tags.getCompoundTag("display").getString("Name" + phase);
				if(name != null && !name.equals(""))
					tags.getCompoundTag("display").setString("Name", name);
				else
					tags.getCompoundTag("display").removeTag("Name");
			}
			
			itemstack.setTagCompound(tags);
			itemstack.damageItem(5, player);
			player.swingItem();
			world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
		}		
		return itemstack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemstack, int renderpass)
	{
		if(renderpass != 1)
			return 16777215;
		else
		{
			if(!itemstack.hasTagCompound())
				return 0x980000;
			byte phase = itemstack.getTagCompound().getByte("phase");
			if(phase == 1)
				return 0x0010CC;
			else if(phase == 2)
				return 0xE5DA00;
			else
				return 0x980000;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	/**
     * Set the damage for this itemstack. Note, this method is responsible for zero checking.
     * @param stack the stack
     * @param damage the new damage value
     */
    public void setDamage(ItemStack stack, int damage)
    {
		if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.eternal.effectId, stack) > 0)
			super.setDamage(stack, 0);
		else
			super.setDamage(stack, damage);
    }
	
}
