package net.mcreator.fallenrift.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.fallenrift.FallenRiftModElements;
import net.mcreator.fallenrift.FallenRiftMod;

import java.util.Map;

@FallenRiftModElements.ModElement.Tag
public class GearKnifeRightClickedInAirProcedure extends FallenRiftModElements.ModElement {
	public GearKnifeRightClickedInAirProcedure(FallenRiftModElements instance) {
		super(instance, 8);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FallenRiftMod.LOGGER.warn("Failed to load dependency entity for procedure GearKnifeRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				FallenRiftMod.LOGGER.warn("Failed to load dependency world for procedure GearKnifeRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (world.getWorldInfo().isThundering()) {
			entity.setMotionMultiplier(null, new Vector3d(0.25D, (double) 0.05F, 0.25D));
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).attackEntityFrom(new DamageSource("electrocuted").setDamageBypassesArmor(), (float) 15);
			}
		}
	}
}
