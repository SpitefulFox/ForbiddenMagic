package fox.spiteful.forbidden.items;

import java.util.Iterator;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.lib.research.ResearchManager;

public class ItemCrystalwell extends Item implements IScribeTools {

	@SideOnly(Side.CLIENT)
	public IIcon icon;

	public ItemCrystalwell() {
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);

	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:crystalwell");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1) {
		return this.icon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (stack.getItemDamage() >= 100) {
			if (!world.isRemote) {
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
			player.swingItem();
			return new ItemStack(ConfigItems.itemInkwell, 1, 100);
		} else
			return stack;
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