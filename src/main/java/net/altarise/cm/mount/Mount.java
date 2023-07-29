package net.altarise.cm.mount;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.mount.type.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public enum Mount {
    PIG("Cochon", Material.CARROT_STICK, CosmeticRarity.RARE, 90, EntityType.PIG, EntityPig.class, RideablePig.class),
    PONEY("Poney", Material.SADDLE, CosmeticRarity.EPIC, 100, EntityType.HORSE, EntityHorse.class, RideablePoney.class),
    BABY_PIG("Bébé cochon", Material.CARROT_ITEM, CosmeticRarity.LEGENDARY, 90, EntityType.PIG, EntityPig.class, RideableBabyPig.class),
    BABY_RAINBOW_SHEEP("Bébé mouton", Material.WOOL, CosmeticRarity.LEGENDARY, 91, EntityType.SHEEP, EntitySheep.class, RideableRainbowBabySheep.class),
    SKELETON_HORSE("Cheval squelette", Material.LEASH, CosmeticRarity.SUPREM, 100, EntityType.HORSE, EntityHorse.class, RideableSkeletonHorse.class),
    BABY_WITHER("Wither", Material.SKULL_ITEM, CosmeticRarity.SUPREM, 64, EntityType.WITHER, EntityWither.class, RideableWither.class);

    private String name;
    private Material icon;
    private CosmeticRarity rarity;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;


    Mount(String name, Material icon, CosmeticRarity rarity, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        this.name = name;
        this.icon = icon;
        this.rarity = rarity;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public static void registerEntities() {
        BiomeBase[] var201;
        Mount[] var3;
        int var2 = (var3 = values()).length;
        for (int var19 = 0; var19 < var2; var19++) {
            Mount var4 = var3[var19];
            a(var4.getCustomClass(), var4.getName(), var4.getID());
        }
        try {
            var201 = (BiomeBase[]) getPrivateStatic(BiomeBase.class, "biomes");
        } catch (Exception var181) {
            return;
        }
        BiomeBase[] var211 = var201;
        int var21 = var201.length;
        for (var2 = 0; var2 < var21; var2++) {
            BiomeBase var20 = var211[var2];
            if (var20 == null)
                break;
            String[] var8;
            int var7 = (var8 = new String[]{"at", "au", "av", "aw"}).length;
            for (int var6 = 0; var6 < var7; var6++) {
                String field = var8[var6];
                try {
                    Field var18 = BiomeBase.class.getDeclaredField(field);
                    var18.setAccessible(true);
                    List mobList = (List) var18.get(var20);
                    for (BiomeBase.BiomeMeta meta : (Iterable<BiomeBase.BiomeMeta>) mobList) {
                        Mount[] var16;
                        int var15 = (var16 = values()).length;
                        for (int var14 = 0; var14 < var15; var14++) {
                            Mount entity = var16[var14];
                            if (entity.getNMSClass().equals(meta.b))
                                meta.b = entity.getCustomClass();
                        }
                    }
                } catch (Exception var191) {
                    var191.printStackTrace();
                }
            }
        }
    }

    public static void unregisterEntities() {
        BiomeBase[] var22;
        Mount[] var3;
        int var2 = (var3 = values()).length;
        int biomeBase;
        for (biomeBase = 0; biomeBase < var2; biomeBase++) {
            Mount biomes = var3[biomeBase];
            try {
                ((Map) getPrivateStatic(EntityTypes.class, "d")).remove(biomes.getCustomClass());
            } catch (Exception var231) {
                var231.printStackTrace();
            }
            try {
                ((Map) getPrivateStatic(EntityTypes.class, "f")).remove(biomes.getCustomClass());
            } catch (Exception var221) {
                var221.printStackTrace();
            }
        }
        var2 = (var3 = values()).length;
        for (biomeBase = 0; biomeBase < var2; biomeBase++) {
            Mount biomes = var3[biomeBase];
            try {
                a(biomes.getNMSClass(), biomes.getName(), biomes.getID());
            } catch (Exception var211) {
                var211.printStackTrace();
            }
        }
        try {
            var22 = (BiomeBase[]) getPrivateStatic(BiomeBase.class, "biomes");
        } catch (Exception var20) {
            return;
        }
        BiomeBase[] e = var22;
        int var24 = var22.length;
        for (var2 = 0; var2 < var24; var2++) {
            BiomeBase var23 = e[var2];
            if (var23 == null)
                break;
            String[] var8;
            int var7 = (var8 = new String[]{"at", "au", "av", "aw"}).length;
            for (int var6 = 0; var6 < var7; var6++) {
                String field = var8[var6];
                try {
                    Field var21 = BiomeBase.class.getDeclaredField(field);
                    var21.setAccessible(true);
                    List mobList = (List) var21.get(var23);
                    for (BiomeBase.BiomeMeta meta : (Iterable<BiomeBase.BiomeMeta>) mobList) {
                        Mount[] var16;
                        int var15 = (var16 = values()).length;
                        for (int var14 = 0; var14 < var15; var14++) {
                            Mount entity = var16[var14];
                            if (entity.getCustomClass().equals(meta.b))
                                meta.b = entity.getNMSClass();
                        }
                    }
                } catch (Exception var241) {
                    var241.printStackTrace();
                }
            }
        }
    }

    private static Object getPrivateStatic(Class<? extends Object> clazz, String f) throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    private static void a(Class<?> paramClass, String paramString, int paramInt) {
        try {
            ((Map<String, Class<?>>) getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
            ((Map<Class<?>, String>) getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
            ((Map<Integer, Class<?>>) getPrivateStatic(EntityTypes.class, "e")).put(Integer.valueOf(paramInt), paramClass);
            ((Map<Class<?>, Integer>) getPrivateStatic(EntityTypes.class, "f")).put(paramClass, Integer.valueOf(paramInt));
            ((Map<String, Integer>) getPrivateStatic(EntityTypes.class, "g")).put(paramString, Integer.valueOf(paramInt));
        } catch (Exception var4) {
        }
    }

    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }

    public CosmeticRarity getRarity() {
        return rarity;
    }

    public int getID() {
        return this.id;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return this.nmsClass;
    }

    public Class<? extends EntityInsentient> getCustomClass() {
        return this.customClass;
    }
}
