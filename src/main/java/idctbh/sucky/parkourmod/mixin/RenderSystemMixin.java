package idctbh.sucky.parkourmod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import idctbh.sucky.parkourmod.ParkourMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {

	@Inject(at = @At("TAIL"), method = "initGameThread(Z)V")
	private static void initGameThread(CallbackInfo info) {
		ParkourMod.loadOptions();
	}
}