package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

public class EndermanEffect extends Effect {
    @Override
    public Material icon() {
        return Material.EYE_OF_ENDER;
    }

    final int density = 15;
    final double radius = 1.5;

    @Override
    public void task() {


        Location location = target.getLocation().add(0, 1, 0);



        for (int i = 0; i < density; i++) {
            double u = Math.random();
            double v = Math.random();
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);
            double dx = radius * MathL.sin(phi) * MathL.cos(theta);
            double dy = radius * MathL.sin(phi) * MathL.sin(theta);
            double dz = radius * MathL.cos(phi);

            target.getWorld().spigot().playEffect(location.clone().add(dx, dy, dz), org.bukkit.Effect.PORTAL);

        }



    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.LEGENDARY;
    }

    @Override
    public String name() {
        return "Enderman";
    }

    @Override
    public int cost() {
        return 0;
    }
}
