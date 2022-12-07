package idctbh.sucky.parkourmod.mixin;

import idctbh.sucky.parkourmod.options.ParkourOptionScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

	@Final
	@Shadow
	private GameOptions settings;

	protected OptionsScreenMixin(Text title) {
		super(title);
	}

	@Inject(at = @At("TAIL"), method = "init()V")
	public void init(CallbackInfo info) {
		addDrawableChild(new ButtonWidget(width / 2 - 155, height / 6 + 144 - 6, 150, 20,
				Text.translatable("parkour.options.title"), (w) -> {
			client.setScreen(new ParkourOptionScreen(this, settings));
		}));
	}
}