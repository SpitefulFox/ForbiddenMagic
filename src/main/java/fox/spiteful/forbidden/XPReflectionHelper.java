package fox.spiteful.forbidden;

import java.lang.reflect.Field;

import net.minecraft.entity.EntityLiving;

public class XPReflectionHelper {
    private static Field getField(Class clazz, String field) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, field);
            }
        }
    }

    public static int getXP(EntityLiving entityLiving) {
        try {
            Field f = getField(entityLiving.getClass(), "experienceValue");
            f.setAccessible(true);
            return (Integer) f.get(entityLiving);
        } catch (NoSuchFieldException e) {
            try {
                Field f = getField(entityLiving.getClass(), "field_70728_aV");
                f.setAccessible(true);
                return (Integer) f.get(entityLiving);
            } catch (Exception e2) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
