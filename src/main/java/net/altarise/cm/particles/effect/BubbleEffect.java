package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class BubbleEffect extends Effect {
    @Override
    public Material icon() {
        return Material.WATER_BUCKET;
    }
    final double radius = 2.0;
    final double grow = 0.05;
    final double radials = 16;
    final int helices = 4;
    final int maxStep = 70;

    int step = 0;

    float r = (float) 80 / 255;
    float g = (float) 197 / 255;
    float b = (float) 255 / 255;
    @Override
    public void task() {



        Location location = target.getLocation().add(0, 1, 0);

        double rad = radius * (1 - (double) step / maxStep);
        for (int i = 0; i < helices; i++) {
            double angle = step * radials + (2 * Math.PI * i / helices);
            Vector v     = new Vector(MathL.cos(angle) * rad, step * grow - 1, MathL.sin(angle) * rad);


            target.getWorld().spigot().playEffect(location.clone().add(v), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);


            step = (step + 1) % maxStep;

         }

        }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public String name() {
        return "Bulles";
    }

    @Override
    public int cost() {
        return 0;
    }
}
