package com.spiteful.forbidden.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBoundShears extends ItemSword implements IBindable {

	public IIcon icon;

	public ItemBoundShears(ToolMaterial mat) {
		super(mat);
		this.setCreativeTab(Forbidden.tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:bound_shears");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icon;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return block != Blocks.web && block != Blocks.leaves && block != Blocks.leaves2 ? (block == Blocks.wool ? 5.0F : super.func_150893_a(stack, block)) : 15.0F;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.none;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 0;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		return stack;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {
		return block == Blocks.web || block == Blocks.redstone_wire || block == Blocks.tripwire;
	}
}