package fox.spiteful.forbidden.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

import java.util.List;

public class BlockStoneTainted extends Block {

    private IIcon smooth;
    private IIcon brick;

    public BlockStoneTainted(){
        super(Config.taintMaterial);
        setHardness(2.0F);
        setResistance(10.0F);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(Forbidden.tab);
        this.setHarvestLevel("pickaxe", 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        smooth = iconRegister.registerIcon("forbidden:taint_stone_smooth");
        brick = iconRegister.registerIcon("forbidden:taint_stone_brick");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if(metadata == 0)
            return smooth;
        else
            return brick;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }
}
