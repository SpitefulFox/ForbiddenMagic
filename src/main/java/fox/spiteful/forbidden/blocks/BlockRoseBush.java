package fox.spiteful.forbidden.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockRoseBush extends BlockBush implements IGrowable
{

    @SideOnly(Side.CLIENT)
    public IIcon top;
    @SideOnly(Side.CLIENT)
    public IIcon bottom;

    public BlockRoseBush()
    {
        super(Material.plants);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setBlockName("blackRoseBush");
        this.setCreativeTab(Forbidden.tab);
        this.setTickRandomly(true);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && world.isAirBlock(x, y + 1, z);
    }

    /**
     * checks if the block can stay, if not drop as item
     */
    protected void checkAndDropBlock(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if(meta == 0){
            if(world.getBlock(x, y - 1, z) != this){
                world.setBlockToAir(x, y, z);
            }
        }
        else if(meta == 1){
            if(world.getBlock(x, y + 1, z) != this){
                dropBlockAsItem(world, x, y, z, new ItemStack(this, 1));
                world.setBlockToAir(x, y, z);
            }
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) != this) return super.canBlockStay(world, x, y, z); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        if(world.getBlockMetadata(x, y, z) == 0)
            return world.getBlock(x, y - 1, z) == this;
        Block block = world.getBlock(x, y - 1, z);
        return block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune)
    {
        if (metadata != 1)
        {
            return null;
        }
        else
        {
            return Item.getItemFromBlock(this);
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(meta == 1)
            return bottom;
        else
            return top;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
    {
        world.setBlock(x, y, z, this, 1, 2);
        world.setBlock(x, y + 1, z, this, 0, 2);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.top = register.registerIcon("forbidden:umbral_rose_top");
        this.bottom = register.registerIcon("forbidden:umbral_rose_bottom");
    }

    @Override
    /* Whether the plant isn't fully grown?  Who knows?  Aside from Mojang. */
    public boolean func_149851_a(World world, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    @Override
    /* Whether it can accept bonemeal?  Who knows?  Aside from Mojang. */
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    @Override
    /* What happens when the block is bonemealed?  Maybe?  Fuck Obfuscation. */
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        spreadFlowers(world, x, y, z, rand);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote && rand.nextInt(10) <= 2)
            spreadFlowers(world, x, y, z, rand);
    }

    public void spreadFlowers(World world, int x, int y, int z, Random rand) {
        int i1;
        int j1;
        int k1;

        i1 = x + rand.nextInt(3) - 1;
        j1 = y + rand.nextInt(2) - rand.nextInt(2);
        k1 = z + rand.nextInt(3) - 1;

        for (int l1 = 0; l1 < 8; ++l1) {
            if (world.isAirBlock(i1, j1, k1) && this.canBlockStay(world, i1, j1, k1)) {
                x = i1;
                y = j1;
                z = k1;
            }

            i1 = x + rand.nextInt(3) - 1;
            j1 = y + rand.nextInt(2) - rand.nextInt(2);
            k1 = z + rand.nextInt(3) - 1;
        }

        if (world.isAirBlock(i1, j1, k1) && this.canBlockStay(world, i1, j1, k1)) {
            world.setBlock(i1, j1, k1, ForbiddenBlocks.blackFlower, 0, 2);
        }
    }

}