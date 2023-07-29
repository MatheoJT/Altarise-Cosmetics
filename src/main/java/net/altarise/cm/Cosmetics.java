package net.altarise.cm;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import net.altarise.cm.mount.Mount;
import net.altarise.cm.mount.MountEvent;
import net.altarise.cm.particles.ParticlesManager;
import net.altarise.cm.particles.inventory.FastInvManager;
import net.altarise.cm.particles.inventory.ParticlesInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Cosmetics extends JavaPlugin implements Listener {


    private static Cosmetics cosmetics;
    static {
        try {
            java.nio.file.FileSystems.getDefault();
        } catch (java.nio.file.FileSystemNotFoundException e) {
            e.printStackTrace();
        }
    }

    private NoteBlockAPI noteBlockAPI;

    @Override
    public void onEnable() {
        super.onEnable();
        this.noteBlockAPI = new NoteBlockAPI(this);
        noteBlockAPI.onEnable();
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        saveResource("music/coffin.nbs", false);
        saveResource("music/tchoupi.nbs", false);
        cosmetics = this;
        FastInvManager.register(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new MountEvent(), this);
        Mount.registerEntities();
    }


    @Override
    public void onDisable() {
        super.onDisable();
        noteBlockAPI.onDisable();
        Mount.unregisterEntities();
    }

    public static Cosmetics INSTANCE() {
        return cosmetics;
    }


    @EventHandler
    public void onInterract(PlayerInteractEvent event) {
        if (event.getPlayer() == null) return;
        if(event.getPlayer().getItemInHand() == null) return;
        if(event.getPlayer().getItemInHand().getType().equals(Material.ENDER_CHEST)) {
            new ParticlesInventory(event.getPlayer()).open(event.getPlayer());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        ParticlesManager.stopEffect(event.getPlayer());
    }
}
