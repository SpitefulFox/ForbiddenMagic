package fox.spiteful.forbidden.compat;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class ForbiddenLexicon extends LexiconEntry implements IAddonEntry {


    public ForbiddenLexicon(String name, LexiconCategory category){
        super(name, category);
        BotaniaAPI.addEntry(this, category);
    }

    @Override
    public String getSubtitle(){
        return "[Forbidden Magic x Botania]";
    }

    @Override
    public String getUnlocalizedName() {
        return "forbidden.lexicon." + super.getUnlocalizedName();
    }
}
