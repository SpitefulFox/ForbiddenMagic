package fox.spiteful.forbidden;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
import fox.spiteful.forbidden.items.ForbiddenItems;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

import java.util.Random;

public class VillagerHereticManager implements IVillageTradeHandler {

    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        if(villager.getProfession() == Config.hereticID) {
            /*recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 20 + random.nextInt(3), 18), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(ConfigItems.itemResource, 1, 9)));
            recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 4 + random.nextInt(3), 3), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(ConfigItems.itemResource, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 4 + random.nextInt(3), 6), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(ConfigItems.itemResource, 1, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemNuggetChicken, 24 + random.nextInt(8), 0), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.book, 4 + random.nextInt(3), 0), new ItemStack(ConfigItems.itemResource, 1, 9)));
            recipeList.add(new MerchantRecipe(new ItemStack(ConfigItems.itemNuggetBeef, 24 + random.nextInt(8), 0), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(ConfigItems.itemShard, 2 + random.nextInt(2), random.nextInt(6))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald), new ItemStack(ConfigItems.itemManaBean, 1 + random.nextInt(2), 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemBathSalts, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemRingRunic, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemAmuletVis, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3 + random.nextInt(6))));
            */
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1 + random.nextInt(2)), new ItemStack(ForbiddenItems.deadlyShards, 3 + random.nextInt(2), 6)));
            if(!Config.noLust)
                recipeList.add(new MerchantRecipe(new ItemStack(ForbiddenItems.deadlyShards, 2 + random.nextInt(4), 4), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(ForbiddenItems.deadlyShards, 4 + random.nextInt(6), 0), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(ForbiddenItems.gluttonyShard, 4 + random.nextInt(6), 0), new ItemStack(Items.emerald)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1 + random.nextInt(3)), new ItemStack(Items.skull, 1, 0)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 4 + random.nextInt(4)), new ItemStack(Items.ghast_tear, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(Items.glowstone_dust, 8 + random.nextInt(9))));

            ItemStack wand = new ItemStack(ConfigItems.itemWandCasting, 1, 36);
            ((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("iron"));
            ((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("profane"));
            ((ItemWandCasting) wand.getItem()).storeAllVis(wand, new AspectList().add(Aspect.FIRE, 5000).add(Aspect.WATER, 5000).add(Aspect.EARTH, 5000).add(Aspect.AIR, 5000).add(Aspect.ORDER, 5000).add(Aspect.ENTROPY, 5000));
            MerchantRecipe profane = new MerchantRecipe(new ItemStack(Items.emerald, 8 + random.nextInt(5)), new ItemStack(Items.stick), wand);
            profane.func_82783_a(-6);
            recipeList.add(profane);
            MerchantRecipe fire = new MerchantRecipe(new ItemStack(Items.emerald, 4 + random.nextInt(5)), new ItemStack(Items.diamond, 1), new ItemStack(ConfigItems.itemFocusFire, 1));
            fire.func_82783_a(-6);
            recipeList.add(fire);
        }

    }
}
