package fox.spiteful.forbidden.compat;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.subtile.signature.SubTileSignature;

public class DarkSignature extends SubTileSignature {
    String name;
    IIcon icon;

    public DarkSignature(String nombre){
        name = nombre;
    }

    @Override
    public void registerIcons(IIconRegister reg){
        icon = reg.registerIcon("forbidden:" + name);
    }

    @Override
    public IIcon getIconForStack(ItemStack item){
        return icon;
    }

    @Override
    public String getUnlocalizedNameForStack(ItemStack item){
        return "tile.darkflower." + name + ".name";
    }

    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack item){
        return "tile.darkflower." + name + ".lore";
    }
}
