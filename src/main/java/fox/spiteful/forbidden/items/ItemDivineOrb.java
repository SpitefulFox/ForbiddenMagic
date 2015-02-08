package fox.spiteful.forbidden.items;

import java.util.List;

import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBloodOrb;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IWarpingGear;

public class ItemDivineOrb extends Item implements IBloodOrb, IBindable, IWarpingGear {
    public ItemDivineOrb() {
        setMaxStackSize(1);
        setCreativeTab(Forbidden.tab);
        AltarRecipeRegistry.registerAltarOrbRecipe(new ItemStack(this), 6, 140);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("forbidden:divineorb");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add(StatCollector.translateToLocal("tooltip.divineorb"));

        if (!(stack.stackTagCompound == null)) {
            list.add(StatCollector.translateToLocal("tooltip.currentowner") + stack.stackTagCompound.getString("ownerName"));
        }

    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (Compat.bm)
            SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);

        if (world != null) {
            double posX = player.posX;
            double posY = player.posY;
            double posZ = player.posZ;
            world.playSoundEffect((double) ((float) posX + 0.5F), (double) ((float) posY + 0.5F), (double) ((float) posZ + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        }
        if (!world.isRemote) {
            return itemstack;
        }

        NBTTagCompound itemTag = itemstack.stackTagCompound;

        if (itemTag == null || itemTag.getString("ownerName").equals("")) {
            return itemstack;
        }

        if(!player.capabilities.isCreativeMode)
            player.setHealth(player.getHealth() - 1);
        SoulNetworkHandler.addCurrentEssenceToMaximum(itemTag.getString("ownerName"), 200, 700000000);

        return itemstack;
    }

    @Override
    public int getMaxEssence() {
        return 80000000;
    }

    @Override
    public int getOrbLevel() {
        return 6;
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 5;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    @Override
    public boolean hasContainerItem()
    {
        return true;
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return itemStack;
    }
}