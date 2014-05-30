package com.spiteful.forbidden.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRidingCrop extends ItemSword {
	
	public IIcon icon;
	
	public ItemRidingCrop(ToolMaterial mat){
		super(mat);
		this.setCreativeTab(Forbidden.tab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("forbidden:crop");
	}
	
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player)
    {
        stack.damageItem(1, player);
		if(victim instanceof EntityHorse || victim instanceof EntityPig)
			victim.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 175, 5));
		else if(victim instanceof EntityPlayer)
		{
			victim.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 75, 1));
			victim.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 75, 1));
		}
		if(!player.worldObj.isRemote && !Config.noLust && player.worldObj.provider.dimensionId == -1 && player.worldObj.rand.nextInt(30) == 1)
		{
			EntityItem ent = victim.entityDropItem(new ItemStack(ForbiddenItems.deadlyShards, 1, 4), 1.0F);
			ent.motionY += player.worldObj.rand.nextFloat() * 0.05F;
			ent.motionX += (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.1F;
			ent.motionZ += (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.1F;
		}
        return true;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}
	
	@Override
	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	 * sword
	 */
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return 1.0F;
	}
	
	@Override
	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.none;
	}
	
	@Override
	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 0;
	}
	
	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(player.ridingEntity != null && player.ridingEntity instanceof EntityLivingBase)
		{
			EntityLivingBase mount = (EntityLivingBase)player.ridingEntity;
			stack.damageItem(1, player);
			player.swingItem();
			//mount.attackEntityFrom(DamageSource.causePlayerDamage(player), 4.0F);
			mount.attackEntityFrom(DamageSource.generic, 1.0F);
			mount.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 175, 5));
		}
		return stack;
	}
	
	@Override
	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == Items.leather ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	@Override
	/**
	 * Returns if the item (tool) can harvest results from the block type.
	 */
	public boolean canHarvestBlock(Block par1Block, ItemStack itemStack)
	{
		return false;
	}
}