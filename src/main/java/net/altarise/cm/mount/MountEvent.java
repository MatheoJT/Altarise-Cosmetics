package net.altarise.cm.mount;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class MountEvent implements Listener {


    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof WitherSkull) event.setCancelled(true);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent event) {
        if (event.getEntity().getType().equals(EntityType.WITHER)) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;

        if (event.getPlayer().getVehicle() != null) event.setCancelled(true);
    }


}
