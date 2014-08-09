package fox.spiteful.forbidden.items;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGluttonyShard extends ItemFood {
	IIcon icon;

	public ItemGluttonyShard() {
		super(2, 0.1F, false);
		this.setCreativeTab(Forbidden.tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:gluttonyshard");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return this.icon;
	}
}