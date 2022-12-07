package idctbh.sucky.parkourmod.mixin;

import idctbh.sucky.parkourmod.ParkourMod;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
abstract public class GameRendererMixin {

	@Inject(at = @At("HEAD"), method = "bobViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V", cancellable = true)
	public void bobViewWhenHurt(MatrixStack arg, float f, CallbackInfo info) {
		if (ParkourMod.getOptions().noHurtBobbing)
			info.cancel();
	}

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;bobView(Lnet/minecraft/client/util/math/MatrixStack;F)V"), method = "renderWorld")
	public void bobView(GameRenderer instance, MatrixStack matrices, float tickDelta) {
		if (!ParkourMod.getOptions().noScreenBobbing) {
			bobView(matrices, tickDelta);
		}
	}

	@Shadow
	protected abstract void bobView(MatrixStack matrices, float tickDelta);
}