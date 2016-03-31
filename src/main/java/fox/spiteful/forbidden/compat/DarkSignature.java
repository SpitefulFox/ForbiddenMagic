package fox.spiteful.forbidden.compat;

        import net.minecraft.client.renderer.texture.IIconRegister;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.item.ItemStack;
        import net.minecraft.util.EnumChatFormatting;
        import net.minecraft.util.IIcon;
        import net.minecraft.util.StatCollector;
        import vazkii.botania.api.BotaniaAPI;
        import vazkii.botania.api.subtile.SubTileEntity;
        import vazkii.botania.api.subtile.SubTileFunctional;
        import vazkii.botania.api.subtile.SubTileGenerating;
        import vazkii.botania.api.subtile.signature.BasicSignature;
        import vazkii.botania.api.subtile.signature.SubTileSignature;

        import java.util.List;

public class DarkSignature extends BasicSignature {
    IIcon icon;

    public DarkSignature(String name){
        super(name);
    }

    @Override
    public void registerIcons(IIconRegister reg){
        icon = reg.registerIcon("forbidden:" + getName());
    }

    @Override
    public IIcon getIconForStack(ItemStack item){
        return icon;
    }

    @Override
    public String getUnlocalizedNameForStack(ItemStack item){
        return "darkflower." + getName();
    }

    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack item){
        return "tile.darkflower." + getName() + ".lore";
    }

}