/**
 * This class based on a class created by <Pokefenn>.
 * It was distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 */

package fox.spiteful.forbidden.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

import java.util.Random;

public class SubTileTainthistle extends SubTileGenerating {

    private static final String TAG_BURN_TIME = "burnTime";
    private static final int range = 4;
    public static LexiconEntry lexicon;
    int burnTime = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        boolean didSomething = false;
        if(this.ticksExisted % 80 == 0) {
            if (mana < getMaxMana() && !supertile.getWorldObj().isRemote) {
                for (int ex = supertile.xCoord - range; ex <= supertile.xCoord + range; ex++) {
                    for (int wy = supertile.yCoord - range; wy <= supertile.yCoord + range; wy++) {
                        for (int zee = supertile.zCoord - range; zee <= supertile.zCoord + range; zee++) {
                            if (isFlux(ex, wy, zee)) {
                                int depth = supertile.getWorldObj().getBlockMetadata(ex, wy, zee);
                                supertile.getWorldObj().setBlockToAir(ex, wy, zee);
                                didSomething = true;
                                burnTime = Math.min(burnTime + depth * (4 + supertile.getWorldObj().rand.nextInt(4)), 4000);
                            }
                        }
                    }
                }

                if (didSomething) {
                    playSound();
                    sync();
                }
            }
        }
        if(burnTime > 0){
            if(supertile.getWorldObj().rand.nextInt(8) == 0)
                doBurnParticles();
            burnTime--;
        }
    }

    @Override
    public boolean isPassiveFlower() {
        return false;
    }

    public void doBurnParticles() {
        Thaumcraft.proxy.reservoirBubble(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, 0x4D00FF);
    }

    public boolean isFlux(int x, int y, int z){
        Block target = supertile.getWorldObj().getBlock(x, y, z);
        return target == ConfigBlocks.blockFluxGas || target == ConfigBlocks.blockFluxGoo;
    }

    public void playSound() {
        supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "random.drink", 0.05F, 0.5F + (float) Math.random() * 0.5F);
    }

    @Override
    public int getMaxMana() {
        return 150;
    }

    @Override
    public int getColor() {
        return 0x4D00FF;
    }

    @Override
    public LexiconEntry getEntry() {
        return lexicon;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_BURN_TIME, burnTime);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        burnTime = cmp.getInteger(TAG_BURN_TIME);
    }

    @Override
    public boolean canGeneratePassively() {
        return burnTime > 0;
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return 2;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return 2 + supertile.getWorldObj().rand.nextInt(5);
    }

    @Override
    public IIcon getIcon(){
        return BotaniaAPI.getSignatureForName("tainthistle").getIconForStack(null);
    }

}
