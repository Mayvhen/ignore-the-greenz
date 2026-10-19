package dev.seethroughleaves.client.mixin;

import dev.seethroughleaves.client.CrosshairFilter;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * The crosshair target is found with a block raycast that uses the OUTLINE shape of every block.
 * For leaves and other decorative foliage we pretend that shape is empty, so the ray goes
 * straight through them and lands on whatever is behind (logs, other blocks, mobs...).
 *
 * Only the client's own world is affected, so server-side raycasts (projectiles, AI, etc.)
 * and the integrated server keep behaving like vanilla.
 */
@Mixin(ClipContext.class)
public abstract class ClipContextMixin {
	@Shadow
	@Final
	private ClipContext.Block block;

	@Inject(method = "getBlockShape", at = @At("HEAD"), cancellable = true)
	private void seethroughleaves$ignoreFoliage(BlockState state, BlockGetter level, BlockPos pos,
			CallbackInfoReturnable<VoxelShape> cir) {
		if (this.block == ClipContext.Block.OUTLINE
				&& level == Minecraft.getInstance().level
				&& CrosshairFilter.shouldIgnore(state)) {
			cir.setReturnValue(Shapes.empty());
		}
	}
}
