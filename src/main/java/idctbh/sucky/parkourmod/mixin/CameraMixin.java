package idctbh.sucky.parkourmod.mixin;

import idctbh.sucky.parkourmod.ParkourMod;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {

	@Shadow private float cameraY;
	@Shadow private Entity focusedEntity;

	@Inject(method = "updateEyeHeight", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/Camera;cameraY:F", ordinal = 0))
	public void sneaktweak$modifyCameraY(CallbackInfo ci) {
		if (!ParkourMod.getOptions().earthquakeFix) {
			cameraY = focusedEntity.getStandingEyeHeight();
		}
	}

	@ModifyConstant(method = "updateEyeHeight", constant = @Constant(floatValue = 0.5f))
	public float sneaktweak$modifyCameraYSpeed(float modifier) {
		return ParkourMod.getOptions().earthquakeFix ? modifier * 1 : 0;
	}
}