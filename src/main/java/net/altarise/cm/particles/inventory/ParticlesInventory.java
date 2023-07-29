package net.altarise.cm.particles.inventory;

import net.altarise.api.utils.ItemBuilder;
import net.altarise.cm.mount.inventory.MountInventory;
import net.altarise.cm.musics.inventory.MusicInventory;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.ParticlesManager;
import net.altarise.cm.particles.effect.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ParticlesInventory extends FastInv {

    private final Player player;


    public ParticlesInventory(Player player) {
        super(9 * 6, "§fCollection de §5§lParticules"); this.player = player;

        //TEMP
        ItemStack border       = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(" ").build();
        ItemStack currenBorder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 2)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(" ").build();

        ItemStack locked = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§cIndisponible").lore("", "§7Cette catégorie n'est", "§7pas disponible durant cette Bêta").headOwnerUrl("46ba63344f49dd1c4f5488e926bf3d9e2b29916a6c50d610bb40a5273dc8c82").build();
        ItemStack particles = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §5§lParticules").lore("§8Particle", "", "§fActive ici tes particules !").headOwnerUrl("df4fcd9598a1fd39c28f50809e4ce8a1322ad48a9abd8c23f21188ce0a8288e4").build();
        ItemStack mount = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §6§lMontures").lore("§8Mount", "", "§fActive ici tes montures !").headOwnerUrl("397a3c20da5eea01c4132c3ff45ea80a364ad42f74dbf797cca4f8ab2d42adb3").build();
        ItemStack music = new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).headOwnerUrl("36f59da67b36a8df3364bb64aeb7f074baa460d596614f3a58be90cdca272297").flag(ItemFlag.HIDE_ATTRIBUTES).displayname("§f❯ §d§lMusiques").lore("§8Music", "", "§fActive ici tes musiques !").build();


        ItemStack cancel = new ItemBuilder(Material.BARRIER).displayname("§cDésactiver").lore("§8Particle", "", "§6Clique §epour désactiver tes particules").flag(ItemFlag.HIDE_ATTRIBUTES).build();


        setItems(4, 7, locked);
        setItem(1, particles, event -> new ParticlesInventory(player).open(player));
        setItem(2, mount, event -> new MountInventory(player).open(player));
        setItem(3, music, event -> new MusicInventory(player).open(player));
        setItems(9, 17, border);
        setItem(10, currenBorder);
        setItems(45, 53, border);
        setItem(49, cancel, event -> {
            ParticlesManager.stopEffect(player);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1f, 1f);
        });

        //----

        setParticle(22, new DestructionEffect());
        setParticle(20, new RedstoneEffect());

        setParticle(29, new AngryEffect());
        setParticle(19, new HappyEffect());
        setParticle(31, new NoteEffect());

        setParticle(30, new BubbleEffect());
        setParticle(23, new EndermanEffect());
        setParticle(39, new HeartEffect());

        setParticle(38, new CrossEffect());
        setParticle(32, new FireCircleEffect());
        setParticle(42, new RainbowEffect());
        setParticle(28, new SlimeEffect());

        setParticle(24, new AngelEffect());
        setParticle(33, new DemonicEffect());
        setParticle(21, new RainbowNoteEffect());
    }


    private void setParticle(int slot, Effect effect) {

        ItemStack icon = new ItemBuilder(effect.icon()).flag(ItemFlag.HIDE_ATTRIBUTES).displayname(effect.rarity().getColor() + effect.name()).lore("§8Particle", "", "§fRareté: " + effect.rarity().getColor() + effect.rarity().getName(), "", "§6Clique §epour utiliser").build();

        this.setItem(slot, icon, event -> {
            ParticlesManager.playEffect(player, effect);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1F, 1F);
        });

    }


}
