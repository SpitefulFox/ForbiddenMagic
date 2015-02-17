package fox.spiteful.forbidden.items.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.DarkAspects;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.items.ForbiddenItems;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;

import java.util.List;

public class ItemFocusBlink extends ItemFocusBasic {

    private IIcon icon;
    private IIcon ornament;
    AspectList visCost = (new AspectList()).add(Aspect.ENTROPY, 300);
    AspectList fireCost = (new AspectList()).add(Aspect.ENTROPY, 300).add(Aspect.FIRE, 100);
    AspectList switchCost = (new AspectList()).add(Aspect.ENTROPY, 300).add(Aspect.ORDER, 100);
    FocusUpgradeType hellfire = ForbiddenItems.getUpgrade(Config.hellfireUpgradeID, new ResourceLocation("forbidden", "textures/misc/hellfire.png"), "forbidden.upgrade.hellfire.name", "forbidden.upgrade.hellfire.text", (new AspectList()).add(DarkAspects.NETHER, 1));
    FocusUpgradeType pandemonium = ForbiddenItems.getUpgrade(Config.pandemoniumUpgradeID, new ResourceLocation("forbidden", "textures/misc/pandemonium.png"), "forbidden.upgrade.pandemonium.name", "forbidden.upgrade.pandemonium.text", (new AspectList()).add(Aspect.DARKNESS, 1));

    public ItemFocusBlink(){
        super();
        setCreativeTab(Forbidden.tab);
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition wut) {
        MovingObjectPosition mop = BlockUtils.getTargetBlock(world, (player.prevPosX + (player.posX - player.prevPosX)),
                (player.prevPosY + (player.posY - player.prevPosY) + 1.62 - player.yOffset),
                (player.prevPosZ + (player.posZ - player.prevPosZ)),
                (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw)),
                (player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch)), false, 128.0);
        if(mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && mop.sideHit != -1){
            ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
            if(wand.consumeAllVis(itemstack, player, getVisCost(itemstack), false, false)){
                double ex = mop.hitVec.xCoord;
                double wy = mop.hitVec.yCoord;
                double zee = mop.hitVec.zCoord;
                switch(mop.sideHit){
                    case 0: wy -= 2.0;
                        break;
                    case 1: break;
                    case 2: zee -= 0.5;
                        break;
                    case 3: zee += 0.5;
                        break;
                    case 4: ex -= 0.5;
                        break;
                    case 5: ex += 0.5;
                        break;
                }
                world.playAuxSFXAtEntity((EntityPlayer) null, 1009, (int) player.posX, (int) player.posY, (int) player.posZ, 0);
                for (int k = 0; k < 8; ++k)
                {
                    world.spawnParticle("smoke", player.posX + (world.rand.nextDouble() - 0.5D) * (double)player.width, player.posY + world.rand.nextDouble() * (double)player.height - 0.25D, player.posZ + (world.rand.nextDouble() - 0.5D) * (double)player.width, 0, 0, 0);
                }
                double range = 3.0 + 1.5 * wand.getFocusEnlarge(itemstack);
                if(this.isUpgradedWith(wand.getFocusItem(itemstack), hellfire)) {
                    List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(ex - range, wy - range, zee - range, ex + range, wy + range, zee + range));
                    for (EntityLivingBase entity : entities) {
                        if(entity == player)
                            continue;
                        for (int k = 0; k < 8; ++k) {
                            world.spawnParticle("flame", entity.posX + (world.rand.nextDouble() - 0.5D) * (double) entity.width, entity.posY + world.rand.nextDouble() * (double) entity.height - 0.25D, entity.posZ + (world.rand.nextDouble() - 0.5D) * (double) entity.width, 0, 0, 0);
                        }
                        int potency = wand.getFocusPotency(itemstack);
                        entity.attackEntityFrom((new EntityDamageSource("fireball", player)).setFireDamage(), 3 + 3 * potency);
                        entity.setFire(3 + 3 * potency);
                    }
                }
                else if(this.isUpgradedWith(wand.getFocusItem(itemstack), pandemonium)){
                    List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(ex - range, wy - range, zee - range, ex + range, wy + range, zee + range));
                    for (EntityLivingBase entity : entities) {
                        if(!(entity instanceof IMob))
                            continue;
                        for (int k = 0; k < 8; ++k) {
                            world.spawnParticle("smoke", entity.posX + (world.rand.nextDouble() - 0.5D) * (double) entity.width, entity.posY + world.rand.nextDouble() * (double) entity.height - 0.25D, entity.posZ + (world.rand.nextDouble() - 0.5D) * (double) entity.width, 0, 0, 0);
                        }
                        entity.setPositionAndUpdate(player.posX, player.posY, player.posZ);
                    }
                }
                player.setPositionAndUpdate(ex, wy, zee);
                world.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)player.posX, (int)player.posY, (int)player.posZ, 0);
                for (int k = 0; k < 8; ++k)
                {
                    world.spawnParticle("flame", player.posX + (world.rand.nextDouble() - 0.5D) * (double)player.width, player.posY + world.rand.nextDouble() * (double)player.height - 0.25D, player.posZ + (world.rand.nextDouble() - 0.5D) * (double)player.width, 0, 0, 0);
                }
                wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false);
                player.swingItem();
            }
        }
        return itemstack;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("forbidden:focus_blink");
        this.ornament = ir.registerIcon("forbidden:focus_blink_orn");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int par1, int renderPass) {
        return renderPass == 1 ? this.icon : this.ornament;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getFocusColor(ItemStack item){
        return 0x909090;
    }

    @Override
    public IIcon getOrnament(ItemStack focusstack) {
        return ornament;
    }

    @Override
    public AspectList getVisCost(ItemStack item){
        return this.isUpgradedWith(item, hellfire) ? fireCost : this.isUpgradedWith(item, pandemonium) ? switchCost : visCost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack item, int rank){
        switch(rank){
            case 1:
                return new FocusUpgradeType[] {FocusUpgradeType.frugal};
            case 2:
                return new FocusUpgradeType[] {hellfire, pandemonium, FocusUpgradeType.frugal};
            case 3:
            case 4:
            case 5:
                if(this.isUpgradedWith(item, hellfire))
                    return new FocusUpgradeType[] {FocusUpgradeType.potency, FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
                else if(this.isUpgradedWith(item, pandemonium))
                    return new FocusUpgradeType[] {FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
                else
                    return new FocusUpgradeType[] {FocusUpgradeType.frugal};
            default:
                return null;
        }
    }
}
