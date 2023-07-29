package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import org.bukkit.Location;
import org.bukkit.Material;

public class NoteEffect extends Effect {
    @Override
    public Material icon() {
        return Material.NOTE_BLOCK;
    }
    int step = 0;

    final int maxSteps = 30;
    @Override
    public void task() {


        step = (step + 1) % maxSteps;

        Location location = target.getLocation();
        float    r        = (float) 40 / 255;
        float g = (float) 180 / 255;
        float b = (float) 99 / 255;

        double slice = (Math.PI * 2 / maxSteps) * step;

        double newX = location.getX() + 0.5 * MathL.cos(slice);
        double newY = location.getY() + 2;
        double newZ = location.getZ() + 0.5 * MathL.sin(slice);

        target.getWorld().spigot().playEffect(new Location(location.getWorld(), newX, newY, newZ), org.bukkit.Effect.NOTE, 0, 1, r, g, b, 1, 0, 64);


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.EPIC;
    }

    @Override
    public String name() {
        return "Note";
    }

    @Override
    public int cost() {
        return 0;
    }
}
