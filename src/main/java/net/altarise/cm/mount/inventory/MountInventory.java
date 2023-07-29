package net.altarise.cm.mount.inventory;


import net.altarise.api.utils.ItemBuilder;
import net.altarise.cm.mount.Mount;
import net.altarise.cm.mount.type.*;
import net.altarise.cm.musics.inventory.MusicInventory;
import net.altarise.cm.particles.inventory.FastInv;
import net.altarise.cm.particles.inventory.ParticlesInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MountInventory extends FastInv {

    private final Player player;


    public MountInventory(Player player) {
        super(9 * 6, "§fCollection de §6§lMontures");
        this.player = player;

        //TEMP
        ItemStack border = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(" ").build();
        ItemStack currenBorder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(" ").build();

        ItemStack locked = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§cIndisponible").lore("", "§7Cette catégorie n'est", "§7pas disponible durant cette Bêta").headOwnerUrl("46ba63344f49dd1c4f5488e926bf3d9e2b29916a6c50d610bb40a5273dc8c82").build();
        ItemStack mount = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §6§lMontures").lore("§8Mount", "", "§fActive ici tes montures !").headOwnerUrl("397a3c20da5eea01c4132c3ff45ea80a364ad42f74dbf797cca4f8ab2d42adb3").build();
        ItemStack particles = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §5§lParticules").lore("§8Particle", "", "§fActive ici tes particules !").headOwnerUrl("df4fcd9598a1fd39c28f50809e4ce8a1322ad48a9abd8c23f21188ce0a8288e4").build();
        ItemStack music = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).headOwnerUrl("36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297").flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §d§lMusiques").lore("§8Music", "", "§fActive ici tes musiques !").build();

        setItems(4, 7, locked);
        setItem(1, particles, event -> new ParticlesInventory(player).open(player));
        setItem(2, mount, event -> new MountInventory(player).open(player));
        setItem(3, music, event -> new MusicInventory(player).open(player));
        setItems(9, 17, border);
        setItem(11, currenBorder);
        setItems(45, 53, border);

        //----


        setMount(20, Mount.PIG, event -> RideablePig.spawn(player));
        setMount(21, Mount.PONEY, event -> RideablePoney.spawn(player));
        setMount(22, Mount.BABY_PIG, event -> RideableBabyPig.spawn(player));
        setMount(23, Mount.BABY_RAINBOW_SHEEP, event -> RideableRainbowBabySheep.spawn(player));
        setMount(24, Mount.BABY_WITHER, event -> RideableWither.spawn(player));
        setMount(25, Mount.SKELETON_HORSE, event -> RideableSkeletonHorse.spawn(player));
    }


    private void setMount(int slot, Mount mount, Consumer<InventoryClickEvent> consumer) {

        ItemStack icon = new ItemBuilder(mount.getIcon()).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(mount.getRarity().getColor() + mount.getName()).lore("§8Mount", "", "§fRareté: " + mount.getRarity().getColor() + mount.getRarity().getName(), "", "§6Clique §epour utiliser").build();

        this.setItem(slot, icon, event -> {
            consumer.accept(event);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1F, 1F);
        });

    }


}