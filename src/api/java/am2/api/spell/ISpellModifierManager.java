package am2.api.spell;

import am2.api.spell.component.interfaces.ISpellModifier;

public interface ISpellModifierManager {
	public int registerSpellModifier(ISpellModifier modifier, String modifierName);
	public ISpellModifier getSpellModifier(int modifierID);
}
