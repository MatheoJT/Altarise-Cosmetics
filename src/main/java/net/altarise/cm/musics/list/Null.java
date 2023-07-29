package net.altarise.cm.musics.list;


import net.altarise.api.utils.ItemBuilder;
import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.musics.Music;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Null implements Music {
    @Override
    public String getName() {
        return "Null";
    }

    @Override
    public String getFileName() {
        return "null";
    }

    @Override
    public String getAuthor() {
        return "Null";
    }

    @Override
    public CosmeticRarity getRarity() {
        return CosmeticRarity.SUPREM;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).headOwnerUrl("eb66b6693b2e94dded5742e42af70625069f93854f825b59ca87a08f5820f9b3").build();
    }
}
