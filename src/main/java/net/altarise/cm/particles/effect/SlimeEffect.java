package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.concurrent.ThreadLocalRandom;

public class SlimeEffect extends Effect {
    @Override
    public Material icon() {
        return Material.SLIME_BALL;
    }

    @Override
    public void task() {
        Location location = target.getLocation();


        double range = 0.5;


        double x = ThreadLocalRandom.current().nextDouble(-range, range);

        double z = ThreadLocalRandom.current().nextDouble(-range, range);







        target.getWorld().spigot().playEffect(location.clone().add(x, 0, z), org.bukkit.Effect.SLIME);


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.COMMON;
    }

    @Override
    public String name() {
        return "Slime";
    }

    @Override
    public int cost() {
        return 0;
    }
}
