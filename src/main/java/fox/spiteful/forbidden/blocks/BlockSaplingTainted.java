package fox.spiteful.forbidden.blocks;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.WorldGenTaintedTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class BlockSaplingTainted extends BlockSapling
{
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockSaplingTainted()
    {
        super();
        setCreativeTab(Forbidden.tab);
        setStepSound(ConfigBlocks.blockTaint.stepSound);
        try {
            Field mat = ReflectionHelper.findField(Block.class, "blockMaterial", "field_149764_J");
            mat.set(this, Config.taintMaterial);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Grow the tree
     */
    @Override
    public void func_149878_d(World world, int x, int y, int z, Random rand)
    {
        if(!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z);

            world.setBlockToAir(x, y, z);
            WorldGenTaintedTree tree = new WorldGenTaintedTree(true);
            if (!tree.generate(world, rand, x, y, z))
            {
                world.setBlock(x, y, z, this, meta, 2);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public Item getItemDropped(int wut, Random random, int yeah)
    {
        return Item.getItemFromBlock(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        icon = register.registerIcon("forbidden:sapling_taint");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icon;
    }
}