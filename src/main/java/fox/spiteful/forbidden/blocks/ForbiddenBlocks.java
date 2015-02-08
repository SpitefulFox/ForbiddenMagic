package fox.spiteful.forbidden.blocks;

import fox.spiteful.forbidden.tiles.TileEntityWrathCage;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;

public class ForbiddenBlocks {
    public static Block arcaneCake;
    public static Block blackFlower;
    public static Block wrathCage;
    public static Block starBlock;
    public static Block roseBush;

    public static void addBlocks() {
        arcaneCake = new BlockArcaneCake().setHardness(0.5F).setStepSound(Block.soundTypeCloth).setBlockName("ArcaneCake");
        GameRegistry.registerBlock(arcaneCake, "ArcaneCake");

        wrathCage = new BlockWrathCage().setHardness(5.0F).setResistance(2000.0F).setStepSound(Block.soundTypeMetal).setBlockTextureName("forbidden:spirit_box").setBlockName("WrathCage");
        GameRegistry.registerBlock(wrathCage, "WrathCage");
        GameRegistry.registerTileEntity(TileEntityWrathCage.class, "WrathCage");

        blackFlower = new BlockBlackFlower().setStepSound(Block.soundTypeGrass).setBlockTextureName("forbidden:flower_black").setBlockName("InkFlower");
        GameRegistry.registerBlock(blackFlower, "InkFlower");

        starBlock = new BlockResource().setHardness(5.0F).setStepSound(Block.soundTypeMetal).setBlockTextureName("forbidden:starblock").setBlockName("StarBlock");
        GameRegistry.registerBlock(starBlock, "StarBlock");
        OreDictionary.registerOre("blockNetherStar", new ItemStack(starBlock, 1, 0));

        roseBush = new BlockRoseBush();
        GameRegistry.registerBlock(roseBush, "UmbralBush");
    }
}