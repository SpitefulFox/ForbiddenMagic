package fox.spiteful.forbidden.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.potions.PotionThaumarhia;

public class ItemFruitTainted extends ItemFood {

    public ItemFruitTainted(){
        super(4, 0.8F, false);
        setCreativeTab(Forbidden.tab);
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if(!world.isRemote && player instanceof EntityPlayerMP) {
            Thaumcraft.addStickyWarpToPlayer(player, 1);
            player.addPotionEffect(getEffect(PotionFluxTaint.instance.id, 600, false));
            player.addPotionEffect(getEffect(Potion.hunger.id, 600, false));
            if(world.rand.nextFloat() < 0.4F){
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("warp.text.15")));
                player.addPotionEffect(getEffect(PotionThaumarhia.instance.id, 600, true));
            }
        }
        return super.onEaten(stack, world, player);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("forbidden:taint_fruit");
    }

    private PotionEffect getEffect(int id, int duration, boolean ambient){
        PotionEffect potion = new PotionEffect(id, duration, 0, ambient);
        potion.getCurativeItems().clear();
        return potion;
    }

}
