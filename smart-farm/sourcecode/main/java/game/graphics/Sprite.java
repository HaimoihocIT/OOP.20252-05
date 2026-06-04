package game.graphics;

public class Sprite {

	// Standard tile size for the game's grid system (16x16). 
	private final static int TSIZE = 16; 
	
	public final int SIZE; 
	
	private int x, y;
	private int width, height;
	
	
	public int[] pixels; 
	protected SpriteSheet sheet;
	

	

	/*
	Extracts a square sprite from a SpriteSheet.
	Converts tile-grid coordinates (x, y) to absolute pixel coordinates.
	*/
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size; 
		this.width = size;
		this.height = size;
		this.pixels = new int[SIZE * SIZE]; 
		this.x = x * size; 
		this.y = y * size; 
		this.sheet = sheet; 
		load();
	}

	// Extracts a rectangular sprite
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}


	// Copies pixel data from the parent SpriteSheet's 1D array to this Sprite's local array 
	// using index arithmetic (x + y * width).
	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH]; 
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// GLOBAL ASSET DECLARATIONS 
	// Terrain Assets: Base map tiles including seasonal variants (16x16)
	public static Sprite nGrass1 = new Sprite(TSIZE, 0, 0, SpriteSheet.test_tiles);
	public static Sprite nGrass2 = new Sprite(TSIZE, 1, 0, SpriteSheet.test_tiles);
	public static Sprite nGrass3 = new Sprite(TSIZE, 2, 0, SpriteSheet.test_tiles);
	public static Sprite nGrass4 = new Sprite(TSIZE, 3, 0, SpriteSheet.test_tiles);
	public static Sprite nGrass5 = new Sprite(TSIZE, 4, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassDry1 = new Sprite(TSIZE, 5, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassDry2 = new Sprite(TSIZE, 6, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassDry3 = new Sprite(TSIZE, 7, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassWinter1 = new Sprite(TSIZE, 8, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassWinter2 = new Sprite(TSIZE, 9, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassWinter3 = new Sprite(TSIZE, 10, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassWinter4 = new Sprite(TSIZE, 11, 0, SpriteSheet.test_tiles);
	public static Sprite nGrassWinter5 = new Sprite(TSIZE, 12, 0, SpriteSheet.test_tiles);

	// Farmland Assets: Auto-tiling sprites for standard soil and watered states
	public static Sprite fTopLeft = new Sprite(TSIZE, 0, 1, SpriteSheet.test_tiles);
	public static Sprite fTop = new Sprite(TSIZE, 1, 1, SpriteSheet.test_tiles);
	public static Sprite fTopRight = new Sprite(TSIZE, 2, 1, SpriteSheet.test_tiles);
	public static Sprite fTopLeftWet1 = new Sprite(TSIZE, 3, 1, SpriteSheet.test_tiles);
	public static Sprite fTopWet1 = new Sprite(TSIZE, 4, 1, SpriteSheet.test_tiles);
	public static Sprite fTopRightWet1 = new Sprite(TSIZE, 5, 1, SpriteSheet.test_tiles);
	public static Sprite fTopLeftWet2 = new Sprite(TSIZE, 6, 1, SpriteSheet.test_tiles);
	public static Sprite fTopWet2 = new Sprite(TSIZE, 7, 1, SpriteSheet.test_tiles);
	public static Sprite fTopRightWet2 = new Sprite(TSIZE, 8, 1, SpriteSheet.test_tiles);

	public static Sprite fLeft = new Sprite(TSIZE, 0, 2, SpriteSheet.test_tiles);
	public static Sprite fCenter = new Sprite(TSIZE, 1, 2, SpriteSheet.test_tiles);
	public static Sprite fRight = new Sprite(TSIZE, 2, 2, SpriteSheet.test_tiles);
	public static Sprite fLeftWet1 = new Sprite(TSIZE, 3, 2, SpriteSheet.test_tiles);
	public static Sprite fCenterWet1 = new Sprite(TSIZE, 4, 2, SpriteSheet.test_tiles);
	public static Sprite fRightWet1 = new Sprite(TSIZE, 5, 2, SpriteSheet.test_tiles);
	public static Sprite fLeftWet2 = new Sprite(TSIZE, 6, 2, SpriteSheet.test_tiles);
	public static Sprite fCenterWet2 = new Sprite(TSIZE, 7, 2, SpriteSheet.test_tiles);
	public static Sprite fRightWet2 = new Sprite(TSIZE, 8, 2, SpriteSheet.test_tiles);

	public static Sprite fBotLeft = new Sprite(TSIZE, 0, 3, SpriteSheet.test_tiles);
	public static Sprite fBot = new Sprite(TSIZE, 1, 3, SpriteSheet.test_tiles);
	public static Sprite fBotRight = new Sprite(TSIZE, 2, 3, SpriteSheet.test_tiles);
	public static Sprite fBotLeftWet1 = new Sprite(TSIZE, 3, 3, SpriteSheet.test_tiles);
	public static Sprite fBotWet1 = new Sprite(TSIZE, 4, 3, SpriteSheet.test_tiles);
	public static Sprite fBotRightWet1 = new Sprite(TSIZE, 5, 3, SpriteSheet.test_tiles);
	public static Sprite fBotLeftWet2 = new Sprite(TSIZE, 6, 3, SpriteSheet.test_tiles);
	public static Sprite fBotWet2 = new Sprite(TSIZE, 7, 3, SpriteSheet.test_tiles);
	public static Sprite fBotRightWet2 = new Sprite(TSIZE, 8, 3, SpriteSheet.test_tiles);

	// Farmland Assets: Winter seasonal soil variants
	public static Sprite fSnowTopLeft = new Sprite(TSIZE, 9, 1, SpriteSheet.test_tiles);
	public static Sprite fSnowTop = new Sprite(TSIZE, 10, 1, SpriteSheet.test_tiles);
	public static Sprite fSnowTopRight = new Sprite(TSIZE, 11, 1, SpriteSheet.test_tiles);
	public static Sprite fSnowLeft = new Sprite(TSIZE, 9, 2, SpriteSheet.test_tiles);
	public static Sprite fSnowCenter = new Sprite(TSIZE, 10, 2, SpriteSheet.test_tiles);
	public static Sprite fSnowRight = new Sprite(TSIZE, 11, 2, SpriteSheet.test_tiles);
	public static Sprite fSnowBotLeft = new Sprite(TSIZE, 9, 3, SpriteSheet.test_tiles);
	public static Sprite fSnowBot = new Sprite(TSIZE, 10, 3, SpriteSheet.test_tiles);
	public static Sprite fSnowBotRight = new Sprite(TSIZE, 11, 3, SpriteSheet.test_tiles);

	// Farmland Assets: Summer heatwave soil variants
	public static Sprite fHeatTopLeft = new Sprite(TSIZE, 0, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTop = new Sprite(TSIZE, 1, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopRight = new Sprite(TSIZE, 2, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopLeftWet1 = new Sprite(TSIZE, 3, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopWet1 = new Sprite(TSIZE, 4, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopRightWet1 = new Sprite(TSIZE, 5, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopLeftWet2 = new Sprite(TSIZE, 6, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopWet2 = new Sprite(TSIZE, 7, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatTopRightWet2 = new Sprite(TSIZE, 8, 4, SpriteSheet.test_tiles);
	public static Sprite fHeatLeft = new Sprite(TSIZE, 0, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatCenter = new Sprite(TSIZE, 1, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatRight = new Sprite(TSIZE, 2, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatLeftWet1 = new Sprite(TSIZE, 3, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatCenterWet1 = new Sprite(TSIZE, 4, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatRightWet1 = new Sprite(TSIZE, 5, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatLeftWet2 = new Sprite(TSIZE, 6, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatCenterWet2 = new Sprite(TSIZE, 7, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatRightWet2 = new Sprite(TSIZE, 8, 5, SpriteSheet.test_tiles);
	public static Sprite fHeatBotLeft = new Sprite(TSIZE, 0, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBot = new Sprite(TSIZE, 1, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotRight = new Sprite(TSIZE, 2, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotLeftWet1 = new Sprite(TSIZE, 3, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotWet1 = new Sprite(TSIZE, 4, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotRightWet1 = new Sprite(TSIZE, 5, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotLeftWet2 = new Sprite(TSIZE, 6, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotWet2 = new Sprite(TSIZE, 7, 6, SpriteSheet.test_tiles);
	public static Sprite fHeatBotRightWet2 = new Sprite(TSIZE, 8, 6, SpriteSheet.test_tiles);

	
	public static Sprite select = new Sprite(8, 0, 0, SpriteSheet.select);
	
	// Interactive Entities: Collectible items, tools
	public static Sprite sword = new Sprite(16, 0, 0, SpriteSheet.sword);
	public static Sprite fertilizer = new Sprite(16, 0, 0, SpriteSheet.fertilizerSheet);
	public static Sprite ladybug = new Sprite(16, 0, 0, SpriteSheet.ladybug);
	public static Sprite hoe = new Sprite(16, 0, 0, SpriteSheet.tools);
	public static Sprite wateringCan = new Sprite(16, 1, 0, SpriteSheet.tools);

	// Particle Effects: Animation frames for dynamic weather systems
	public static Sprite rain1 = new Sprite(16, 0, 0, SpriteSheet.rain);
	public static Sprite rain2 = new Sprite(16, 1, 0, SpriteSheet.rain);
	public static Sprite rain3 = new Sprite(16, 2, 0, SpriteSheet.rain);
	public static Sprite snow1 = new Sprite(16, 0, 0, SpriteSheet.snow);
	public static Sprite snow2 = new Sprite(16, 1, 0, SpriteSheet.snow);
	public static Sprite snow3 = new Sprite(16, 2, 0, SpriteSheet.snow);
	
	// Crop Entities: Multi-stage growth textures for farming mechanics
	public static Sprite sRice1 = new Sprite(16, 32, 0, 0, SpriteSheet.plant_test);
	public static Sprite sRice2 = new Sprite(16, 32, 1, 0, SpriteSheet.plant_test);
	public static Sprite sRice3 = new Sprite(16, 32, 2, 0, SpriteSheet.plant_test);
	public static Sprite sRice4 = new Sprite(16, 32, 3, 0, SpriteSheet.plant_test);
	public static Sprite sCabbage1 = new Sprite(16, 32, 4, 0, SpriteSheet.plant_test);
	public static Sprite sCabbage2 = new Sprite(16, 32, 5, 0, SpriteSheet.plant_test);
	public static Sprite sCabbage3 = new Sprite(16, 32, 6, 0, SpriteSheet.plant_test);
	public static Sprite sCabbage4 = new Sprite(16, 32, 7, 0, SpriteSheet.plant_test);
	public static Sprite sCorn1 = new Sprite(16, 32, 8, 0, SpriteSheet.plant_test);
	public static Sprite sCorn2 = new Sprite(16, 32, 9, 0, SpriteSheet.plant_test);
	public static Sprite sCorn3 = new Sprite(16, 32, 10, 0, SpriteSheet.plant_test);
	public static Sprite sCorn4 = new Sprite(16, 32, 11, 0, SpriteSheet.plant_test);
	public static Sprite sCarrot1 = new Sprite(16, 32, 12, 0, SpriteSheet.plant_test);
	public static Sprite sCarrot2 = new Sprite(16, 32, 13, 0, SpriteSheet.plant_test);
	public static Sprite sCarrot3 = new Sprite(16, 32, 14, 0, SpriteSheet.plant_test);
	public static Sprite sCarrot4 = new Sprite(16, 32, 15, 0, SpriteSheet.plant_test);

	public static Sprite sRadish1 = new Sprite(16, 32, 0, 1, SpriteSheet.plant_test);
	public static Sprite sRadish2 = new Sprite(16, 32, 1, 1, SpriteSheet.plant_test);
	public static Sprite sRadish3 = new Sprite(16, 32, 2, 1, SpriteSheet.plant_test);
	public static Sprite sRadish4 = new Sprite(16, 32, 3, 1, SpriteSheet.plant_test);
	public static Sprite sTomato1 = new Sprite(16, 32, 4, 1, SpriteSheet.plant_test);
	public static Sprite sTomato2 = new Sprite(16, 32, 5, 1, SpriteSheet.plant_test);
	public static Sprite sTomato3 = new Sprite(16, 32, 6, 1, SpriteSheet.plant_test);
	public static Sprite sTomato4 = new Sprite(16, 32, 7, 1, SpriteSheet.plant_test);
	public static Sprite sPumpkin1 = new Sprite(16, 32, 8, 1, SpriteSheet.plant_test);
	public static Sprite sPumpkin2 = new Sprite(16, 32, 9, 1, SpriteSheet.plant_test);
	public static Sprite sPumpkin3 = new Sprite(16, 32, 10, 1, SpriteSheet.plant_test);
	public static Sprite sPumpkin4 = new Sprite(16, 32, 11, 1, SpriteSheet.plant_test);
	public static Sprite sEggplant1 = new Sprite(16, 32, 12, 1, SpriteSheet.plant_test);
	public static Sprite sEggplant2 = new Sprite(16, 32, 13, 1, SpriteSheet.plant_test);
	public static Sprite sEggplant3 = new Sprite(16, 32, 14, 1, SpriteSheet.plant_test);
	public static Sprite sEggplant4 = new Sprite(16, 32, 15, 1, SpriteSheet.plant_test);

	public static Sprite sChili1 = new Sprite(16, 32, 0, 2, SpriteSheet.plant_test);
	public static Sprite sChili2 = new Sprite(16, 32, 1, 2, SpriteSheet.plant_test);
	public static Sprite sChili3 = new Sprite(16, 32, 2, 2, SpriteSheet.plant_test);
	public static Sprite sChili4 = new Sprite(16, 32, 3, 2, SpriteSheet.plant_test);
	public static Sprite sPepper1 = new Sprite(16, 32, 4, 2, SpriteSheet.plant_test);
	public static Sprite sPepper2 = new Sprite(16, 32, 5, 2, SpriteSheet.plant_test);
	public static Sprite sPepper3 = new Sprite(16, 32, 6, 2, SpriteSheet.plant_test);
	public static Sprite sPepper4 = new Sprite(16, 32, 7, 2, SpriteSheet.plant_test);

	public static Sprite sSeedGlobal = new Sprite(16, 32, 0, 3, SpriteSheet.plant_test);
	public static Sprite sDead1 = new Sprite(16, 32, 1, 3, SpriteSheet.plant_test);
	public static Sprite sDead2 = new Sprite(16, 32, 2, 3, SpriteSheet.plant_test);

	// GUI Assets: Rectangular sprites for menus and HUD 
	public static Sprite bgBlur = new Sprite(400, 225, 0, 0, SpriteSheet.bgBlurSheet);
	public static Sprite titleBoard = new Sprite(90, 27, 0, 0, SpriteSheet.titleBoardSheet);
	public static Sprite woodenBoard = new Sprite(308, 48, 0, 0, SpriteSheet.woodenBoardSheet);
	public static Sprite prevPage = new Sprite(26, 28, 0, 0, SpriteSheet.prevPageSheet);
	public static Sprite nextPage = new Sprite(26, 28, 0, 0, SpriteSheet.nextPageSheet);
	public static Sprite advDayBtn = new Sprite(52, 28, 0, 0, SpriteSheet.advDaySheet);
	
	public static Sprite sunnyIcon = new Sprite(26, 28, 0, 0, SpriteSheet.sunnyIconSheet);
	public static Sprite rainIcon = new Sprite(26, 28, 0, 0, SpriteSheet.rainIconSheet);
	public static Sprite snowIcon = new Sprite(26, 28, 0, 0, SpriteSheet.snowIconSheet);
	public static Sprite heatwaveIcon = new Sprite(26, 28, 0, 0, SpriteSheet.heatwaveIconSheet);
	public static Sprite ladyBugIcon = new Sprite(26, 28, 0, 0, SpriteSheet.ladyBugIconSheet);
	public static Sprite helpBoard = new Sprite(308, 155, 0, 0, SpriteSheet.helpBoardSheet);
	public static Sprite smallWoodenBoard = new Sprite(90, 27, 0, 0, SpriteSheet.smallWoodenBoardSheet);
	
	public static Sprite hoeAnimRight1 = new Sprite(32,0,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimRight2 = new Sprite(32,1,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimLeft1 = new Sprite(32,3,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimLeft2 = new Sprite(32,2,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimDown1 = new Sprite(32, 0, 1, SpriteSheet.animHoe);
	public static Sprite hoeAnimDown2 = new Sprite(32, 1, 1, SpriteSheet.animHoe);
}