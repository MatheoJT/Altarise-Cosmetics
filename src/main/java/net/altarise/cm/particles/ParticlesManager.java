package net.altarise.cm.particles;

import net.altarise.cm.Cosmetics;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class ParticlesManager {

    private static final Map<Player, BukkitTask> effects = new HashMap<>();


    public static void playEffect(Player player, Effect effect) {
        stopEffect(player);
        BukkitTask task;
        effect.setTarget(player);
        task = effect.runTaskTimerAsynchronously(Cosmetics.INSTANCE(), 0, 2);

        effects.put(player, task);
    }

    public static boolean hasEffect(Player player) {
        return effects.containsKey(player);
    }


    public static void stopEffect(Player player) {
        if (hasEffect(player)) {
            effects.get(player).cancel();
            effects.remove(player);
        }
    }
}
