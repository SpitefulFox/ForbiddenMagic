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
    public static Block taintLog;
    public static Block taintPlanks;
    public static Block taintLeaves;
    public static Block taintSapling;
    public static Block taintStone;

    public static void addBlocks() {
        arcaneCake = GameRegistry.registerBlock(new BlockArcaneCake().setBlockName("ArcaneCake"), "ArcaneCake");

        wrathCage = GameRegistry.registerBlock(new BlockWrathCage().setBlockName("WrathCage"), "WrathCage");
        GameRegistry.registerTileEntity(TileEntityWrathCage.class, "WrathCage");

        blackFlower = GameRegistry.registerBlock(new BlockBlackFlower().setBlockName("InkFlower"), "InkFlower");

        starBlock = GameRegistry.registerBlock(new BlockResource().setBlockName("StarBlock"), "StarBlock");
        OreDictionary.registerOre("blockNetherStar", new ItemStack(starBlock, 1, 0));

        roseBush = GameRegistry.registerBlock(new BlockRoseBush(), "UmbralBush");

        taintLog = GameRegistry.registerBlock(new BlockLogTainted().setBlockName("TaintLog"), "TaintLog");
        taintPlanks = GameRegistry.registerBlock(new BlockPlanksTainted().setBlockName("TaintPlank"), "TaintPlank");
        taintLeaves = GameRegistry.registerBlock(new BlockLeavesTainted().setBlockName("TaintLeaves"), "TaintLeaves");
        taintSapling = GameRegistry.registerBlock(new BlockSaplingTainted().setBlockName("TaintSapling"), "TaintSapling");

        taintStone = GameRegistry.registerBlock(new BlockStoneTainted(), ItemBlockStoneTainted.class, "TaintBlock");
    }
}