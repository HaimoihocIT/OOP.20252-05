package game;

public final class GameConstants {
    private GameConstants() {}
    public static final int SCREEN_WIDTH = 400;
    public static final int SCREEN_HEIGHT = SCREEN_WIDTH / 16 * 9;
    public static final int DEFAULT_SCALE = 3;
    public static final int MIN_SCALE = 1;
    public static final int MAX_SCALE = 6;

    public static final int SEED_COUNT = 10;

    public static final int GRID_ROWS = 25;
    public static final int GRID_COLS = 14;
    public static final int TILE_SIZE = 16;

    public static final int STARTING_DAY = 1;
    public static final int STARTING_BALANCE = 100;
    public static final int FERTILIZER_COST = 2;
    public static final int MIN_SEED_COST = 5;

    // UI BUTTONS
    public static final int BTN_SMALL_W = 90;
    public static final int BTN_SMALL_H = 27;
    public static final int BTN_CENTER_X = 155;

    // MENU
    // 4-button layout (Active game)
    public static final int MENU_4BTN_CONTINUE_Y = 70;
    public static final int MENU_4BTN_NEWGAME_Y = 100;
    public static final int MENU_4BTN_HELP_Y = 130;
    public static final int MENU_4BTN_QUIT_Y = 160;
    // 3-button layout (No active game)
    public static final int MENU_3BTN_START_Y = 70;
    public static final int MENU_3BTN_HELP_Y = 105;
    public static final int MENU_3BTN_QUIT_Y = 140;

    // GAMEOVER
    public static final int GAMEOVER_PLAY_AGAIN_Y = 120;
    public static final int GAMEOVER_EXIT_Y = 155;

    // HELP
    public static final int HELP_BACK_Y = 190;

    // SHOP
    public static final int CROPS_PER_PAGE = 3;
    public static final int SHOP_BOARD_X = 46;
    public static final int SHOP_BOARD_W = 308; // Width of each wooden board entry
    public static final int SHOP_BOARD_H = 48;  // Height of each wooden board entry
    public static final int[] SHOP_BOARD_YS = {45, 98, 151};
    public static final int SHOP_TITLE_X = 155;
    public static final int SHOP_TITLE_Y = 10;
    public static final int SHOP_TITLE_TEXT_X = 171;
    public static final int SHOP_TITLE_TEXT_Y = 19;
    public static final int SHOP_PREV_X = 129;
    public static final int SHOP_NEXT_X = 245;
    public static final int SHOP_NAV_Y = 200;
    public static final int SHOP_NAV_W = 26;
    public static final int SHOP_NAV_H = 28;
    public static final int SHOP_PAGE_TEXT_X = 174;
    public static final int SHOP_PAGE_TEXT_Y = 208;
    public static final int SHOP_HINT_X = 105;
    public static final int SHOP_HINT_Y = 222;
    public static final int SHOP_SPRITE_OFFSET_X = 10;
    public static final int SHOP_SPRITE_OFFSET_Y = 12;
    public static final int SHOP_TEXT_OFFSET_X = 35;
    public static final int SHOP_TEXT_OFFSET_Y = 5;
    public static final int SHOP_TEXT_LINE_SPACING = 12;

    // HUD
    public static final int HUD_TOOL_ICON_X = 210;
    public static final int HUD_TOOL_ICON_Y = 190;
    public static final int HUD_TOOL_SPACING = 20;
    public static final int HUD_TOOL_CLICK_Y_MIN = 182;
    public static final int HUD_TOOL_CLICK_Y_MAX = 210;
    public static final int HUD_ADV_BTN_X = 315;
    public static final int HUD_ADV_BTN_Y = 190;
    public static final int HUD_ADV_BTN_W = 52;
    public static final int HUD_ADV_BTN_H = 28;
    public static final int HUD_INFO_X = 3;
    public static final int HUD_INFO_Y = 175;

    // COLORS
    public static final int COLOR_BOARD_TEXT = 0xff603931;
    public static final int COLOR_MENU_BG = 0x1a1a2e;
    public static final int COLOR_WHITE = 0xffffffff;
    public static final int COLOR_YELLOW =  0xfffce323;
    public static final int COLOR_SUBTITLE = 0xffcccccc;
    public static final int COLOR_BTN_GREEN = 0x2d6a4f;
    public static final int COLOR_BTN_BLUE = 0x1d3557;
    public static final int COLOR_BTN_RED = 0x6b2737;
    public static final int COLOR_OVERLAY_BLACK = 0x000000;
    public static final int COLOR_OUTLINE_WHITE = 0xffffff;
    public static final int COLOR_TEXT_GRAY = 0xffaaaaaa;
    public static final int COLOR_TEXT_DIM = 0xff888888;
    public static final int COLOR_HELP_TEXT = 0xffdddddd;
    public static final int COLOR_NUTRIENT_TEXT = 0xffffffaa;

    // config the farm grid
    public static final String FARM_MAP_PATH = "/maps/farm_map.txt";
    public static final int PEST_SPAWN_BASE_DAYS = 2;
    public static final int PEST_SPAWN_RANDOM_DAYS = 2;

}
