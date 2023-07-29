package net.altarise.cm.musics.list;


import net.altarise.api.utils.ItemBuilder;
import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.musics.Music;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Tchoupi implements Music {
    @Override
    public String getName() {
        return "T'choupi";
    }

    @Override
    public String getFileName() {
        return "tchoupi";
    }

    @Override
    public String getAuthor() {
        return "Guillaume Poyet";
    }

    @Override
    public CosmeticRarity getRarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).headOwnerUrl("25d66cbf7453fac4b41859f6271083386c97d11e310aac9d631b9db10bfd45d").build();
    }
}
