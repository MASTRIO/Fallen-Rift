
package net.mcreator.fallenrift.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.fallenrift.item.LogoItem;
import net.mcreator.fallenrift.FallenRiftModElements;

@FallenRiftModElements.ModElement.Tag
public class CreativeTabItemGroup extends FallenRiftModElements.ModElement {
	public CreativeTabItemGroup(FallenRiftModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabcreative_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(LogoItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
