package fox.spiteful.forbidden.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemTaintCoal extends Item {

    public ItemTaintCoal(){
        super();
        setCreativeTab(Forbidden.tab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("forbidden:taint_charcoal");
    }
}
