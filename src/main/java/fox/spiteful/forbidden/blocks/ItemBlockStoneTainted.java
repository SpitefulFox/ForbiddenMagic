package fox.spiteful.forbidden.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStoneTainted extends ItemBlock {
    public ItemBlockStoneTainted(Block block){
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "tile.TaintStone_" + (itemStack.getItemDamage() == 0 ? "Smooth" : "Brick");
    }
}
