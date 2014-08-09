package fox.spiteful.forbidden;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;

public class WandOverlord implements IWandTriggerManager {
	@Override
	public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event) {
		if (event == 1 && side == 1)
			return hellfire(world, wand, player, x, y, z, side);
		return false;
	}

	public boolean hellfire(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side) {
		if (world.isRemote || wand == null || !(wand.getItem() instanceof ItemWandCasting) || ((ItemWandCasting) wand.getItem()).getFocus(wand) != null || !ResearchManager.isResearchComplete(player.getCommandSenderName(), "HELLFIRE") || !player.canPlayerEdit(x, y, z, side, new ItemStack(Items.fire_charge)))
			return false;

		if (world.isAirBlock(x, y + 1, z) && ((ItemWandCasting) wand.getItem()).consumeVis(wand, player, Aspect.FIRE, 2, false)) {
			world.playSoundEffect((double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D, "fire.ignite", 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
			world.setBlock(x, y + 1, z, Blocks.fire);
			player.swingItem();
			return true;
		} else
			return false;
	}
}