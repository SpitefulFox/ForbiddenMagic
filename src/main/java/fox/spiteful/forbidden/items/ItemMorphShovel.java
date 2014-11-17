package fox.spiteful.forbidden.items;

import fox.spiteful.forbidden.Forbidden;
import fox.spiteful.forbidden.enchantments.DarkEnchantments;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.api.IRepairable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IWarpingGear;

public class ItemMorphShovel extends ItemSpade implements IRepairable, IWarpingGear {
	public IIcon[] icon;

	public ItemMorphShovel(ToolMaterial enumtoolmaterial) {
		super(enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
		this.setHarvestLevel("shovel", 4);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		icon = new IIcon[2];
		this.icon[0] = ir.registerIcon("forbidden:chameleonshovel");
		this.icon[1] = ir.registerIcon("forbidden:eyeshovel");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass) {
		return renderPass != 1 ? icon[0] : icon[1];
	}

	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.epic;
	}

	public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
		return stack2.isItemEqual(new ItemStack(ForbiddenItems.deadlyShards, 1, 1)) ? true : super.getIsRepairable(stack, stack2);
	}

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (player.isSneaking() && itemstack.hasTagCompound() && getMaxDamage() - itemstack.getItemDamage() > 5) {
            NBTTagCompound tags = itemstack.getTagCompound();
            byte phase = tags.getByte("phase");
            if(tags.hasKey("ench")){
                NBTTagList enchants = itemstack.getEnchantmentTagList();
                tags.setTag("enchants" + phase, enchants);
            }
            else
                tags.removeTag("enchants" + phase);

            if (tags.hasKey("display")) {
                String name = tags.getCompoundTag("display").getString("Name");
                if (name != null && !name.equals(""))
                    tags.getCompoundTag("display").setString("Name" + phase, name);
                else
                    tags.getCompoundTag("display").removeTag("Name" + phase);
            }
            if (++phase > 2)
                phase = 0;
            tags.setByte("phase", phase);
            if(tags.hasKey("enchants" + phase)) {
                NBTTagList enchants = (NBTTagList) (tags.getTag("enchants" + phase));
                tags.setTag("ench", enchants);
            }
            else
                tags.removeTag("ench");

            if (tags.hasKey("display")) {
                String name = tags.getCompoundTag("display").getString("Name" + phase);
                if (name != null && !name.equals(""))
                    tags.getCompoundTag("display").setString("Name", name);
                else
                    tags.getCompoundTag("display").removeTag("Name");
            }

            itemstack.setTagCompound(tags);
            itemstack.damageItem(5, player);
            player.swingItem();
            world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
        }
        return itemstack;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemstack, int renderpass) {
		if (renderpass != 1)
			return 16777215;
		else {
			if (!itemstack.hasTagCompound())
				return 0x980000;
			byte phase = itemstack.getTagCompound().getByte("phase");
			if (phase == 1)
				return 0x0010CC;
			else if (phase == 2)
				return 0xE5DA00;
			else
				return 0x980000;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

    public void onUpdate(ItemStack stack, World world, Entity entity, int fuckObfuscation, boolean fuckObfuscation2) {
        super.onUpdate(stack, world, entity, fuckObfuscation, fuckObfuscation2);
        if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.voidtouched.effectId, stack) > 0 && stack.isItemDamaged() && entity != null && entity.ticksExisted % 10 == 0 && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase)entity);
        }

    }

    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.voidtouched.effectId, itemstack) > 0)
            return 1;
        else
            return 0;
    }

    //Public Domain AOE code courtesy of Tinkers Construct's authors

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        // only effective materials matter. We don't want to aoe when beraking dirt with a hammer.
        if(EnchantmentHelper.getEnchantmentLevel(DarkEnchantments.impact.effectId, stack) <= 0)
            return super.onBlockStartBreak(stack, x, y, z, player);
        Block block = player.worldObj.getBlock(x,y,z);
        int meta = player.worldObj.getBlockMetadata(x,y,z);
        if(block == null || !ForgeHooks.isToolEffective(stack, block, meta))
            return super.onBlockStartBreak(stack, x,y,z, player);
        MovingObjectPosition mop = ItemMorphPickaxe.raytraceFromEntity(player.worldObj, player, false, 4.5d);
        if(mop == null)
            return super.onBlockStartBreak(stack, x,y,z, player);
        int sideHit = mop.sideHit;
        //int sideHit = Minecraft.getMinecraft().objectMouseOver.sideHit;
        // we successfully destroyed a block. time to do AOE!
        int xRange = 1;
        int yRange = 1;
        int zRange = 0;
        switch (sideHit) {
            case 0:
            case 1:
                yRange = 0;
                zRange = 1;
                break;
            case 2:
            case 3:
                xRange = 1;
                zRange = 0;
                break;
            case 4:
            case 5:
                xRange = 0;
                zRange = 1;
                break;
        }
        for (int xPos = x - xRange; xPos <= x + xRange; xPos++)
            for (int yPos = y - yRange; yPos <= y + yRange; yPos++)
                for (int zPos = z - zRange; zPos <= z + zRange; zPos++) {
                    // don't break the originally already broken block, duh
                    if (xPos == x && yPos == y && zPos == z)
                        continue;
                    breakExtraBlock(player.worldObj, stack, xPos, yPos, zPos, sideHit, player, x,y,z);
                }
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    protected void breakExtraBlock(World world, ItemStack stack, int x, int y, int z, int sidehit, EntityPlayer player, int refX, int refY, int refZ) {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if (world.isAirBlock(x, y, z))
            return;
        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        // only effective materials
        if (!ForgeHooks.isToolEffective(stack, block, meta))
            return;
        Block refBlock = world.getBlock(refX, refY, refZ);
        float refStrength = ForgeHooks.blockStrength(refBlock, player, world, refX, refY, refZ);
        float strength = ForgeHooks.blockStrength(block, player, world, x,y,z);
        // only harvestable blocks that aren't impossibly slow to harvest
        if (!ForgeHooks.canHarvestBlock(block, player, meta) || refStrength/strength > 10f)
            return;
        if (player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, false))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            // send update to client
            if (!world.isRemote) {
                ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
            }
            return;
        }
        // server sided handling
        if (!world.isRemote) {
            // serverside we reproduce ItemInWorldManager.tryHarvestBlock
            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, x,y,z, meta, player);
            if(block.removedByPlayer(world, player, x,y,z, true)) // boolean is if block can be harvested, checked above
            {
                block.onBlockDestroyedByPlayer( world, x,y,z, meta);
                block.harvestBlock(world, player, x,y,z, meta);
            }
            // always send block update to client
            EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
            mpPlayer.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
        }
        // client sided handling
        else {
            PlayerControllerMP pcmp = Minecraft.getMinecraft().playerController;
            // clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.
            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            if(block.removedByPlayer(world, player, x,y,z))
            {
                block.onBlockDestroyedByPlayer(world, x,y,z, meta);
            }
            pcmp.onPlayerDestroyBlock(x, y, z, sidehit);
            // send an update to the server, so we get an update back
            //if(PHConstruct.extraBlockUpdates)
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x,y,z, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }

}
