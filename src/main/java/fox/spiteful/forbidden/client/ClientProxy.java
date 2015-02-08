package fox.spiteful.forbidden.client;

import fox.spiteful.forbidden.CommonProxy;
import fox.spiteful.forbidden.tiles.TileEntityWrathCage;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInfo() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWrathCage.class, new TileEntityWrathCageRenderer());
    }

}