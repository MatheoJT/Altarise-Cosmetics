package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class BatmanEffect extends Effect {
    @Override
    public Material icon() {
        return Material.GOLD_BLOCK;
    }


    private int step = 0;

    private int spawnDelay;

    @Override
    public void task() {
        // Segment 1
        float r = (float) 237 / 255;
        float g = (float) 205 / 255;
        float b = (float) 47 / 255;
        Location location = target.getLocation();
        for (double x = -7; x <= -3; x += 0.05) {
            double y       = 3 * Math.sqrt(-Math.pow(x / 7, 2) + 1);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }
        for (double x = 3; x <= 7; x += 0.05) {
            double y = 3 * Math.sqrt(-Math.pow(x / 7, 2) + 1);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

        // Segment 2
        for (double x = -7; x <= -4; x += 0.05) {
            double y = -3 * Math.sqrt(-Math.pow(x / 7, 2) + 1);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }
        for (double x = 4; x <= 7; x += 0.05) {
            double y = -3 * Math.sqrt(-Math.pow(x / 7, 2) + 1);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

        // Segment 3
        for (double x = -4; x <= 4; x += 0.125) {
            double y = Math.abs(x / 2) - ((3 * Math.sqrt(33) - 7) / 112) * Math.pow(x, 2) + Math.sqrt(1 - Math.pow(Math.abs(Math.abs(x) - 2) - 1, 2)) - 3;
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

        // Segment 4
        for (double x = -1; x <= -0.75; x += 0.025) {
            double y = 9 - 8 * Math.abs(x);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }
        for (double x = 0.75; x <= 1; x += 0.025) {
            double y = 9 - 8 * Math.abs(x);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

        // Segment 5
        for (double x = -0.75; x <= -0.5; x += 0.05) {
            double y = 3 * Math.abs(x) + 0.75;
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }
        for (double x = 0.5; x <= 0.75; x += 0.05) {
            double y = 3 * Math.abs(x) + 0.75;
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

        // Segment 6
        for (double x = -0.5; x <= 0.5; x += 0.2) {
            double y = 2.25;
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

        // Segment 7
        for (double x = -3; x <= -1; x += 0.02) {
            double y = 1.5 - 0.5 * Math.abs(x) - ((6 * Math.sqrt(10)) / 14) * (Math.sqrt(3 - Math.pow(x, 2) + 2 * Math.abs(x)) - 2);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }
        for (double x = 1; x <= 3; x += 0.02) {
            double y = 1.5 - 0.5 * Math.abs(x) - ((6 * Math.sqrt(10)) / 14) * (Math.sqrt(3 - Math.pow(x, 2) + 2 * Math.abs(x)) - 2);
            Vector segment = new Vector(x, y, 0).multiply(0.3);
            VectorUtils.rotateAroundAxisY(segment, -Math.toRadians(location.getYaw()));
            target.getWorld().spigot().playEffect(location.clone().add(segment).add(0, 3, 0), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);

        }

    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.SUPREM;
    }

    @Override
    public String name() {
        return "Batman";
    }

    @Override
    public int cost() {
        return 0;
    }
}
