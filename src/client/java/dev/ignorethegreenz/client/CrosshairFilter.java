package dev.ignorethegreenz.client;

import java.util.Set;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Decides which blocks the crosshair sees straight through.
 *
 * <p>Most of the work is done by vanilla tags:
 * <ul>
 *     <li>{@code #minecraft:leaves} – every kind of leaf.</li>
 *     <li>{@code #minecraft:replaceable_by_trees} – short/tall grass, ferns, dry grass,
 *     bushes, flowers, vines, glow lichen, hanging roots, leaf litter, pale moss carpet,
 *     nether sprouts and roots, seagrass, shelf mushrooms.</li>
 *     <li>{@code #minecraft:small_flowers} – dandelion, poppy, tulips and friends.</li>
 * </ul>
 *
 * <p>Deliberately left targetable, because they are useful: crops and their stems,
 * saplings and propagules, mushrooms, sweet berries, sugar cane, bamboo, dripleaves,
 * cave vines (glow berries), spore blossoms, cactus, azaleas and lily pads.
 */
public final class CrosshairFilter {
	/**
	 * Decorative plants that no vanilla tag groups together. Add or remove entries here
	 * to taste – that is the only place the block list lives.
	 */
	private static final Set<Block> DECORATIVE = Set.of(
			Blocks.RED_SHRUB,
			Blocks.PINK_PETALS,
			Blocks.WILDFLOWERS,
			Blocks.MOSS_CARPET,
			Blocks.TWISTING_VINES,
			Blocks.TWISTING_VINES_PLANT,
			Blocks.WEEPING_VINES,
			Blocks.WEEPING_VINES_PLANT);

	private CrosshairFilter() {
	}

	public static boolean shouldIgnore(BlockState state) {
		return state.is(BlockTags.LEAVES)
				|| state.is(BlockTags.REPLACEABLE_BY_TREES)
				|| state.is(BlockTags.SMALL_FLOWERS)
				|| DECORATIVE.contains(state.getBlock());
	}
}
