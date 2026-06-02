package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public int WIDTH, HEIGHT;
	public int[] pixels;

    private Sprite[] sprites;

    public Sprite[] getSprites() {
		return sprites;
	}

	
	//initialize spritesheet
	public SpriteSheet(String path, int size) {
		this.path = path;  
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[WIDTH * WIDTH]; //The array should hold as many pixels as the tilesheet is big
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);//Translates image loaded into an array of individual pixels
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Load in spritesheets here
	// Essential Farm Assets
	public static SpriteSheet sword = new SpriteSheet("/textures/sword.png", 16);
	public static SpriteSheet fertilizerSheet = new SpriteSheet("/textures/fertiilizer.png", 16);
	public static SpriteSheet select = new SpriteSheet("/textures/select.png", 8);
	public static SpriteSheet rain = new SpriteSheet("/textures/rain.png", 48);
	public static SpriteSheet snow = new SpriteSheet("/textures/snow.png", 48);
	public static SpriteSheet tools = new SpriteSheet("/textures/tools.png", 32);
	public static SpriteSheet animHoe = new SpriteSheet("/textures/hoeAnim.png", 128);
	public static SpriteSheet test_tiles = new SpriteSheet("/textures/test_tile01.png", 256);
	public static SpriteSheet ladybug = new SpriteSheet("/textures/ladybug.png", 16);
	public static SpriteSheet plant_test = new SpriteSheet("/textures/plant_test.png", 256);
	
	// Shop UI Sheets
	public static SpriteSheet bgBlurSheet = new SpriteSheet("/textures/bg_blur.png", 400, 225);
	public static SpriteSheet titleBoardSheet = new SpriteSheet("/textures/title_board.png", 90, 27);
	public static SpriteSheet woodenBoardSheet = new SpriteSheet("/textures/wooden_board.png", 308, 48);
	public static SpriteSheet prevPageSheet = new SpriteSheet("/textures/prev_page.png", 26, 28);
	public static SpriteSheet nextPageSheet = new SpriteSheet("/textures/next_page.png", 26, 28);
	public static SpriteSheet advDaySheet = new SpriteSheet("/textures/adv_day.png", 52, 28);
	
	// New UI Assets
	public static SpriteSheet sunnyIconSheet = new SpriteSheet("/textures/sunny_icon.png", 26, 28);
	public static SpriteSheet rainIconSheet = new SpriteSheet("/textures/rain_icon.png", 26, 28);
	public static SpriteSheet snowIconSheet = new SpriteSheet("/textures/snow_icon.png", 26, 28);
	public static SpriteSheet heatwaveIconSheet = new SpriteSheet("/textures/heatwave_icon.png", 26, 28);
	public static SpriteSheet ladyBugIconSheet = new SpriteSheet("/textures/ladybug_icon.png", 26, 28);
	public static SpriteSheet helpBoardSheet = new SpriteSheet("/textures/help_board.png", 308, 155);
	public static SpriteSheet smallWoodenBoardSheet = new SpriteSheet("/textures/small_wooden_board.png", 90, 27);
	
	
}