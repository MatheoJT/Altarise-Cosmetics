package net.altarise.cm.musics.list;


import net.altarise.api.utils.ItemBuilder;
import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.musics.Music;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Coffin implements Music {
    @Override
    public String getName() {
        return "Astronomia";
    }

    @Override
    public String getFileName() {
        return "coffin";
    }

    @Override
    public String getAuthor() {
        return "Vicetone";
    }

    @Override
    public CosmeticRarity getRarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).headOwnerUrl("b7cab56c82cb81bdb9979a464bc9d3ba3e6722ba122cf6c52873010a2b59aefe").build();
    }
}
