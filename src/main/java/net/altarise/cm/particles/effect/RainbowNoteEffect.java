package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

public class RainbowNoteEffect extends Effect {
    @Override
    public Material icon() {
        return Material.JUKEBOX;
    }

    private int hue = 0;

    @Override
    public void task() {
        Location location = target.getLocation().add(0, 1, 0);



        float    r        = (float) hue / 255;
        float g = (float) 180 / 255;
        float b = (float) 99 / 255;


        hue += 2;
        hue %= 255;

        int orbs = 2;
        for (int i = 0; i < orbs; i++) {
            int    numSteps = 60;
            double slice    = Math.PI * 2 / numSteps;
            double orbSlice = Math.PI * 2 / orbs;

            int    stepX    = 0;
            double radius   = 1;
            double dx       = -MathL.cos(slice * stepX + orbSlice * i) * radius;
            int    stepY    = 0;
            int    maxStepY = 30;
            double dy       = (stepY / (double) maxStepY);
            double dz       = -MathL.sin(slice * stepX + orbSlice * i) * radius;


            target.getWorld().spigot().playEffect(location.clone().add(dx, dy, dz), org.bukkit.Effect.NOTE, 0, 1, r, g, b, 1, 0, 64);


        }
    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public String name() {
        return "Note §4R§ca§6i§en§ab§2o§bw";
    }

    @Override
    public int cost() {
        return 0;
    }
}
