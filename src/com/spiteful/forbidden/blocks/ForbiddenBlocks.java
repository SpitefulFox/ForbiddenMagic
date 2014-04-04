package com.spiteful.forbidden.blocks;

import com.spiteful.forbidden.*;
import com.spiteful.forbidden.tiles.*;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Arrays;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;

public class ForbiddenBlocks
{
	public static Block arcaneCake;
	public static Block blackFlower;
	public static Block wrathCage;
	public static Block starBlock;

	public static void addBlocks()
	{
		arcaneCake = new BlockArcaneCake(Config.arcaneCakeBlockID).setHardness(0.5F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("ArcaneCake");
		GameRegistry.registerBlock(arcaneCake, "ArcaneCake");
		
		wrathCage = new BlockWrathCage(Config.wrathCageID).setHardness(5.0F).setResistance(2000.0F).setStepSound(Block.soundMetalFootstep).setTextureName("forbidden:spirit_box").setUnlocalizedName("WrathCage");
		GameRegistry.registerBlock(wrathCage, "WrathCage");
		GameRegistry.registerTileEntity(TileEntityWrathCage.class, "WrathCage");
		
		blackFlower = new BlockBlackFlower(Config.blackFlowerBlockID).setStepSound(Block.soundGrassFootstep).setTextureName("forbidden:flower_black").setUnlocalizedName("InkFlower");
		GameRegistry.registerBlock(blackFlower, "InkFlower");
		
		starBlock = new BlockResource(Config.starBlockID).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setTextureName("forbidden:starblock").setUnlocalizedName("StarBlock");
		GameRegistry.registerBlock(starBlock, "StarBlock");
		OreDictionary.registerOre("blockNetherStar", new ItemStack(starBlock, 1, 0));
	}
}