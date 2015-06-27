package fox.spiteful.forbidden.enchantments;

import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.enchantment.Enchantment;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import fox.spiteful.forbidden.Config;

import java.lang.reflect.Method;

public class DarkEnchantments {
    public static Enchantment cluster;
    public static Enchantment wrath;
    public static Enchantment greedy;
    public static Enchantment consuming;
    public static Enchantment educational;
    public static Enchantment corrupting;
    public static Enchantment voidtouched;
    public static Enchantment impact;

    public static void hex() {
        if(!Config.enchanting)
            return;

        cluster = new EnchantmentCluster(Config.clusterEnchID);
        wrath = new EnchantmentWrath(Config.wrathEnchID);
        greedy = new EnchantmentGreedy(Config.greedyEnchID);
        consuming = new EnchantmentConsuming(Config.consumingEnchID);
        educational = new EnchantmentEducational(Config.educationalEnchID);
        corrupting = new EnchantmentCorrupting(Config.corruptingEnchID);
        voidtouched = new EnchantmentVoid(Config.voidEnchID);
        impact = new EnchantmentImpact(Config.impactEnchID);

        if (Compat.tt) {
            try {
                Method reg = Class.forName("thaumic.tinkerer.common.enchantment.core.EnchantmentManager").getDeclaredMethod("registerExponentialCostData", Enchantment.class, String.class, Boolean.TYPE, AspectList.class, String.class);
                reg.invoke(null, wrath, "forbidden:textures/misc/wrath.png", false, (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6), "WRATH");
                reg.invoke(null, cluster, "forbidden:textures/misc/lucrative.png", false, (new AspectList()).add(Aspect.FIRE, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5), "CLUSTER");
                reg.invoke(null, greedy, "forbidden:textures/misc/greedy.png", false, (new AspectList()).add(Aspect.ENTROPY, 50).add(Aspect.EARTH, 30).add(Aspect.ORDER, 20), "GREEDY");
                reg.invoke(null, educational, "forbidden:textures/misc/educational.png", false, new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15), "EDUCATIONAL");
                reg.invoke(null, consuming, "forbidden:textures/misc/consuming.png", false, (new AspectList()).add(Aspect.ENTROPY, 10), "CONSUMING");
                reg.invoke(null, corrupting, "forbidden:textures/misc/corrupting.png", false, (new AspectList()).add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 15).add(Aspect.EARTH, 10), "CORRUPTING");
                reg.invoke(null, voidtouched, "forbidden:textures/misc/voidtouched.png", false, (new AspectList()).add(Aspect.ENTROPY, 70), "VOIDTOUCHED");
                reg.invoke(null, impact, "forbidden:textures/misc/impact.png", false, (new AspectList()).add(Aspect.ENTROPY, 30).add(Aspect.EARTH, 30), "IMPACT");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

}