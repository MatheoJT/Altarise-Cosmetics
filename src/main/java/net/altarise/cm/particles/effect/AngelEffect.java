package net.altarise.cm.particles.effect;

import net.altarise.cm.CosmeticRarity;
import net.altarise.cm.particles.Effect;
import net.altarise.cm.particles.MathL;
import net.altarise.cm.particles.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public class AngelEffect extends Effect {
    int number = 16;
    double radius = 0.50;
    double playerOffset = 1.5;

    @Override
    public Material icon() {
        return Material.FEATHER;
    }

    @Override
    public void task() {



        double slice = 2 * Math.PI / number;

        for (int i = 0; i < number; i++) {
            float r = (float) 237 / 255;
            float g = (float) 205 / 255;
            float b = (float) 47 / 255;
            double angle = slice * i;
            double dx = radius * MathL.cos(angle);
            double dy = playerOffset + 0.5;
            double dz = radius * MathL.sin(angle);

            Location loc = new Location(target.getWorld(), target.getLocation().getX() + dx, target.getLocation().getY() + dy, target.getLocation().getZ() + dz);



            target.getWorld().spigot().playEffect(loc, org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);


        }


        for (double t = 0; t < Math.PI * 2; t += Math.PI / 48) {
            float r = (float) 255 / 255;
            float g = (float) 255 / 255;
            float b = (float) 255 / 255;
            Location location = target.getLocation();
            double offset = (Math.pow(Math.E, MathL.cos(t)) - 2 * MathL.cos(t * 4) - Math.pow(MathL.sin(t / 12), 5)) / 2;
            double x = MathL.sin(t) * offset;
            double y = MathL.cos(t) * offset;
            Vector v = VectorUtils.rotateAroundAxisY(new Vector(x, y, -0.3), -Math.toRadians(location.getYaw()));

            target.getWorld().spigot().playEffect(location.clone().add(v.getX(), v.getY() + 0.7, v.getZ()), org.bukkit.Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 64);


        }


    }

    @Override
    public CosmeticRarity rarity() {
        return CosmeticRarity.SUPREM;
    }

    @Override
    public String name() {
        return "Ange";
    }

    @Override
    public int cost() {
        return 0;
    }
}
