package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

public class DestructionEffect extends Effect {
    @Override
    public Material icon() {
        return Material.COBBLESTONE;
    }

    double step = 0;

    final int points = 50;
    final double radius = 1;
    final double offset = 0;
    final int numSteps = 15;
    @Override
    public void task() {



        Location location = target.getLocation();
        step = (step + 1) % numSteps;

        for (int i = 0; i < points; i++) {
            double dx = MathL.cos(Math.PI * 2 * ((double) i / points)) * radius;
            double dy = offset;
            double dz = MathL.sin(Math.PI * 2 * ((double) i / points)) * radius;





            target.getWorld().spigot().playEffect(location.clone().add(dx, dy, dz), org.bukkit.Effect.TILE_BREAK, 4, 1, 0, 0, 0, 1, 0, 64);


        }

    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public String name() {
        return "Destruction";
    }

    @Override
    public int cost() {
        return 0;
    }
}
