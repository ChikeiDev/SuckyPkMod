package idctbh.sucky.parkourmod.options;


public class ParkourOptions {
	public boolean fullBright;
	public boolean earthquakeFix;
	public boolean toggleSprint;
	public boolean sprint;
	public boolean noHurtBobbing;
	public boolean potionHud;
	public boolean noScreenBobbing;
	public ToggleSprintHudOptions toggleSprintHud;
	public PotionHudOptions potionHudOptions;

	public ParkourOptions() {
		fullBright = false;
		earthquakeFix = false;
		toggleSprint = false;
		sprint = false;
		noHurtBobbing = false;
		potionHud = false;
		noScreenBobbing = false;
		toggleSprintHud = new ToggleSprintHudOptions();
		toggleSprintHud.x = -200;
		toggleSprintHud.y = -15;
		toggleSprintHud.position = Position.CENTER_BOTTOM;
		toggleSprintHud.color = 0xE0E0E0;
		potionHudOptions = new PotionHudOptions();
		potionHudOptions.x = 10;
		potionHudOptions.y = 10;
		potionHudOptions.position = Position.LEFT_TOP;
		potionHudOptions.color = 0xE0E0E0;
	}
}
