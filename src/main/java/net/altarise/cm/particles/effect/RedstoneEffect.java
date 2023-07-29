package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class RedstoneEffect extends Effect {
    @Override
    public Material icon() {
        return Material.REDSTONE;
    }
    private int step = 0;

    private final double radius = 1.5;
    private final double grow = 0.08;
    private final double radials = 16;
    private final int helices = 2;
    private final int maxStep = 32;
    private final int popParticleAmount = 10;

    private final double popOffset = 1.5;
    @Override
    public void task() {
        step = (step + 1) % maxStep;
        float r = (float) 255 / 255;
        float g = (float) 0 / 255;
        float b = (float) 0 / 255;

        Location location = target.getLocation().add(0, 1, 0);

        double rad = radius * (1 - (double) step / maxStep);
        for (int i = 0; i < helices; i++) {
            double angle = step * radials + (2 * Math.PI * i / helices);
            Vector v     = new Vector(MathL.cos(angle) * rad, step * grow - 1, MathL.sin(angle) * rad);

            target.getWorld().spigot().playEffect(location.clone().add(v), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);


        }

        if (step == maxStep - 1)
            for (int i = 0; i < popParticleAmount; i++)

                 target.getWorld().spigot().playEffect(location.clone().add(0, popOffset, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);



    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public String name() {
        return "Redstone";
    }

    @Override
    public int cost() {
        return 0;
    }
}
