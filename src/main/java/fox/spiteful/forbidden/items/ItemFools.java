package fox.spiteful.forbidden.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemFools extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public ItemFools() {
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("fools");
        this.setCreativeTab(Forbidden.tab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        icons = new IIcon[5];

        icons[0] = ir.registerIcon("forbidden:poppet_blood");
        icons[1] = ir.registerIcon("forbidden:poppet_wizard");
        icons[2] = ir.registerIcon("forbidden:arthana_thaumium");
        icons[3] = ir.registerIcon("forbidden:boline_void");
        icons[4] = ir.registerIcon("forbidden:wizardHat");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int dam) {
        return this.icons[dam % icons.length];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        //int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 3);
        int i = stack.getItemDamage();
        return super.getUnlocalizedName() + "." + i;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item par1, CreativeTabs xCreativeTabs, List list) {
        for (int j = 0; j < 5; ++j) {
            list.add(new ItemStack(par1, 1, j));
        }
    }

}
