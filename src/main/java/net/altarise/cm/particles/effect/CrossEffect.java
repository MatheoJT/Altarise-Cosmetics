package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

import java.awt.*;

public class CrossEffect extends Effect {
    @Override
    public Material icon() {
        return Material.ARROW;
    }

    int hue = 0;
    int step = 0;
    final int maxStep = 32;

    @Override
    public void task() {




        Location location = target.getLocation().add(0, 1, 0);

        Color rgb = Color.getHSBColor(hue / 360F, 1.0F, 1.0F);


        hue += 2;
        hue %= 360;


        double ring1 = Math.PI / (maxStep / 2D) * step;
        double ring2 = Math.PI / (maxStep / 2D) * (((step + maxStep / 2D) % maxStep));




        target.getWorld().spigot().playEffect(location.clone().add(MathL.cos(ring1), MathL.sin(ring1), MathL.sin(ring1)), org.bukkit.Effect.COLOURED_DUST, 0, 1, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 1, 0, 64);
        target.getWorld().spigot().playEffect(location.clone().add(MathL.cos(ring1 + Math.PI), MathL.sin(ring1), MathL.sin(ring1 + Math.PI)), org.bukkit.Effect.COLOURED_DUST, 0, 1, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 1, 0, 64);
        target.getWorld().spigot().playEffect(location.clone().add(MathL.cos(ring2), MathL.sin(ring2), MathL.sin(ring2)), org.bukkit.Effect.COLOURED_DUST, 0, 1, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 1, 0, 64);
        target.getWorld().spigot().playEffect(location.clone().add(MathL.cos(ring2 + Math.PI), MathL.sin(ring2), MathL.sin(ring2 + Math.PI)), org.bukkit.Effect.COLOURED_DUST, 0, 1, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), 1, 0, 64);


        step = (step + 1) % maxStep;



    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.RARE;
    }

    @Override
    public String name() {
        return "Croix";
    }

    @Override
    public int cost() {
        return 0;
    }
}
