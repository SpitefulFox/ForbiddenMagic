package fox.spiteful.forbidden.items.baubles;

import baubles.api.BaublesApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.baubles.ItemAmuletVis;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class ItemSubCollar extends ItemAmuletVis {

    public IIcon icon;
    DecimalFormat myFormatter = new DecimalFormat("#######.##");

    public ItemSubCollar(){
        super();
        maxStackSize = 1;
        setCreativeTab(Forbidden.tab);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + "SubCollar";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon("forbidden:collar");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return icon;
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public int getMaxVis(ItemStack stack) {
        return 25000;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.capacity.text") + " " + this.getMaxVis(stack) / 100);
        if(stack.hasTagCompound()) {
            Iterator count = Aspect.getPrimalAspects().iterator();

            while(count.hasNext()) {
                Aspect aspect = (Aspect)count.next();
                if(stack.stackTagCompound.hasKey(aspect.getTag())) {
                    String amount = this.myFormatter.format((double)((float)stack.stackTagCompound.getInteger(aspect.getTag()) / 100.0F));
                    list.add("\u00a7" + aspect.getChatcolor() + aspect.getName() + EnumChatFormatting.RESET + " x " + amount);
                }
            }
            if(stack.stackTagCompound.hasKey("owner"))
                list.add(StatCollector.translateToLocal("tooltip.collar.owner") + " " + stack.stackTagCompound.getString("owner"));
        }

    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
    {
        if (entity.worldObj.isRemote)
        {
            return false;
        }
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer sub = (EntityPlayer)entity;
            IInventory baubles = BaublesApi.getBaubles(sub);
            if(baubles.getStackInSlot(0) == null) {
                if(!itemstack.hasTagCompound()){
                    NBTTagCompound tag = new NBTTagCompound();
                    itemstack.setTagCompound(tag);
                }
                itemstack.stackTagCompound.setString("owner", player.getDisplayName());
                baubles.setInventorySlotContents(0, itemstack.copy());
                itemstack.stackSize = 0;
                sub.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.collar.placescollar").replace("%s", player.getDisplayName())));
                player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.collar.youplacecollar").replace("%s", sub.getDisplayName())));
                return true;
            }
            else
                player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.collar.alreadywearing").replace("%s", sub.getDisplayName())));
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs xCreativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
    }

}
