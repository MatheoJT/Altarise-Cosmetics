package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

public class FireCircleEffect extends Effect {
    @Override
    public Material icon() {
        return Material.BLAZE_POWDER;
    }
    int stepX = 0;

    int particles = 12;
    int particlesPerRotation = 90;
    double radius = 0.8;
    @Override
    public void task() {



        stepX++;

        Location location = target.getLocation().add(0, 1, 0);
        float    r        = (float) 255 / 255;
        float g = (float) 0 / 255;
        float b = (float) 0 / 255;

        for (double stepY = -60; stepY < 60; stepY += 120D / particles) {
            double dx = -(MathL.cos(((stepX + stepY) / (double) particlesPerRotation) * Math.PI * 2)) * radius;
            double dy = stepY / particlesPerRotation / 2D;
            double dz = -(MathL.sin(((stepX + stepY) / (double) particlesPerRotation) * Math.PI * 2)) * radius;

            target.getWorld().spigot().playEffect(location.clone().add(dx, dy, dz), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);


        }


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.LEGENDARY;
    }

    @Override
    public String name() {
        return "Cercle de feu";
    }

    @Override
    public int cost() {
        return 0;
    }
}
