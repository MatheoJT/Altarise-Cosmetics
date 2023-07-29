package net.altarise.cm.mount.type;

import net.altarise.cm.Cosmetics;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Field;

public class RideableWither extends EntityWither {

    private BukkitTask task;

    public RideableWither(World world) {
        super(world);
    }

    public static void spawn(Player player) {
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        RideableWither rideableWither = new RideableWither(nmsWorld);
        rideableWither.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        rideableWither.r(200);
        rideableWither.task = Bukkit.getScheduler().runTaskTimer(Cosmetics.INSTANCE(), () -> rideableWither.r(200), 20, 20);
        NBTTagCompound tag = rideableWither.getNBTTag() == null ? new NBTTagCompound() : rideableWither.getNBTTag();
        rideableWither.c(tag);
        rideableWither.f(tag);
        nmsWorld.addEntity(rideableWither, CreatureSpawnEvent.SpawnReason.CUSTOM);
        ((CraftPlayer) player).getHandle().mount(rideableWither);
    }

    public void g(float sideMot, float forMot) {
        if (this.passenger == null || !(this.passenger instanceof net.minecraft.server.v1_8_R3.EntityHuman)) {
            die();
            task.cancel();
            return;
        }

        this.lastYaw = this.yaw = this.passenger.yaw;
        this.pitch = this.passenger.pitch * 0.7F;
        setYawPitch(this.yaw, this.pitch);
        this.aI = this.aG = this.yaw;
        sideMot = ((EntityLiving) this.passenger).aZ * 0.3F;
        forMot = ((EntityLiving) this.passenger).ba;
        if (forMot <= 0.0F)
            forMot *= 0.25F;

        Field jump = null;
        try {
            jump = EntityLiving.class.getDeclaredField("aY");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        if (jump != null) {
            jump.setAccessible(true);
            if (this.locY < 210) {
                try {
                    if (jump.getBoolean(this.passenger)) {
                        this.motY = 0.5D;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        this.S = 1.0F;
        this.aK = bI() * 0.1F;
        if (!this.world.isClientSide) {
            k((float) getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue());
            super.g(sideMot, forMot);
        }

        this.ay = this.az;
        double d0 = this.locX - this.lastX;
        double d1 = this.locZ - this.lastZ;
        float f4 = (float) Math.sqrt(d0 * d0 + d1 * d1) * 4.0F;
        this.az += (Math.min(1.0F, f4) - this.az) * 0.4F;
        this.aA += this.az;
    }

}
