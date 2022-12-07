package idctbh.sucky.parkourmod.options;

import idctbh.sucky.parkourmod.ParkourMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ToggleSprintHud extends DrawableHelper {

	public void render(MatrixStack stack, float partial) {
		MinecraftClient client = MinecraftClient.getInstance();
		client.getProfiler().push("toggleSprint");
		TextRenderer renderer = client.textRenderer;

		float x = ParkourMod.getOptions().toggleSprintHud.getX();
		float y = ParkourMod.getOptions().toggleSprintHud.getY();
		int color = ParkourMod.getOptions().toggleSprintHud.color;
		if (ParkourMod.getOptions().sprint) {
			String toggleSprintEnabled = Text.translatable("parkour.messages.toggleSprint.enabled").getString();
			renderer.drawWithShadow(stack, toggleSprintEnabled, x, y, color);
		} else {
			String toggleSprintDisabled = Text.translatable("parkour.messages.toggleSprint.disabled").getString();
			renderer.drawWithShadow(stack, toggleSprintDisabled, x, y, color);
		}
		client.getProfiler().pop();
	}
}
