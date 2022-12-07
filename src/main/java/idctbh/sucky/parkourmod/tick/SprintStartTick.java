package idctbh.sucky.parkourmod.tick;

import idctbh.sucky.parkourmod.ParkourMod;
import idctbh.sucky.parkourmod.options.ParkourOptions;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class SprintStartTick implements ClientTickEvents.StartTick {

	private boolean lastPressed;

	@Override
	public void onStartTick(MinecraftClient client) {
		ParkourOptions options = ParkourMod.getOptions();
		if (!options.toggleSprint) return;
		boolean pressed = ParkourMod.getSprintKeyBinding().isPressed();
		if (pressed && !lastPressed) {
			options.sprint = !options.sprint;
			if (!options.sprint)
				client.options.sprintKey.setPressed(false);
		}
		lastPressed = pressed;
		if (options.sprint)
			client.options.sprintKey.setPressed(true);
	}
}
