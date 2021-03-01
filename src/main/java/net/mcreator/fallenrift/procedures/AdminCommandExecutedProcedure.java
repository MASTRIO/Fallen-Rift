package net.mcreator.fallenrift.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.fallenrift.FallenRiftModElements;
import net.mcreator.fallenrift.FallenRiftMod;

import java.util.Map;
import java.util.HashMap;

@FallenRiftModElements.ModElement.Tag
public class AdminCommandExecutedProcedure extends FallenRiftModElements.ModElement {
	public AdminCommandExecutedProcedure(FallenRiftModElements instance) {
		super(instance, 21);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				FallenRiftMod.LOGGER.warn("Failed to load dependency entity for procedure AdminCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				FallenRiftMod.LOGGER.warn("Failed to load dependency cmdparams for procedure AdminCommandExecuted!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				FallenRiftMod.LOGGER.warn("Failed to load dependency world for procedure AdminCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("warp"))) {
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("hub"))) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					WarpToRiftHubProcedure.executeProcedure($_dependencies);
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("rift"))) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("world", world);
					WarpToFallenRiftProcedure.executeProcedure($_dependencies);
				}
			}
		}
	}
}
