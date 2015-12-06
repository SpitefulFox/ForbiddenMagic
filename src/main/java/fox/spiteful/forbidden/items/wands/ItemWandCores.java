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
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

import fox.spiteful.forbidden.compat.Compat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCores extends Item {

    public final String[] types = {
            "tainted",
            "infernal",
            "soul",
            "blood",
            "witchwood",
            "profane",
            "blood_inert",
            "livingwood",
            "livingwood_inert",
            "blood_staff",
            "witchwood_staff",
            "dreamwood",
            "dreamwood_inert",
            "dreamwood_staff" };
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

        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting, 1, 72);
        ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("thaumium"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("tainted"));
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 72);
        ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("thaumium"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("infernal"));
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 2000);
        ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("orichalcum"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("neutronium"));
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 2000);
        ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("orichalcum"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("neutronium_staff"));
        list.add(wand);
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 36);
        ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("iron"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("profane"));
        ((ItemWandCasting) wand.getItem()).storeAllVis(wand, new AspectList().add(Aspect.FIRE, 5000).add(Aspect.WATER, 5000).add(Aspect.EARTH, 5000).add(Aspect.AIR, 5000).add(Aspect.ORDER, 5000).add(Aspect.ENTROPY, 5000));
        list.add(wand);
        if (Compat.bm && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("alchemical"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("blood"));
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 168);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("alchemical"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("blood_staff"));
            list.add(wand);
        }
        if (Compat.am2 && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 72);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("vinteum"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("witchwood"));
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 144);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("vinteum"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("witchwood_staff"));
            list.add(wand);
        }
        if (Compat.botan && Config.crossWand) {
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("manasteel"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("livingwood"));
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("elementium"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("dreamwood"));
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 144);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("elementium"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("dreamwood_staff"));
            list.add(wand);
            wand = new ItemStack(ConfigItems.itemWandCasting, 1, 1);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("terrasteel"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("wood"));
            list.add(wand);
        }
        wand = new ItemStack(ConfigItems.itemWandCasting, 1, 84);
        ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("void"));
        ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("equivalent"));
        list.add(wand);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + types[stack.getItemDamage()];
    }
}
