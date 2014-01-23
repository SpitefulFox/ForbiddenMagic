package com.spiteful.forbidden.client;

import com.spiteful.forbidden.tiles.TileEntityWrathCage;
import com.spiteful.forbidden.tiles.WrathSpawnerLogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityWrathCageRenderer extends TileEntitySpecialRenderer
{
    public void renderTileEntityWrathCage(TileEntityWrathCage wrathCage, double par2, double par4, double par6, float par8)
    {
        if(!wrathCage.getSpawnerLogic().isMobSet())
			return;
		GL11.glPushMatrix();
        GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
        renderMob(wrathCage.getSpawnerLogic(), par2, par4, par6, par8);
        GL11.glPopMatrix();
    }

    public static void renderMob(WrathSpawnerLogic spawnLogic, double par1, double par3, double par5, float par7)
    {
        Entity entity = spawnLogic.getEntityForRender();

        if (entity != null)
        {
            entity.setWorld(spawnLogic.getSpawnerWorld());
			float f1 = 0.4375F;
			if(spawnLogic.getEntityNameToSpawn().equals("Ghast"))
				f1 = 0.1F;
			else if(spawnLogic.getEntityNameToSpawn().equals("Slime") || spawnLogic.getEntityNameToSpawn().equals("ThaumSlime"))
				f1 = 0.4F;
			else if(spawnLogic.getEntityNameToSpawn().equals("Enderman"))
				f1 = 0.3F;
            GL11.glTranslatef(0.0F, 0.4F, 0.0F);
            if(!spawnLogic.getSpawnerWorld().isBlockIndirectlyGettingPowered(spawnLogic.getSpawnerX(), spawnLogic.getSpawnerY(), spawnLogic.getSpawnerZ()))
				GL11.glRotatef((float)(spawnLogic.field_98284_d + (spawnLogic.field_98287_c - spawnLogic.field_98284_d) * (double)par7) * 10.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.4F, 0.0F);
            GL11.glScalef(f1, f1, f1);
            entity.setLocationAndAngles(par1, par3, par5, 0.0F, 0.0F);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, par7);
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityWrathCage((TileEntityWrathCage)par1TileEntity, par2, par4, par6, par8);
    }
}
