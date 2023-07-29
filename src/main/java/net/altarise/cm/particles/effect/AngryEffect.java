package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.concurrent.ThreadLocalRandom;

public class AngryEffect extends Effect {
    @Override
    public Material icon() {
        return Material.NETHER_STALK;
    }
    final double headOffset = 0.5;
    final double range = 0.5;
    @Override
    public void task() {


        double   x        = ThreadLocalRandom.current().nextDouble(-range, range);
        double   z        = ThreadLocalRandom.current().nextDouble(-range, range);
        Location location = target.getLocation().add(0, 1, 0);


        target.getWorld().spigot().playEffect(location.clone().add(x, headOffset, z), org.bukkit.Effect.VILLAGER_THUNDERCLOUD);


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public String name() {
        return "Enervé";
    }

    @Override
    public int cost() {
        return 0;
    }
}
