package fox.spiteful.forbidden.blocks;

import fox.spiteful.forbidden.Forbidden;
import net.minecraft.block.Block;
import thaumcraft.common.config.Config;

public class BlockPlanksTainted extends Block{

    public BlockPlanksTainted(){
        super(Config.taintMaterial);
        setCreativeTab(Forbidden.tab);
        setStepSound(Block.soundTypeWood);
        setHardness(2.0F);
        setResistance(5.0F);
        setBlockTextureName("forbidden:taint_planks");
        this.setHarvestLevel("axe", 0);
    }

}
