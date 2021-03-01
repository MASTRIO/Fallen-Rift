
package net.mcreator.fallenrift.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Direction;
import net.minecraft.potion.Effects;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.fallenrift.itemgroup.CreativeTabItemGroup;
import net.mcreator.fallenrift.FallenRiftModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@FallenRiftModElements.ModElement.Tag
public class CrimsonShrubBlock extends FallenRiftModElements.ModElement {
	@ObjectHolder("fallen_rift:crimson_shrub")
	public static final Block block = null;
	public CrimsonShrubBlock(FallenRiftModElements instance) {
		super(instance, 10);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(CreativeTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("crimson_forest").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		FlowersFeature feature = new DefaultFlowersFeature(BlockClusterFeatureConfig.field_236587_a_) {
			@Override
			public BlockState getFlowerToPlace(Random random, BlockPos bp, BlockClusterFeatureConfig fc) {
				return block.getDefaultState();
			}

			@Override
			public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
				RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
				boolean dimensionCriteria = false;
				if (dimensionType == World.THE_NETHER)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				return super.generate(world, generator, random, pos, config);
			}
		};
		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> (ConfiguredFeature<?, ?>) feature
				.withConfiguration(
						(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(block.getDefaultState()), new SimpleBlockPlacer()))
								.tries(64).build())
				.withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(16));
	}
	public static class BlockCustomFlower extends FlowerBlock {
		public BlockCustomFlower() {
			super(Effects.SATURATION, 0,
					Block.Properties.create(Material.PLANTS, MaterialColor.TNT).doesNotBlockMovement().sound(SoundType.PLANT)
							.hardnessAndResistance(0f, 2f).setNeedsPostProcessing((bs, br, bp) -> true).setEmmisiveRendering((bs, br, bp) -> true)
							.setLightLevel(s -> 3));
			setRegistryName("crimson_shrub");
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 47;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public PlantType getPlantType(IBlockReader world, BlockPos pos) {
			return PlantType.NETHER;
		}
	}
}
