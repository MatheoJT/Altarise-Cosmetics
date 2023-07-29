package net.altarise.cm.musics;

import net.altarise.cm.CosmeticRarity;
import org.bukkit.inventory.ItemStack;


public interface Music {

    String getName();

    String getFileName();

    String getAuthor();

    CosmeticRarity getRarity();

    ItemStack getIcon();
}
