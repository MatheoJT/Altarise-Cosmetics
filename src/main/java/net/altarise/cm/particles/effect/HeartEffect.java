package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.concurrent.ThreadLocalRandom;

public class HeartEffect extends Effect {
    @Override
    public Material icon() {
        return Material.APPLE;
    }
    double range = 1;


    double x = ThreadLocalRandom.current().nextDouble(-range, range);
    double y = ThreadLocalRandom.current().nextDouble(0, range);
    double z = ThreadLocalRandom.current().nextDouble(-range, range);
    @Override
    public void task() {



        Location loc = target.getLocation();
        target.getWorld().spigot().playEffect(loc.add(x, y, z), org.bukkit.Effect.HEART);

    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public String name() {
        return "Coeur";
    }

    @Override
    public int cost() {
        return 0;
    }
}
