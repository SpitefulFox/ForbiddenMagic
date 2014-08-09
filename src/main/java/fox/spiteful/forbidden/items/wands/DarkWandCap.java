package fox.spiteful.forbidden.items.wands;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;

/**
 * This class is used to keep the material information for the various caps. It
 * is also used to generate the wand recipes ingame.
 * 
 * @author Azanor
 * 
 */
public class DarkWandCap extends WandCap {
	public DarkWandCap(String tag, float discount, ItemStack item, int craftCost, ResourceLocation skin) {
		super(tag, discount, item, craftCost);
		setTexture(skin);
	}

	public DarkWandCap(String tag, float discount, List<Aspect> specialAspects, float discountSpecial, ItemStack item, int craftCost, ResourceLocation skin) {
		super(tag, discount, specialAspects, discountSpecial, item, craftCost);
		setTexture(skin);
	}
}
