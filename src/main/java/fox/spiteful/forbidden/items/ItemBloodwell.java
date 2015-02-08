package fox.spiteful.forbidden.items;

import java.util.List;

import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBloodwell extends Item implements IScribeTools, IBindable {

    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemBloodwell() {
        maxStackSize = 1;
        canRepair = false;
        setMaxDamage(5);
        setCreativeTab(Forbidden.tab);
        setHasSubtypes(false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("forbidden:bloodwell");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity fauxplayer, int z, boolean par5) {
        if (Compat.bm && stack.getItemDamage() > 0 && stack.hasTagCompound()) {
            EntityPlayer player = null;
            if (fauxplayer instanceof EntityPlayer)
                player = (EntityPlayer) fauxplayer;
            else
                return;

            if (player.capabilities.isCreativeMode)
                stack.setItemDamage(0);
            else if (SoulNetworkHandler.syphonFromNetwork(stack, 25) > 0)
                stack.setItemDamage(stack.getItemDamage() - 1);
            else if (player.getHealth() > 6) {
                player.setHealth(player.getHealth() - 2);
                stack.setItemDamage(stack.getItemDamage() - 1);
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
           SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);
        return itemstack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean z) {
        if (stack.hasTagCompound()) {
            list.add("");
            list.add(StatCollector.translateToLocal("tooltip.currentowner") + stack.stackTagCompound.getString("ownerName"));
        }
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if (damage > 0) {
            if (SoulNetworkHandler.syphonFromNetwork(stack, 25 * damage) > 0)
                super.setDamage(stack, 0);
            else
                super.setDamage(stack, damage);
        } else
            super.setDamage(stack, damage);
    }
}