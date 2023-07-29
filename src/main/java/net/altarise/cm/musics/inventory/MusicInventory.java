package net.altarise.cm.musics.inventory;


import net.altarise.api.utils.ItemBuilder;
import net.altarise.cm.mount.inventory.MountInventory;
import net.altarise.cm.musics.Music;
import net.altarise.cm.musics.SongManager;
import net.altarise.cm.musics.list.Coffin;
import net.altarise.cm.musics.list.Null;
import net.altarise.cm.musics.list.Tchoupi;
import net.altarise.cm.particles.inventory.FastInv;
import net.altarise.cm.particles.inventory.ParticlesInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class MusicInventory extends FastInv {

    private final Player player;


    public MusicInventory(Player player) {
        super(9 * 6, "§fCollection de §d§lMusiques");
        this.player = player;

        //TEMP
        ItemStack border = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(" ").build();
        ItemStack currenBorder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 6)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(" ").build();

        ItemStack locked = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§cIndisponible").lore("", "§7Cette catégorie n'est", "§7pas disponible durant cette Bêta").headOwnerUrl("46ba63344f49dd1c4f5488e926bf3d9e2b29916a6c50d610bb40a5273dc8c82").build();
        ItemStack mount = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §6§lMontures").lore("§8Mount", "", "§fActive ici tes montures !").headOwnerUrl("397a3c20da5eea01c4132c3ff45ea80a364ad42f74dbf797cca4f8ab2d42adb3").build();
        ItemStack particles = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §5§lParticules").lore("§8Particle", "", "§fActive ici tes particules !").headOwnerUrl("df4fcd9598a1fd39c28f50809e4ce8a1322ad48a9abd8c23f21188ce0a8288e4").build();
        ItemStack music = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).headOwnerUrl("36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297").flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §d§lMusiques").lore("§8Music", "", "§fActive ici tes musiques !").build();

        ItemStack stopButton = new ItemBuilder(Material.BARRIER).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§4§lArrêter la musique").build();
        ItemStack volUp = new ItemBuilder(Material.ARROW).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§a§lAugmenter le volume").lore("", "§2Volume: §f" + SongManager.getVolume(player) + "%", "", "§6Clique-Gauche: §e+1 Volume", "§6Clique-Droit: §e+10 Volume").build();
        ItemStack volDown = new ItemBuilder(Material.ARROW).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§c§lDiminuer le volume").lore("", "§2Volume: §f" + SongManager.getVolume(player) + "%", "", "§6Clique-Gauche: §e-1 Volume", "§6Clique-Droit: §e-10 Volume").build();
        ItemStack mode = new ItemBuilder(Material.HOPPER).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§6§lLecture §c(Indisponible)").lore("", "§f§l> §e§lUnique", "§e  Aléatoire", "§e  Boucle").build();


        setItems(4, 7, locked);
        setItem(1, particles, event -> new ParticlesInventory(player).open(player));
        setItem(2, mount, event -> new MountInventory(player).open(player));
        setItem(3, music, event -> new MusicInventory(player).open(player));
        setItems(9, 17, border);
        setItem(12, currenBorder);
        setItems(45, 53, border);
        setItem(49, stopButton, event -> {
            SongManager.stopSong(player);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1F, 1F);
        });

        setItem(48, volDown, event -> {
            if (event.isRightClick()) {
                SongManager.volumeDown(player, 10);
            } else {
                SongManager.volumeDown(player, 1);
            }

            new MusicInventory(player).open(player);
        });

        setItem(50, volUp, event -> {
            if (event.isRightClick()) {
                SongManager.volumeUp(player, 10);
            } else {
                SongManager.volumeUp(player, 1);
            }

            new MusicInventory(player).open(player);
        });

        setItem(51, mode);

        //----

        setMount(21, new Coffin());
        setMount(22, new Null());
        setMount(23, new Tchoupi());
    }


    private void setMount(int slot, Music music) {

        ItemStack icon = new ItemBuilder(music.getIcon()).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(music.getRarity().getColor() + music.getName()).lore("§8Music", "", "§6Autheur: §7" + music.getAuthor(), "", "§fRareté: " + music.getRarity().getColor() + music.getRarity().getName(), "", "§6Clique §epour utiliser").build();

        this.setItem(slot, icon, event -> {
            SongManager.playSong(player, music);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1F, 1F);
        });

    }
}