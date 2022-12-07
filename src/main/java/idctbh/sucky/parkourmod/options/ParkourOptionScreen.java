package idctbh.sucky.parkourmod.options;

import idctbh.sucky.parkourmod.ParkourMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ParkourOptionScreen extends GameOptionsScreen {

	private ButtonListWidget listWidget;

	private static ParkourOptions options() {
		return ParkourMod.getOptions();
	}

	public ParkourOptionScreen(Screen parent, GameOptions gameOptions) {
		super(parent, gameOptions, Text.translatable("parkour.options.title"));
	}

	@Override
	protected void init() {
		SimpleOption<?>[] options = {
				SimpleOption.ofBoolean("parkour.options.fullBright", options().fullBright, t -> options().fullBright = t),
				SimpleOption.ofBoolean("parkour.options.toggleSprint", options().toggleSprint, t -> options().toggleSprint = t),
				SimpleOption.ofBoolean("parkour.options.noHurtBobbing", options().noHurtBobbing, t -> options().noHurtBobbing = t),
				SimpleOption.ofBoolean("parkour.options.earthquakeFix", options().earthquakeFix, t -> options().earthquakeFix = t),
				SimpleOption.ofBoolean("parkour.options.potionHud", options().potionHud, t -> options().potionHud = t),
				SimpleOption.ofBoolean("thymine.options.noScreenBobbing", options().noScreenBobbing, t -> options().noScreenBobbing = t),
		};

		this.listWidget = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
		listWidget.addAll(options);

		addSelectableChild(listWidget);
		addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (buttonWidget) -> {
			close();
		}));
	}

	@Override
	public void close() {
		ParkourMod.saveOptions();
		super.close();
	}

	private static Text getPercentValueText(Text prefix, double value) {
		return Text.translatable("options.percent_value", prefix, (int) (value * 100.0));
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (super.mouseReleased(mouseX, mouseY, button)) return true;
		if (this.listWidget.mouseReleased(mouseX, mouseY, button)) return true;
		return false;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);
		listWidget.render(matrices, mouseX, mouseY, delta);
		drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 5, 16777215);
		super.render(matrices, mouseX, mouseY, delta);
	}
}
