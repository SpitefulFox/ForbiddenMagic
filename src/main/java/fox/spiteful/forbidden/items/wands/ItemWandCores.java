package fox.spiteful.forbidden.items.wands;

import java.util.List;

import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.ItemApi;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

import fox.spiteful.forbidden.compat.Compat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCores extends Item {

	public final String[] types = { "tainted", "infernal", "soul", "blood", "witchwood", "totem", "blood_inert", "livingwood", "livingwood_inert", "blood_staff", "witchwood_staff" };
	public IIcon[] icon;

	public ItemWandCores() {
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir) {
		icon = new IIcon[types.length];
		for (int x = 0; x < types.length; x++)
			this.icon[x] = ir.registerIcon("forbidden:wand_rod_" + types[x]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs xCreativeTabs, List list) {
		for (int x = 0; x < types.length; x++) {
			list.add(new ItemStack(this, 1, x));
		}

		ItemStack wand = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("thaumium"));
		((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("tainted"));
		list.add(wand);
		wand = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("thaumium"));
		((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("infernal"));
		list.add(wand);
		wand = ItemApi.getItem("itemWandCasting", 2000);
		((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("orichalcum"));
		((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("neutronium"));
		list.add(wand);
		wand = ItemApi.getItem("itemWandCasting", 2000);
		((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("orichalcum"));
		((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("neutronium_staff"));
		list.add(wand);
		/*if (Compat.pb) {
			wand = ItemApi.getItem("itemWandCasting", 84);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("soul"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("soul"));
			list.add(wand);
		}*/
		if (Compat.bm && Config.crossWand) {
			wand = ItemApi.getItem("itemWandCasting", 84);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("alchemical"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("blood"));
			list.add(wand);
			wand = ItemApi.getItem("itemWandCasting", 168);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("alchemical"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("blood_staff"));
			list.add(wand);
		}
		if (Compat.am2 && Config.crossWand) {
			wand = ItemApi.getItem("itemWandCasting", 72);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("vinteum"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("witchwood"));
			list.add(wand);
			wand = ItemApi.getItem("itemWandCasting", 144);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("vinteum"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("witchwood_staff"));
			list.add(wand);
		}
		if (Compat.botan && Config.crossWand) {
			wand = ItemApi.getItem("itemWandCasting", 84);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("manasteel"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("livingwood"));
			list.add(wand);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + types[stack.getItemDamage()];
	}
}
