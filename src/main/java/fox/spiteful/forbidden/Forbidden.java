package fox.spiteful.forbidden;

import cpw.mods.fml.common.FMLCommonHandler;
import fox.spiteful.forbidden.potions.DarkPotions;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.wands.WandTriggerRegistry;

import fox.spiteful.forbidden.blocks.ForbiddenBlocks;
import fox.spiteful.forbidden.compat.Compat;
import fox.spiteful.forbidden.enchantments.DarkEnchantments;
import fox.spiteful.forbidden.items.ForbiddenItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "ForbiddenMagic", name = "Forbidden Magic", dependencies = "required-after:Thaumcraft@[4.2.2.0,);after:ThaumicTinkerer;after:AWWayofTime;after:Botania")
public class Forbidden {
    @Instance("ForbiddenMagic")
    public static Forbidden instance;
    public static CreativeTabs tab = new CreativeTabs("forbidden"){
        @Override
        public Item getTabIconItem() {
            return ForbiddenItems.fork;
        }
    };
    public static CreativeTabs crysTab;
    @SidedProxy(clientSide = "fox.spiteful.forbidden.client.ClientProxy", serverSide = "fox.spiteful.forbidden.CommonProxy")
    public static CommonProxy proxy;
    public static WandOverlord wandLord;
    public static FMEventHandler events;

    @EventHandler
    public void prelude(FMLPreInitializationEvent event) {
        instance = this;
        Config.configurate(event.getSuggestedConfigurationFile());
        if (Config.wrathCage)
            crysTab = new CreativeTabs("mobcrystal"){
                @Override
                public Item getTabIconItem() {
                    return ForbiddenItems.mobCrystal;
                }
            };
        Compat.initiate();
        DarkAspects.initAspects();
        Config.spawnilify();
        ForbiddenItems.addItems();
        ForbiddenBlocks.addBlocks();
        DarkEnchantments.hex();
        if(Compat.bm)
            DarkPotions.alchemize();
        proxy.registerRenderInfo();
    }

    @EventHandler
    public void crescendo(FMLInitializationEvent event) {
        events = new FMEventHandler();
        MinecraftForge.EVENT_BUS.register(events);
        FMLCommonHandler.instance().bus().register(events);
    }

    @EventHandler
    public void outro(FMLPostInitializationEvent event) {
        DarkAspects.addAspects();
        ForbiddenRecipes.addRecipes();
        ForbiddenResearch.addResearch();
        Compat.compatify();

        wandLord = new WandOverlord();
        WandTriggerRegistry.registerWandBlockTrigger(wandLord, 1, Blocks.obsidian, 0);
        WandTriggerRegistry.registerWandBlockTrigger(wandLord, 1, Blocks.netherrack, 0);
    }
}