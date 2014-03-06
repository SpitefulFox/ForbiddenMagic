package com.spiteful.forbidden.items;

import com.spiteful.forbidden.*;

import java.util.List;
import java.util.Iterator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.PacketHandler;

public class ItemCrystalwell extends Item implements IScribeTools {

	@SideOnly(Side.CLIENT)
	public Icon icon;
	
	public ItemCrystalwell(int id) {
		super(id);
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		this.icon = ir.registerIcon("forbidden:crystalwell");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
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
				   Thaumcraft.proxy.playerKnowledge.addAspectPool(player.username, aspect, amount);
				   Thaumcraft.proxy.getResearchManager().updateAspectNBT(player);
				   PacketHandler.sendAspectPoolPacket(aspect.getTag(), amount, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.username, aspect), (EntityPlayerMP)player);
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
	public boolean hasEffect(ItemStack stack, int pass)
	{
		return stack.getItemDamage() >= 100;
	}

}