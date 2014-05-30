package com.spiteful.forbidden.client;

import com.spiteful.forbidden.CommonProxy;
import com.spiteful.forbidden.tiles.TileEntityWrathCage;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderInfo(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWrathCage.class, new TileEntityWrathCageRenderer());
	}

}