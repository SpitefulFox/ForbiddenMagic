package com.spiteful.forbidden.items;

import com.spiteful.forbidden.*;

import net.minecraft.item.ItemFood;
import net.minecraft.util.Icon;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGluttonyShard extends ItemFood
{
	Icon icon;
	
	public ItemGluttonyShard(int id)
	{
		super(id, 2, 0.1F, false);
		this.setCreativeTab(Forbidden.tab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.icon = ir.registerIcon("forbidden:gluttonyshard");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return this.icon;
	}
}