package fox.spiteful.forbidden.items;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.api.IRepairable;

import fox.spiteful.forbidden.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class ItemTaintShovel extends ItemSpade implements IRepairable
{
    public IIcon icon;

    public ItemTaintShovel(ToolMaterial enumtoolmaterial)
    {
        super(enumtoolmaterial);
        this.setCreativeTab(Forbidden.tab);
        this.setHarvestLevel("shovel", 3);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
        this.icon = ir.registerIcon("forbidden:taintshovel");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return this.icon;
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }

    public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
    {
        return stack2.isItemEqual(new ItemStack(Config.thaumcraftResource.getItem(), 1, 2)) ? true : super.getIsRepairable(stack, stack2);
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if (ForgeHooks.isToolEffective(stack, block, meta) || block.getMaterial() == Config.taintMaterial)
        {
            return efficiencyOnProperMaterial;
        }

        return func_150893_a(stack, block);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        int purified = 0;
        for(int ex = x - 5;ex < x + 6;ex++){
            for(int wy = y - 4;wy < y + 5;wy++){
                for(int zee = z - 5;zee < z + 6;zee++){
                    Block target = world.getBlock(ex, wy, zee);
                    if(target != null && (target == ConfigBlocks.blockFluxGoo || target == ConfigBlocks.blockFluxGas)){
                        purified++;
                        world.setBlockToAir(ex, wy, zee);
                        float d1 = ((float) ex + world.rand.nextFloat());
                        float d2 = ((float) wy + world.rand.nextFloat());
                        float d0 = ((float) zee + world.rand.nextFloat());
                        Thaumcraft.proxy.nodeBolt(world, (float)player.posX, (float)player.posY, (float)player.posZ, d1, d2, d0);
                    }
                }
            }
        }
        if(purified > 0){
            itemstack.damageItem(Math.min(purified, 15), player);
            player.swingItem();
            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
            return true;
        }
        else
            return super.onItemUse(itemstack, player, world, x, y, z, side, par8, par9, par10);
    }
}
