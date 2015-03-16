package fox.spiteful.forbidden.tiles;

import fox.spiteful.forbidden.compat.DarkSignature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.List;

public class SubTileWhisperweed extends SubTileFunctional {
    private static final int cost = 6000;
    private final static int range = 3;
    public static LexiconEntry lexicon;

    @Override
    public void onUpdate(){
        super.onUpdate();

        if(redstoneSignal > 0)
            return;

        if(!supertile.getWorldObj().isRemote && mana >= cost && this.ticksExisted % 300 == 0) {
            List<EntityPlayer> players = supertile.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - range, supertile.yCoord - range, supertile.zCoord - range, supertile.xCoord + range + 1, supertile.yCoord + range + 1, supertile.zCoord + range + 1));
            if(players.size() > 0) {
                EntityPlayer player = players.get(supertile.getWorldObj().rand.nextInt(players.size()));
                int amt = 1 + player.worldObj.rand.nextInt(3);
                if(player.worldObj.rand.nextInt(10) < 2){
                    amt += 1 + player.worldObj.rand.nextInt(3);
                    Thaumcraft.addWarpToPlayer(player, 1 + player.worldObj.rand.nextInt(5), true);
                }

                for(int a = 0; a < amt; ++a) {
                    Aspect aspect;
                    if(player.worldObj.rand.nextInt(25) > 2)
                        aspect = (Aspect)Aspect.getPrimalAspects().get(player.worldObj.rand.nextInt(6));
                    else
                        aspect = (Aspect)Aspect.getCompoundAspects().get(player.worldObj.rand.nextInt(Aspect.getCompoundAspects().size()));
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), aspect, (short)1);
                    PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), (short)1, Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), aspect))), (EntityPlayerMP)player);
                    ResearchManager.scheduleSave(player);
                }
                mana -= cost;
            }

        }
    }

    @Override
    public int getColor(){
        return 0x745380;
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
        return BotaniaAPI.getSignatureForName("whisperweed").getIconForStack(null);
    }
}
