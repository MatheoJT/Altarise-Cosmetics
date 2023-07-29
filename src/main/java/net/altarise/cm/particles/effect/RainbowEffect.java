package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class RainbowEffect extends Effect {
    @Override
    public Material icon() {
        return Material.RECORD_3;
    }
    double step = 0;

    int points = 3;
    int numSteps = 40;
    int hue = 0;

    @Override
    public void task() {




        Location location = target.getLocation();

        hue++;
        if(hue == 360) hue = 0;


        Color rgb = Color.getHSBColor(hue / 360F, 1.0F, 1.0F);
        hue += 2;
        hue %= 360;



        for (int i = 0; i < points; i++) {




            double range = 1;


            double x = ThreadLocalRandom.current().nextDouble(-range, range);

            double z = ThreadLocalRandom.current().nextDouble(-range, range);
            double y = ThreadLocalRandom.current().nextDouble(-range, range);


            target.getWorld().spigot().playEffect(location.clone().add(x, y, z), org.bukkit.Effect.COLOURED_DUST, 0, 1, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 1, 0, 64);



        }

        step = (step + Math.PI * 2 / numSteps) % numSteps;


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public String name() {
        return "§4R§ca§6i§en§ab§2o§bw";
    }

    @Override
    public int cost() {
        return 0;
    }
}
