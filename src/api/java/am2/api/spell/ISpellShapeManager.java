package am2.api.spell;

import am2.api.spell.component.interfaces.ISpellShape;

public interface ISpellShapeManager {
	public int registerSpellShape(ISpellShape shape, String shapeName);
	public ISpellShape getSpellShape(int shapeID);
}
