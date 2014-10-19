package fox.spiteful.forbidden.tiles;

import fox.spiteful.forbidden.compat.DarkSignature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileEuclidaisy extends SubTileFunctional {
    private static final int cost = 9000;
    public static LexiconEntry lexicon;

    @Override
    public void onUpdate(){
        super.onUpdate();

        if(redstoneSignal > 0)
            return;

        if(mana >= cost && !supertile.getWorldObj().isRemote && supertile.getWorldObj().getTotalWorldTime() % 400 == 0) {
            Thaumcraft.proxy.burst(supertile.getWorldObj(), (double)supertile.xCoord + 0.5D, (double)supertile.yCoord + 0.5D, (double)supertile.zCoord + 0.5D, 1.0F);
            AspectList aspect;
            if(supertile.getWorldObj().rand.nextInt(10) < 4)
                aspect = (new AspectList()).add(Aspect.AURA, 2);
            else {
                Aspect[] aspects = Aspect.aspects.values().toArray(new Aspect[0]);
                aspect = (new AspectList()).add(aspects[supertile.getWorldObj().rand.nextInt(aspects.length)], 2);
            }
            ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
            ((ItemWispEssence)ess.getItem()).setAspects(ess, aspect);
            dropItem(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, ess);
            mana -= cost;
            sync();
        }
    }

    public void dropItem(World world, int x, int y, int z, ItemStack item){
        float f = 0.7F;
        double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
        entityitem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityitem);
    }

    @Override
    public int getColor(){
        return 0xFF8CFF;
    }

    @Override
    public int getMaxMana() {
        return cost;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public LexiconEntry getEntry(){
        return lexicon;
    }

    @Override
    public IIcon getIcon(){
        return ((DarkSignature)(BotaniaAPI.getSignatureForName("euclidaisy"))).getIconForStack(null);
    }
}
