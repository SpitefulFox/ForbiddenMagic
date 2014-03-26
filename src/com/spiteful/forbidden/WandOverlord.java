package com.spiteful.forbidden;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.wands.IWandFocus;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandOverlord implements IWandTriggerManager
{
	public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event)
	{
		if(event == 1 && side == 1)
			return hellfire(world, wand, player, x, y, z, side);
		return false;
	}
	
	public boolean hellfire(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side)
	{
		if(world.isRemote || wand == null || !(wand.getItem() instanceof ItemWandCasting) || ((ItemWandCasting)wand.getItem()).getFocus(wand) != null
			|| !ResearchManager.isResearchComplete(player.username, "HELLFIRE") || !player.canPlayerEdit(x, y, z, side, new ItemStack(Item.fireballCharge)))
			return false;
		
		if(world.isAirBlock(x, y + 1, z) && ((ItemWandCasting)wand.getItem()).consumeVis(wand, player, Aspect.FIRE, 2))
		{
			world.playSoundEffect((double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
			world.setBlock(x, y + 1, z, Block.fire.blockID);
			player.swingItem();
			return true;
		}
		else
			return false;
	}
}