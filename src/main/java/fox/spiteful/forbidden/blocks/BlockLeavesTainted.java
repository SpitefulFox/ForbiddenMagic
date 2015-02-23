package fox.spiteful.forbidden.blocks;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.items.ForbiddenItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

import java.lang.reflect.Field;
import java.util.Random;

public class BlockLeavesTainted extends BlockLeaves
{
    int[] decayThingy;
    @SideOnly(Side.CLIENT)
    private IIcon opaqueIcon;
    @SideOnly(Side.CLIENT)
    private IIcon transparentIcon;

    public BlockLeavesTainted()
    {
        super();
        setCreativeTab(Forbidden.tab);
        setLightOpacity(0);
        setHardness(0.2F);
        setStepSound(ConfigBlocks.blockTaint.stepSound);
        try {
            Field mat = ReflectionHelper.findField(Block.class, "blockMaterial", "field_149764_J");
            mat.set(this, Config.taintMaterial);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return Item.getItemFromBlock(ForbiddenBlocks.taintSapling);
    }

    /**
    * Chance of sapling dropping
     */
    @Override
    protected int func_150123_b(int p_150123_1_)
    {
        return 60;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess iba, int x, int y, int z, int side)
    {
        return !Blocks.leaves.isOpaqueCube() || super.shouldSideBeRendered(iba, x, y, z, side);
    }
    @Override
    public boolean isOpaqueCube()
    {
        return Blocks.leaves.isOpaqueCube();
    }
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return !Blocks.leaves.isOpaqueCube() ? transparentIcon : opaqueIcon;
    }
    @Override
    public String[] func_150125_e()
    {
        return new String[0];
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        opaqueIcon = register.registerIcon("forbidden:taint_leaves_opaque");
        transparentIcon = register.registerIcon("forbidden:taint_leaves");
    }

    @Override
    protected void func_150124_c(World world, int x, int y, int z, int meta, int wat)
    {
        if (world.rand.nextInt(20) == 0)
        {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(ForbiddenItems.taintFruit, 1, 0));
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            int l = world.getBlockMetadata(x, y, z);

            if ((l & 8) != 0 && (l & 4) == 0)
            {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.decayThingy == null)
                {
                    this.decayThingy = new int[b1 * b1 * b1];
                }

                int l1;

                if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
                {
                    int i2;
                    int j2;

                    for (l1 = -b0; l1 <= b0; ++l1)
                    {
                        for (i2 = -b0; i2 <= b0; ++i2)
                        {
                            for (j2 = -b0; j2 <= b0; ++j2)
                            {
                                Block block = world.getBlock(x + l1, y + i2, z + j2);

                                if (block != ForbiddenBlocks.taintLog)
                                {
                                    if (block.isLeaves(world, x + l1, y + i2, z + j2))
                                    {
                                        this.decayThingy[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                    }
                                    else
                                    {
                                        this.decayThingy[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                    }
                                }
                                else
                                {
                                    this.decayThingy[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1)
                    {
                        for (i2 = -b0; i2 <= b0; ++i2)
                        {
                            for (j2 = -b0; j2 <= b0; ++j2)
                            {
                                for (int k2 = -b0; k2 <= b0; ++k2)
                                {
                                    if (this.decayThingy[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
                                    {
                                        if (this.decayThingy[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        {
                                            this.decayThingy[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.decayThingy[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        {
                                            this.decayThingy[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.decayThingy[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
                                        {
                                            this.decayThingy[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.decayThingy[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
                                        {
                                            this.decayThingy[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.decayThingy[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
                                        {
                                            this.decayThingy[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.decayThingy[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
                                        {
                                            this.decayThingy[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.decayThingy[k1 * j1 + k1 * b1 + k1];

                if (l1 >= 0)
                {
                    world.setBlockMetadataWithNotify(x, y, z, l & -9, 4);
                }
                else
                {
                    this.removeLeaves(world, x, y, z);
                }
            }
        }
    }

    private void removeLeaves(World world, int x, int y, int z)
    {
        this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlockToAir(x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xFFFFFF;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int metadata)
    {
        return 0xFFFFFF;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return 0xFFFFFF;
    }

    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
}