package fox.spiteful.forbidden.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.api.IRepairable;
import thaumcraft.common.lib.utils.BlockUtils;

import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.enchantments.DarkEnchantments;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IWarpingGear;

import java.util.List;

public class ItemMorphPickaxe extends ItemPickaxe implements IRepairable, IWarpingGear {
    public IIcon[] icon;
    int side;

    public ItemMorphPickaxe(ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.setCreativeTab(Forbidden.tab);
        this.setHarvestLevel("pickaxe", 4);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        icon = new IIcon[2];
        this.icon[0] = ir.registerIcon("forbidden:chameleonpick");
        this.icon[1] = ir.registerIcon("forbidden:eyepick");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamageForRenderPass(int par1, int renderPass) {
        return renderPass != 1 ? icon[0] : icon[1];
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    @Override
    public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ForbiddenItems.deadlyShards, 1, 1)) ? true : super.getIsRepairable(stack, stack2);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (player.isSneaking() && itemstack.hasTagCompound() && getMaxDamage() - itemstack.getItemDamage() > 5) {
            NBTTagCompound tags = itemstack.getTagCompound();
            byte phase = tags.getByte("phase");
            if(tags.hasKey("ench")){
                NBTTagList enchants = itemstack.getEnchantmentTagList();
                tags.setTag("enchants" + phase, enchants);
            }
            else
                tags.removeTag("enchants" + phase);

            if (tags.hasKey("display")) {
                String name = tags.getCompoundTag("display").getString("Name");
                if (name != null && !name.equals(""))
                    tags.getCompoundTag("display").setString("Name" + phase, name);
                else
                    tags.getCompoundTag("display").removeTag("Name" + phase);
            }
            if (++phase > 2)
                phase = 0;
            tags.setByte("phase", phase);
            if(tags.hasKey("enchants" + phase)) {
                NBTTagList enchants = (NBTTagList) (tags.getTag("enchants" + phase));
                tags.setTag("ench", enchants);
            }
            else
                tags.removeTag("ench");

            if (tags.hasKey("display")) {
                String name = tags.getCompoundTag("display").getString("Name" + phase);
                if (name != null && !name.equals(""))
                    tags.getCompoundTag("display").setString("Name", name);
                else
                    tags.getCompoundTag("display").removeTag("Name");
            }

            itemstack.setTagCompound(tags);
            itemstack.damageItem(5, player);
            player.swingItem();
            world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
        }
        return itemstack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int renderpass) {
        if (renderpass != 1)
            return 16777215;
        else {
            if (!itemstack.hasTagCompound())
                return 0x980000;
            byte phase = itemstack.getTagCompound().getByte("phase");
            if (phase == 1)
                return 0x0010CC;
            else if (phase == 2)
                return 0xE5DA00;
            else
                return 0x980000;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int fuckObfuscation, boolean fuckObfuscation2) {
        super.onUpdate(stack, world, entity, fuckObfuscation, fuckObfuscation2);
        if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.voidtouched.effectId, stack) > 0 && stack.isItemDamaged() && entity != null && entity.ticksExisted % 10 == 0 && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase)entity);
        }

    }

    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.voidtouched.effectId, itemstack) > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        MovingObjectPosition movingobjectposition = BlockUtils.getTargetBlock(player.worldObj, player, true);
        if(movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.side = movingobjectposition.sideHit;
        }

        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
        if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.impact.effectId, stack) <= 0)
            return true;
        if(!player.worldObj.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            if(ForgeHooks.isToolEffective(stack, block, meta)) {
                for(int aa = -1; aa <= 1; ++aa) {
                    for(int bb = -1; bb <= 1; ++bb) {
                        int xx = 0;
                        int yy = 0;
                        int zz = 0;
                        if(this.side <= 1) {
                            xx = aa;
                            zz = bb;
                        } else if(this.side <= 3) {
                            xx = aa;
                            yy = bb;
                        } else {
                            zz = aa;
                            yy = bb;
                        }

                        if(!(player instanceof EntityPlayer) || world.canMineBlock((EntityPlayer)player, x + xx, y + yy, z + zz)) {
                            Block bl = world.getBlock(x + xx, y + yy, z + zz);
                            meta = world.getBlockMetadata(x + xx, y + yy, z + zz);
                            if(bl.getBlockHardness(world, x + xx, y + yy, z + zz) >= 0.0F && ForgeHooks.isToolEffective(stack, bl, meta)) {
                                stack.damageItem(1, player);
                                BlockUtils.harvestBlock(world, (EntityPlayer)player, x + xx, y + yy, z + zz, true, 2);
                            }
                        }
                    }
                }
            }
            else
                return super.onBlockDestroyed(stack, world, block, x, y, z, player);
        }

        return true;
    }

}
