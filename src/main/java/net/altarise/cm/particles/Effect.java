package net.altarise.cm.particles;

import net.altarise.cm.CosmeticRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Effect extends BukkitRunnable {

    public Player target;

    public void setTarget(Player player) {
        target = player;
    }


    public abstract Material icon();

    public abstract void task();

    public abstract CosmeticRarity rarity();

    public abstract String name();

    @Deprecated
    public abstract int cost();




    @Override
    public void run() {
        if(target != null) {
         if(target.isOnline()) {
             task();
            }
        }
    }
}
