package game.view;

import game.GameContext;
import game.graphics.Sprite;
import game.input.Tool;
import game.model.crop.Crop;
import game.model.FarmCell;
import game.model.weather.Rainy;
import game.model.weather.Weather;
import game.model.weather.TileAppearance;
import static game.GameConstants.*;


public class GameRenderer extends BaseRenderer {

    public GameRenderer() {
    }

    public Sprite getSpriteForCrop(Crop crop) {
        return CropSpriteMapper.getSprite(crop.getSpriteKey());
    }

    @Override
    public void render(GameContext ctx) {
        renderGrid(ctx);
        renderSelection(ctx);
        renderHUD(ctx);

        Weather w = ctx.grid.getCurrentWeather();
        if (w instanceof Rainy) {
            ctx.screen.applyRainOverlay(ctx.tickCounter);
        }
        
    }

    // Grid 

    private void renderGrid(GameContext ctx) {
        Weather weather = ctx.grid.getCurrentWeather();

        for (int y = 0; y < ctx.grid.getCols(); y++) {
            for (int x = 0; x < ctx.grid.getRows(); x++) {
                FarmCell cell = ctx.grid.getCell(x, y);
                char type    = ctx.grid.getTileType(x, y);

                TileAppearance appearance;
                if (type == 'S') {
                    boolean u = ctx.grid.getTileType(x, y - 1) == 'S';
                    boolean d = ctx.grid.getTileType(x, y + 1) == 'S';
                    boolean l = ctx.grid.getTileType(x - 1, y) == 'S';
                    boolean r = ctx.grid.getTileType(x + 1, y) == 'S';
                    // Delegate appearance selection entirely to the Weather object
                    appearance = weather.getFarmlandAppearance(u, d, l, r, cell.getMoistureLevel());
                } else {
                    int baseSeed = Math.abs(x * 7 + y * 13);
                    appearance = weather.getGrassAppearance(baseSeed);
                }
                Sprite sprite = WeatherSpriteMapper.getSprite(appearance);

                ctx.screen.renderSprite(x * TILE_SIZE, y * TILE_SIZE, sprite, false);

                // Render crop
                Crop crop = cell.getCurrentCrop();
                if (crop != null) {
                    Sprite cropSprite = CropSpriteMapper.getSprite(crop.getSpriteKey());
                    if (cropSprite != null) {
                        int yOffset = (cropSprite.getHeight() > TILE_SIZE) ? -TILE_SIZE : 0;
                        ctx.screen.renderSprite(x * TILE_SIZE, y * TILE_SIZE + yOffset, cropSprite, false);
                    }
                }

                // Render pest indicator
                if (cell.hasPests()) {
                    ctx.screen.renderSprite(x * TILE_SIZE, y * TILE_SIZE, Sprite.ladybug, false);
                }
            }
        }
    }

    // Selection highlight 

    private void renderSelection(GameContext ctx) {
        if (ctx.selectedX != -1) {
            ctx.screen.renderOutline(
                ctx.selectedX * TILE_SIZE,
                ctx.selectedY * TILE_SIZE,
                TILE_SIZE, TILE_SIZE, COLOR_WHITE);
        }
    }

    // HUD

    private void renderHUD(GameContext ctx) {
        ctx.guiFont.render(ctx.screen, "BALANCE: $" + ctx.getBalance(), SCREEN_WIDTH - 200, 2, COLOR_WHITE, 1, true, false);
        ctx.guiFont.render(ctx.screen, "DAY: " + ctx.getDay(),          SCREEN_WIDTH - 110, 2, COLOR_WHITE, 1, true, false);

        ctx.guiFont.render(ctx.screen, ctx.message, HUD_INFO_X, 5, COLOR_WHITE, 1, true, false);
        ctx.guiFont.render(ctx.screen, "ESC:MENU", SCREEN_WIDTH - 50, 2, COLOR_WHITE, 1, true, false);

        int hudX     = HUD_INFO_X;
        int hudYStart = HUD_INFO_Y;

        ctx.guiFont.render(ctx.screen, "Weather: " + ctx.grid.getCurrentWeather().getName(), hudX, 17, COLOR_WHITE, 1, true, false);
        ctx.guiFont.render(ctx.screen, "TOOL: " + ctx.selectedTool.toString().replace("_", " "), hudX, hudYStart - 12, COLOR_WHITE, 1, true, false);

        if (ctx.selectedX != -1) {
            FarmCell cell = ctx.grid.getCell(ctx.selectedX, ctx.selectedY);
            if (cell.getCurrentCrop() != null) {
                String cropInfo = "CROP: " + cell.getCurrentCrop().getClass().getSimpleName().toUpperCase();
                ctx.guiFont.render(ctx.screen, cropInfo, hudX, hudYStart, COLOR_WHITE, 1, true, false);
                ctx.guiFont.render(ctx.screen, "PROG: " + cell.getCurrentCrop().getDaysPlanted() + "/" + cell.getCurrentCrop().getGrowthTime() + " [" + cell.getCurrentCrop().getStage().name() + "]", hudX, hudYStart + 12 , COLOR_WHITE, 1, true, false);
                ctx.guiFont.render(ctx.screen, "TOL:  "+ cell.getCurrentCrop().getStressDays() + "/" + cell.getCurrentCrop().getMaxStressDays() + " DAYS", hudX, hudYStart + 24, COLOR_WHITE, 1, true, false);
                
                ctx.guiFont.render(ctx.screen, "NUTR: " + cell.getNutrientLevel() + "  WATER: " + cell.getMoistureLevel(), hudX, hudYStart + 36, COLOR_WHITE, 1, true, false);
            } else {
                ctx.guiFont.render(ctx.screen, "NUTR: " + cell.getNutrientLevel(), hudX, hudYStart , COLOR_WHITE, 1, true, false);
                ctx.guiFont.render(ctx.screen, "WATER: " + cell.getMoistureLevel(), hudX, hudYStart + 12 , COLOR_WHITE, 1, true, false);
            }
        }

        // Tool icons
        int iconXBase = HUD_TOOL_ICON_X;
        Sprite seedToolIcon = CropSpriteMapper.getSprite(ctx.cropCatalog.get(ctx.seedIndex).name + "_MATURE");
        renderToolIcon(ctx, iconXBase,                     HUD_TOOL_ICON_Y, seedToolIcon, Tool.SEED_SHOP,    "1", -10);
        renderToolIcon(ctx, iconXBase + HUD_TOOL_SPACING,  HUD_TOOL_ICON_Y, Sprite.hoe,        Tool.HARVEST,      "2", 0);
        renderToolIcon(ctx, iconXBase + HUD_TOOL_SPACING * 2, HUD_TOOL_ICON_Y, Sprite.wateringCan, Tool.WATERING_CAN, "3", 0);
        renderToolIcon(ctx, iconXBase + HUD_TOOL_SPACING * 3, HUD_TOOL_ICON_Y, Sprite.sword,    Tool.SWORD,        "4", 0);
        renderToolIcon(ctx, iconXBase + HUD_TOOL_SPACING * 4, HUD_TOOL_ICON_Y, Sprite.fertilizer, Tool.FERTILIZER, "5", 0);

        // Advance Day button
        ctx.screen.renderSprite(HUD_ADV_BTN_X, HUD_ADV_BTN_Y, Sprite.advDayBtn, false);
        ctx.guiFont.render(ctx.screen, "ADV DAY", HUD_ADV_BTN_X + 8, 200, COLOR_BOARD_TEXT, 1, false, false);

        renderWeatherButtons(ctx);
        renderPestsButton(ctx);
        
    }

    private void renderToolIcon(GameContext ctx, int x, int y, Sprite s, Tool t, String label, int yOffset) {
        ctx.screen.renderSprite(x, y + yOffset, s, false);
        ctx.guiFont.render(ctx.screen, label, x + 5, y + 18, COLOR_WHITE, 1, true, false);
        if (ctx.selectedTool == t) {
            ctx.screen.renderSprite(x, y, Sprite.select, false);
        }
    }

    private void renderWeatherButtons(GameContext ctx) {
        int x = 370;
        int[] ys = {45, 80, 115};
        Sprite[] icons = {Sprite.sunnyIcon, Sprite.rainIcon, Sprite.heatwaveIcon};
        String[] weatherIds = {"WEATHER_SUNNY", "WEATHER_RAINY", "WEATHER_DROUGHT"};
        
        for (int i = 0; i < 3; i++) {
            ctx.screen.renderSprite(x, ys[i], icons[i], false);
            if (ctx.grid.getTriggerManager().isTriggerActive(weatherIds[i])) {
                renderOutline(ctx, x, ys[i], 26, 28, COLOR_WHITE);
            }
        }
    }

    private void renderPestsButton(GameContext ctx){
        ctx.screen.renderSprite(370, 150, Sprite.ladyBugIcon, false);
        if (ctx.grid.getTriggerManager().isTriggerActive("PEST")) {
            renderOutline(ctx, 370, 150, 26, 28, COLOR_WHITE);
        }
    }
    private void renderOutline(GameContext ctx, int x, int y, int w, int h, int color){
        ctx.screen.renderOutline(x, y, w, h, color);
        
    }
}
