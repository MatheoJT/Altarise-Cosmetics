package net.altarise.cm.mount.type;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;

public class RideableRainbowBabySheep extends EntitySheep {
    public RideableRainbowBabySheep(World world) {
        super(world);
    }

    public static void spawn(Player player) {
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        RideableRainbowBabySheep rideableRainbowBabySheep = new RideableRainbowBabySheep(nmsWorld);
        rideableRainbowBabySheep.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        Sheep sheep = (Sheep) rideableRainbowBabySheep.getBukkitEntity();
        sheep.setBaby();
        sheep.setCustomName("jeb_");
        sheep.setCustomNameVisible(false);
        sheep.setAgeLock(true);
        nmsWorld.addEntity(rideableRainbowBabySheep, CreatureSpawnEvent.SpawnReason.CUSTOM);
        ((CraftPlayer) player).getHandle().mount(rideableRainbowBabySheep);
    }

    public void g(float sideMot, float forMot) {
        if (this.passenger == null || !(this.passenger instanceof net.minecraft.server.v1_8_R3.EntityHuman)) {
            die();
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
            if (this.onGround) {
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
