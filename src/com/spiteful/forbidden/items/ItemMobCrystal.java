package com.spiteful.forbidden.items;

import com.spiteful.forbidden.*;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

public class ItemMobCrystal extends Item {

	Icon[] icons;
	
	public ItemMobCrystal(int par1) {
		super(par1);
        this.setMaxStackSize(1);
        //this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(Forbidden.crysTab);
    }
	
	@SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
            NBTTagString nbttagstring = (NBTTagString)nbttagcompound.getTag("mob");

            if (nbttagstring != null)
            {
                Aspect mobAspect = Config.spawnerMobs.get(nbttagstring.toString());
				if(mobAspect != null)
					par3List.add(mobAspect.getName());
            }
        }
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        icons = new Icon[2];

        icons[0] = ir.registerIcon("forbidden:emptycrystal");
		icons[1] = ir.registerIcon("forbidden:mobcrystal");
    }	
	
	@SideOnly(Side.CLIENT)
	/**
     * Player, Render pass, and item usage sensitive version of getIconIndex.
     *
     * @param stack The item stack to get the icon for. (Usually this, and usingItem will be the same if usingItem is not null)
     * @param renderPass The pass to get the icon for, 0 is default.
     * @param player The player holding the item
     * @param usingItem The item the player is actively using. Can be null if not using anything.
     * @param useRemaining The ticks remaining for the active item.
     * @return The icon index
     */
    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (stack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            NBTTagString nbttagstring = (NBTTagString)(nbttagcompound.getTag("mob"));
			
			if(nbttagstring != null)
				return icons[1];
		}
		
		return icons[0];
    }
	
	@SideOnly(Side.CLIENT)
	/**
     * Returns the icon index of the stack given as argument.
     */
    public Icon getIconIndex(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
            NBTTagString nbttagstring = (NBTTagString)(nbttagcompound.getTag("mob"));
			
			if(nbttagstring != null)
				return icons[1];
		}
		
		return icons[0];
    }

    @SideOnly(Side.CLIENT)
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
		
		for (String mob: Config.spawnerMobs.keySet()){
			ItemStack crystal = new ItemStack(par1, 1, 0);
			NBTTagString mobTag = new NBTTagString("mob", mob);
            crystal.setTagInfo("mob", mobTag);
			par3List.add(crystal);
		}
    }
	
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
            NBTTagString nbttagstring = (NBTTagString)nbttagcompound.getTag("mob");
			
			if(nbttagstring != null)
				return ("" + StatCollector.translateToLocal("entity." + nbttagstring.toString() + ".name") + " " + StatCollector.translateToLocal("item.MobCrystal.name")).trim();
		}
		
		return ("" + StatCollector.translateToLocal("item.MobCrystalEmpty.name")).trim();
    }
}
