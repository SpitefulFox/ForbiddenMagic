package com.spiteful.forbidden.items;

import java.util.Iterator;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import WayofTime.alchemicalWizardry.common.PacketHandler;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrystalwell extends Item implements IScribeTools {

	@SideOnly(Side.CLIENT)
	public IIcon icon;
	
	public ItemCrystalwell() {
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
		
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:crystalwell");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1) {
		return this.icon;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItemDamage() >= 100)
		{
			if(!world.isRemote)
			{
				Iterator count = Aspect.getPrimalAspects().iterator();

				while(count.hasNext()) {
				   Aspect aspect = (Aspect)count.next();
				   short amount = (short)(world.rand.nextInt(3) + 4);
				   Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getDisplayName(), aspect, amount);
				   //TODO: Does this work?
				   //Thaumcraft.proxy.getResearchManager().updateAspectNBT(player);
				   Thaumcraft.packetPipeline.sendTo(new PacketAspectPool(aspect.getTag(), Short.valueOf((short)amount), Short.valueOf(Thaumcraft.proxy.getPlayerKnowledge().getAspectPoolFor(player.getCommandSenderName(), aspect))), (EntityPlayerMP)player);
				}
			}
			player.swingItem();
			return ItemApi.getItem("itemInkwell", 100);
		}
		else
			return stack;
	}
	
	/**
	* Render Pass sensitive version of hasEffect()
	*/
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack, int pass)
	{
		return stack.getItemDamage() >= 100;
	}

}