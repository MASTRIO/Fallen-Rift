package net.mcreator.fallenrift.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.fallenrift.FallenRiftModVariables;
import net.mcreator.fallenrift.FallenRiftModElements;
import net.mcreator.fallenrift.FallenRiftMod;

import java.util.Map;

@FallenRiftModElements.ModElement.Tag
public class FinishRunningDungeonProcedure extends FallenRiftModElements.ModElement {
	public FinishRunningDungeonProcedure(FallenRiftModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				FallenRiftMod.LOGGER.warn("Failed to load dependency world for procedure FinishRunningDungeon!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		FallenRiftMod.LOGGER.info("Fallen Rift dungeon completed");
		FallenRiftModVariables.WorldVariables.get(world).dungeonsRun = (double) ((FallenRiftModVariables.WorldVariables.get(world).dungeonsRun) + 1);
		FallenRiftModVariables.WorldVariables.get(world).syncData(world);
	}
}
