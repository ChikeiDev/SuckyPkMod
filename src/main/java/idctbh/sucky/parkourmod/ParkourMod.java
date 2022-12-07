package idctbh.sucky.parkourmod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import idctbh.sucky.parkourmod.options.ParkourOptions;
import idctbh.sucky.parkourmod.tick.SprintStartTick;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.apache.commons.io.FileUtils;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ParkourMod implements ModInitializer {

	public static final String MOD_ID = "parkourmod";

	private static ParkourOptions options;
	private static KeyBinding sprintKeyBinding;

	private static final File configFile = new File("config/parkourOpt.json");
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		sprintKeyBinding = new KeyBinding("parkour.options.toggleSprint", GLFW.GLFW_KEY_L, "parkour");
		KeyBindingHelper.registerKeyBinding(sprintKeyBinding);
		ClientTickEvents.START_CLIENT_TICK.register(new SprintStartTick());
	}

	public static ParkourOptions getOptions() {
		return options;
	}

	public static KeyBinding getSprintKeyBinding() {
		return sprintKeyBinding;
	}

	public static void saveOptions() {
		try {
			FileUtils.write(configFile, gson.toJson(options), StandardCharsets.UTF_8);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void loadOptions() {
		if (configFile.exists()) {
			try {
				String text = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8);
				options = gson.fromJson(text, ParkourOptions.class);
			} catch (IOException exception) {
				exception.printStackTrace();

				options = new ParkourOptions();
			}
		} else {
			options = new ParkourOptions();
		}
	}
}
