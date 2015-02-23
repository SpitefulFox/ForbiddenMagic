package fox.spiteful.forbidden.blocks;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.LogHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.apache.logging.log4j.Level;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class BlockLogTainted extends BlockLog
{
    @SideOnly(Side.CLIENT)
    private IIcon sides;
    @SideOnly(Side.CLIENT)
    private IIcon ends;
    @SideOnly(Side.CLIENT)
    private IIcon gross;
    @SideOnly(Side.CLIENT)
    private IIcon gross2;

    public BlockLogTainted()
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
        this.setHarvestLevel("axe", 0);
    }

    @Override
    public int damageDropped(int p_149692_1_)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        sides = register.registerIcon("forbidden:taint_log_side");
        ends = register.registerIcon("forbidden:taint_log_bottom");
        gross = register.registerIcon("forbidden:taint_log_side_gross");
        gross2 = register.registerIcon("forbidden:taint_log_side_gross2");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        int logDirection = meta & 12;
        return logDirection == 0 && (side == 1 || side == 0) ? ends : (logDirection == 4 && (side == 5 || side == 4) ? ends : (logDirection == 8 && (side == 2 || side == 3) ? ends : sides));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        int meta = world.getBlockMetadata(x, y, z);
        int logDirection = meta & 12;
        if((logDirection == 0 && (side == 1 || side == 0)) || (logDirection == 4 && (side == 5 || side == 4)) || (logDirection == 8 && (side == 2 || side == 3)))
            return ends;
        else {
            Random r = new Random((long)(side + y + x * z));
            return r.nextInt(100) < 75 ? sides : r.nextBoolean() ? gross : gross2;
        }
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta)
    {
        return sides;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int meta)
    {
        return ends;
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

}