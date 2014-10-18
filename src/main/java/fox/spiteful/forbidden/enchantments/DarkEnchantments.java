package fox.spiteful.forbidden.enchantments;

import fox.spiteful.forbidden.compat.Compat;
import net.minecraft.enchantment.Enchantment;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;

import fox.spiteful.forbidden.Config;

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
				EnchantmentManager.registerExponentialCostData(wrath, "forbidden:textures/misc/pigbane.png", false, (new AspectList()).add(Aspect.ENTROPY, 6).add(Aspect.FIRE, 6), "WRATH");
				EnchantmentManager.registerExponentialCostData(cluster, "forbidden:textures/misc/lucrative.png", false, (new AspectList()).add(Aspect.FIRE, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5), "CLUSTER");
				EnchantmentManager.registerExponentialCostData(greedy, "forbidden:textures/misc/greedy.png", false, (new AspectList()).add(Aspect.ENTROPY, 50).add(Aspect.EARTH, 30).add(Aspect.ORDER, 20), "GREEDY");
				EnchantmentManager.registerExponentialCostData(educational, "forbidden:textures/misc/educational.png", false, new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15), "EDUCATIONAL");
				EnchantmentManager.registerExponentialCostData(consuming, "forbidden:textures/misc/consuming.png", false, (new AspectList()).add(Aspect.ENTROPY, 10), "CONSUMING");
				EnchantmentManager.registerExponentialCostData(corrupting, "forbidden:textures/misc/corrupting.png", false, (new AspectList()).add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 15).add(Aspect.EARTH, 10), "CORRUPTING");
                EnchantmentManager.registerExponentialCostData(voidtouched, "forbidden:textures/misc/voidtouched.png", false, (new AspectList()).add(Aspect.ENTROPY, 70), "VOIDTOUCHED");
			} catch (Throwable e) {
			}
		}
	}

}