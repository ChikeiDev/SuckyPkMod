package idctbh.sucky.parkourmod.mixin;

import idctbh.sucky.parkourmod.ParkourMod;
import idctbh.sucky.parkourmod.options.PotionHud;
import idctbh.sucky.parkourmod.options.ToggleSprintHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	private final ToggleSprintHud toggleSprintHud = new ToggleSprintHud();
	private final PotionHud potionHud = new PotionHud();

	@Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
	public void render(MatrixStack stack, float f, CallbackInfo info) {
		if (!ParkourMod.getOptions().toggleSprintHud.isHidden() && ParkourMod.getOptions().toggleSprint) {
			toggleSprintHud.render(stack, f);
		}
		if (ParkourMod.getOptions().potionHud) {
			potionHud.render(stack, f);
		}
	}
}