package fox.spiteful.forbidden.items;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import fox.spiteful.forbidden.blocks.ForbiddenBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcaneCake extends Item {
    public ItemArcaneCake() {
        this.setCreativeTab(Forbidden.tab);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World yWorld, int z, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block = yWorld.getBlock(z, par5, par6);

        if (block == Blocks.snow_layer && (yWorld.getBlockMetadata(z, par5, par6) & 7) < 1) {
            par7 = 1;
        } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
            if (par7 == 0) {
                --par5;
            }

            if (par7 == 1) {
                ++par5;
            }

            if (par7 == 2) {
                --par6;
            }

            if (par7 == 3) {
                ++par6;
            }

            if (par7 == 4) {
                --z;
            }

            if (par7 == 5) {
                ++z;
            }
        }

        if (!player.canPlayerEdit(z, par5, par6, par7, stack)) {
            return false;
        } else if (stack.stackSize == 0) {
            return false;
        } else {
            Block cake = ForbiddenBlocks.arcaneCake;
            if (yWorld.canPlaceEntityOnSide(cake, z, par5, par6, false, par7, (Entity) null, stack)) {
                int j1 = cake.onBlockPlaced(yWorld, z, par5, par6, par7, par8, par9, par10, 0);

                if (yWorld.setBlock(z, par5, par6, cake, j1, 3)) {
                    if (yWorld.getBlock(z, par5, par6) == cake) {
                        cake.onBlockPlacedBy(yWorld, z, par5, par6, player, stack);
                        cake.onPostBlockPlaced(yWorld, z, par5, par6, j1);
                    }

                    yWorld.playSoundEffect((double) ((float) z + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.soundName, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --stack.stackSize;
                }
            }

            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
        this.itemIcon = ir.registerIcon("forbidden:cake");
    }
}
