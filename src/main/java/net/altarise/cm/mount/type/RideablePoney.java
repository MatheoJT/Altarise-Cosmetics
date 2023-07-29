package net.altarise.cm.mount.type;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class RideablePoney extends EntityHorse {

    public RideablePoney(World world) {
        super(world);
    }

    public static void spawn(Player player) {
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        RideablePoney rideablePoney = new RideablePoney(nmsWorld);
        rideablePoney.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        nmsWorld.addEntity(rideablePoney, CreatureSpawnEvent.SpawnReason.CUSTOM);
        Horse horse = (Horse) rideablePoney.getBukkitEntity();
        horse.setVariant(Horse.Variant.HORSE);
        horse.getInventory().setSaddle(new org.bukkit.inventory.ItemStack(org.bukkit.Material.SADDLE));
        horse.setBaby();
        horse.setAgeLock(true);
        horse.setTamed(true);
        horse.setOwner(player);
        ((CraftPlayer) player).getHandle().mount(rideablePoney);
    }

    public void g(float sideMot, float forMot) {
        if (this.passenger == null || !(this.passenger instanceof EntityHuman)) {
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
