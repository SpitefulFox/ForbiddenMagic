package fox.spiteful.forbidden.items;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Iterator;
import java.util.List;

public class ItemBoundwell extends Item implements IScribeTools, IBindable{
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemBoundwell() {
        maxStackSize = 1;
        canRepair = false;
        setMaxDamage(100);
        setCreativeTab(Forbidden.tab);
        setHasSubtypes(false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("forbidden:boundwell");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1) {
        return this.icon;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);
        if (itemstack.getItemDamage() >= 100) {
            player.swingItem();
            if (!world.isRemote) {
                if (player.capabilities.isCreativeMode)
                    itemstack.setItemDamage(0);
                else {
                    SoulNetworkHandler.syphonAndDamageFromNetwork(itemstack, player, 10000);
                    if(player.getHealth() > 0){
                        itemstack.setItemDamage(0);
                    }
                    else
                        return itemstack;
                }
                Aspect aspect;
                short amount;
                for(Iterator count = Aspect.getPrimalAspects().iterator(); count.hasNext(); PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), Short.valueOf(amount), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), aspect))), (EntityPlayerMP)player))
                {
                    aspect = (Aspect)count.next();
                    amount = (short)(world.rand.nextInt(4) + 4);
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), aspect, amount);
                    ResearchManager.scheduleSave(player);
                }
            }
        }
        return itemstack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean z) {
        if (stack.hasTagCompound()) {
            list.add("");
            list.add(StatCollector.translateToLocal("tooltip.currentowner") + stack.stackTagCompound.getString("ownerName"));
        }
    }

    /**
     * Render Pass sensitive version of hasEffect()
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return stack.getItemDamage() >= 100;
    }

}
