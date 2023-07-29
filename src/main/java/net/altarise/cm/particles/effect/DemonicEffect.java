package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

public class DemonicEffect extends Effect {
    @Override
    public Material icon() {
        return Material.LAVA_BUCKET;
    }

    int stepX = 0;
    int stepY = 0;

    final int orbs = 4;
    final int maxStepX = 80;
    final int maxStepY = 60;

    final int points = 16;
    final double radius = 0.50;
    final double playerOffset = 1.5;
    @Override
    public void task() {


        stepX++;
        stepY--;


        double slice = 2 * Math.PI / points;

        for (int i = 0; i < points; i++) {
            float r = (float) 255 / 255;
            float g = (float) 0 / 255;
            float b = (float) 0 / 255;
            double angle = slice * i;
            double dx = radius * MathL.cos(angle);
            double dy = playerOffset + 0.5;
            double dz = radius * MathL.sin(angle);

            Location loc = new Location(target.getWorld(), target.getLocation().getX() + dx, target.getLocation().getY() + dy, target.getLocation().getZ() + dz);



            target.getWorld().spigot().playEffect(loc, org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);


        }

        Location location = target.getLocation().add(0, 1, 0);

        for (int i = 0; i < orbs; i++) {
            double dx = -(MathL.cos((stepX / (double) maxStepX) * (Math.PI * 2) + (((Math.PI * 2) / orbs) * i))) * ((maxStepY - Math.abs(stepY)) / (double) maxStepY);
            double dy = (stepY / (double) maxStepY) * 1.5;
            double dz = -(MathL.sin((stepX / (double) maxStepX) * (Math.PI * 2) + (((Math.PI * 2) / orbs) * i))) * ((maxStepY - Math.abs(stepY)) / (double) maxStepY);

            target.getWorld().spigot().playEffect(location.clone().add(dx, dy, dz), org.bukkit.Effect.LAVADRIP);

        }


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.SUPREM;
    }

    @Override
    public String name() {
        return "Démon";
    }

    @Override
    public int cost() {
        return 0;
    }
}
