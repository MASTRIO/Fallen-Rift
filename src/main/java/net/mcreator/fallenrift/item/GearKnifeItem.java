
package net.mcreator.fallenrift.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.player.PlayerEntity;

import net.mcreator.fallenrift.procedures.GearKnifeRightClickedInAirProcedure;
import net.mcreator.fallenrift.itemgroup.CreativeTabItemGroup;
import net.mcreator.fallenrift.FallenRiftModElements;

import java.util.Map;
import java.util.HashMap;

@FallenRiftModElements.ModElement.Tag
public class GearKnifeItem extends FallenRiftModElements.ModElement {
	@ObjectHolder("fallen_rift:gear_knife")
	public static final Item block = null;
	public GearKnifeItem(FallenRiftModElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 3;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(GearItem.block, (int) (1)));
			}
		}, 3, -3.7f, new Item.Properties().group(CreativeTabItemGroup.tab)) {
			@Override
			public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
				ActionResult<ItemStack> retval = super.onItemRightClick(world, entity, hand);
				ItemStack itemstack = retval.getResult();
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("world", world);
					GearKnifeRightClickedInAirProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("gear_knife"));
	}
}
