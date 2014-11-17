package fox.spiteful.forbidden.compat;

import WayofTime.alchemicalWizardry.api.alchemy.energy.ReagentRegistry;
import WayofTime.alchemicalWizardry.api.rituals.IMasterRitualStone;
import WayofTime.alchemicalWizardry.api.rituals.RitualComponent;
import WayofTime.alchemicalWizardry.api.rituals.RitualEffect;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.potions.PotionWarpWard;

import java.util.ArrayList;
import java.util.List;

public class RitualSanity extends RitualEffect
{
    public static final int crystallosDrain = 4;

    @Override
    public void performEffect(IMasterRitualStone ritualStone)
    {
        String owner = ritualStone.getOwner();

        int currentEssence = SoulNetworkHandler.getCurrentEssence(owner);
        World world = ritualStone.getWorld();
        int x = ritualStone.getXCoord();
        int y = ritualStone.getYCoord();
        int z = ritualStone.getZCoord();

        int timeDelay = 600;

        if (world.getWorldTime() % timeDelay != 0)
        {
            return;
        }

        boolean crazy = world.getWorldTime() % (timeDelay * 4) == 0;

        boolean hasCrystallos = this.canDrainReagent(ritualStone, ReagentRegistry.crystallosReagent, crystallosDrain, false);

        int range = 15 * (hasCrystallos ? 3 : 1);
        int vertRange = 15 * (hasCrystallos ? 3 : 1);

        float posX = x + 0.5f;
        float posY = y + 0.5f;
        float posZ = z + 0.5f;
        List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - 0.5f, posY - 0.5f, posZ - 0.5f, posX + 0.5f, posY + 0.5f, posZ + 0.5f).expand(range, vertRange, range));
        int entityCount = 0;

        for (EntityPlayer player : list)
        {
            entityCount ++;
        }

        int cost = this.getCostPerRefresh();

        if (currentEssence < cost * entityCount)
        {
            SoulNetworkHandler.causeNauseaToPlayer(owner);

        } else
        {
            entityCount = 0;

            for (EntityPlayer player : list)
            {
                PotionEffect effect = player.getActivePotionEffect(PotionWarpWard.instance);
                if (effect == null || effect.getDuration() <= timeDelay)
                {
                    player.addPotionEffect(new PotionEffect(PotionWarpWard.instance.id, timeDelay + 2, 0));
                    entityCount++;
                    if(crazy && world.rand.nextInt(35) <= 3)
                        Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(player.getDisplayName(), 1 + world.rand.nextInt(3));
                }
            }

            if (entityCount > 0)
            {
                if (hasCrystallos)
                {
                    this.canDrainReagent(ritualStone, ReagentRegistry.crystallosReagent, crystallosDrain, true);
                }
                SoulNetworkHandler.syphonFromNetwork(owner, cost * entityCount);
            }
        }
    }

    @Override
    public int getCostPerRefresh()
    {
        return 15000;
    }

    @Override
    public List<RitualComponent> getRitualComponentList()
    {
        ArrayList<RitualComponent> sanityRitual = new ArrayList();
        sanityRitual.add(new RitualComponent(4, 1, 0, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(-4, 1, 0, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(0, 1, 4, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(0, 1, -4, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(4, 2, 0, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(-4, 2, 0, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(0, 2, 4, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(0, 2, -4, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(4, 3, 0, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(-4, 3, 0, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(0, 3, 4, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(0, 3, -4, RitualComponent.DUSK));
        sanityRitual.add(new RitualComponent(4, 4, 0, RitualComponent.AIR));
        sanityRitual.add(new RitualComponent(-4, 4, 0, RitualComponent.AIR));
        sanityRitual.add(new RitualComponent(0, 4, 4, RitualComponent.AIR));
        sanityRitual.add(new RitualComponent(0, 4, -4, RitualComponent.AIR));
        sanityRitual.add(new RitualComponent(4, 4, 1, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(-4, 4, 1, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(1, 4, 4, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(1, 4, -4, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(4, 4, -1, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(-4, 4, -1, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(-1, 4, 4, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(-1, 4, -4, RitualComponent.EARTH));
        sanityRitual.add(new RitualComponent(3, 4, 0, RitualComponent.FIRE));
        sanityRitual.add(new RitualComponent(-3, 4, 0, RitualComponent.FIRE));
        sanityRitual.add(new RitualComponent(0, 4, 3, RitualComponent.FIRE));
        sanityRitual.add(new RitualComponent(0, 4, -3, RitualComponent.FIRE));

        return sanityRitual;
    }
}