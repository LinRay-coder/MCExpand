package com.example.mcexpand.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.mcexpand.ModBlocks;
import com.example.mcexpand.block.SimpleFamily;
import com.example.mcexpand.block.StoneFamily;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

/**
 * 方块标签数据生成。
 *
 * <p>本 mod 所有石制方块都：
 * <ul>
 *   <li>加入 {@code minecraft:mineable/pickaxe}（用镐挖掘）；</li>
 *   <li>加入 {@code minecraft:needs_stone_tool}（至少需要石镐才掉落，与
 *       {@code requiresCorrectToolForDrops()} 配合）。</li>
 * </ul>
 */
public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		// 26.3 起 FabricTagsProvider#valueLookupBuilder 改名为 builder，
		// 且方块标签 appender 统一为 BlockItemTagAppender<Block>（只接受 ResourceKey，不再接受方块实例）。
		BlockItemTagAppender<Block> pickaxe = builder(BlockTags.MINEABLE_WITH_PICKAXE);
		BlockItemTagAppender<Block> needsStone = builder(BlockTags.NEEDS_STONE_TOOL);

		// 全套石头系列：需镐挖掘，且至少石镐才掉落（与 requiresCorrectToolForDrops 配合）。
		for (StoneFamily family : ModBlocks.FAMILIES) {
			for (Block block : family.all()) {
				addBlock(pickaxe, needsStone, block);
			}
		}

		// 简版系列（末地石 / 紫珀 / 下界红砖）：原版这些方块木镐即可挖掘掉落，
		// 因此只加入 mineable/pickaxe，不加 needs_stone_tool。
		for (SimpleFamily family : ModBlocks.SIMPLE_FAMILIES) {
			for (Block block : family.all()) {
				pickaxe.add(blockKey(block));
			}
		}
	}

	/** 取方块对应的注册键（26.3 的标签 appender 只接受 ResourceKey）。 */
	private static ResourceKey<Block> blockKey(Block block) {
		return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
	}

	private static void addBlock(
			BlockItemTagAppender<Block> pickaxe,
			BlockItemTagAppender<Block> needsStone,
			Block block) {
		ResourceKey<Block> key = blockKey(block);
		pickaxe.add(key);
		needsStone.add(key);
	}
}
