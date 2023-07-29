

package net.altarise.cm.particles;

import org.bukkit.Location;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public final class VectorUtils {

    
    private VectorUtils() {

    }

   
    public static void rotateAroundAxisX(Vector v, double angle) {
        double y, z, cos, sin;
        cos = MathL.cos(angle);
        sin = MathL.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        v.setY(y).setZ(z);
    }

   
    public static Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = MathL.cos(angle);
        sin = MathL.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

   
    public static void rotateAroundAxisZ(Vector v, double angle) {
        double x, y, cos, sin;
        cos = MathL.cos(angle);
        sin = MathL.sin(angle);
        x = v.getX() * cos - v.getY() * sin;
        y = v.getX() * sin + v.getY() * cos;
        v.setX(x).setY(y);
    }

 
    public static Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(v, angleX);
        rotateAroundAxisY(v, angleY);
        rotateAroundAxisZ(v, angleZ);
        return v;
    }

  
    public static Vector rotateVector(Vector v, Location location) {
        return rotateVector(v, location.getYaw(), location.getPitch());
    }

  
    public static Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = MathL.cos(yaw);
        double cosPitch = MathL.cos(pitch);

        double sinYaw = MathL.sin(yaw);
        double sinPitch = MathL.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;


        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector(x, y, z);
    }


    public static double angleToXAxis(Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }
}
