package com.spiteful.forbidden.client;

import com.spiteful.forbidden.*;
import com.spiteful.forbidden.tiles.*;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderInfo(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWrathCage.class, new TileEntityWrathCageRenderer());
	}

}