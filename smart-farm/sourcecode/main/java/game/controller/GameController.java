package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;
import game.input.Tool;
import game.model.FarmCell;
import game.model.crop.Crop;
import game.model.crop.GrowthStage;

import static game.GameConstants.*;

public class GameController implements StateUpdater{
    @Override
    public void update(GameContext ctx, InputManager inputManager) {
        int mx = ctx.mouse.getX() / ctx.scale;
        int my = ctx.mouse.getY() / ctx.scale;

        // Tool shortcuts (1-5)
        if(ctx.keyboard.k1) ctx.selectedTool = Tool.SEED_SHOP;
        else if(ctx.keyboard.k2) ctx.selectedTool = Tool.HARVEST;
        else if(ctx.keyboard.k3) ctx.selectedTool = Tool.WATERING_CAN;
        else if(ctx.keyboard.k4) ctx.selectedTool = Tool.SWORD;
        else if(ctx.keyboard.k5) ctx.selectedTool = Tool.FERTILIZER;

        // Tab - switch between seeds in the seed shop
        if (inputManager.isJustPressed(ctx.keyboard.tab, "tabCycle")) {
            if (ctx.selectedTool == Tool.SEED_SHOP){
                ctx.seedIndex = (ctx.seedIndex + 1) % SEED_COUNT;
                ctx.message = "Selected: " + ctx.cropCatalog.get(ctx.seedIndex).name;
            }
        }

        // [ and ] - change scale
        if (inputManager.isJustPressed(ctx.keyboard.bracketLeft || ctx.keyboard.bracketRight, "scaleKey")) {
            if (ctx.keyboard.bracketLeft) changeScale(ctx, -1);
            if (ctx.keyboard.bracketRight) changeScale(ctx, 1);
        }

        // Arrow keys — grid navigation
        if (inputManager.isJustPressed(ctx.keyboard.up || ctx.keyboard.down || ctx.keyboard.left || ctx.keyboard.right, "arrowGridNav")) {
            if (ctx.selectedX == -1) {
                ctx.selectedX = ctx.grid.getRows() / 2;
                ctx.selectedY = ctx.grid.getCols() / 2;
            } else {
                if (ctx.keyboard.up    && ctx.selectedY > 0) ctx.selectedY--;
                if (ctx.keyboard.down  && ctx.selectedY < ctx.grid.getCols()-1) ctx.selectedY++;
                if (ctx.keyboard.left  && ctx.selectedX > 0) ctx.selectedX--;
                if (ctx.keyboard.right && ctx.selectedX < ctx.grid.getRows()-1) ctx.selectedX++;
            }
        }

        // Space — apply tool on selected cell
        if (inputManager.isJustPressed(ctx.keyboard.space, "spaceTriggered")) {
            if (ctx.selectedX != -1 && ctx.selectedY != -1) {
                applyTool(ctx, ctx.selectedX, ctx.selectedY);
            }
        }

        // Escape — back to menu
        if (inputManager.isJustPressed(ctx.keyboard.escape, "escapeTriggered")) {
            ctx.handler.setState(GameState.MENU);
        }

        // Enter — advance day
        if (inputManager.isJustPressed(ctx.keyboard.enter, "enterTriggered")) {
            ctx.advanceDay();
            ctx.grid.advanceDay(ctx);
            checkGameOver(ctx);
        }

        // R(rainy)/D(drought)/S(sunny) - force weather
        if (inputManager.isJustPressed(ctx.keyboard.kR || ctx.keyboard.kD || ctx.keyboard.kS, "weatherShortcuts")) {
            if (ctx.keyboard.kR) ctx.grid.getTriggerManager().toggleTrigger("WEATHER_RAINY", ctx);
            else if (ctx.keyboard.kD) ctx.grid.getTriggerManager().toggleTrigger("WEATHER_DROUGHT", ctx);
            else if (ctx.keyboard.kS) ctx.grid.getTriggerManager().toggleTrigger("WEATHER_SUNNY", ctx);
        }

        // L - create Lady bugs
        if (inputManager.isJustPressed(ctx.keyboard.kL, "triggerPests")) {
            ctx.grid.getTriggerManager().toggleTrigger("PEST", ctx);
        }

        // P — open shop
        if (inputManager.isJustPressed(ctx.keyboard.kP, "pKeyTriggered")) {
            ctx.shopPage = 0;
            ctx.handler.setState(GameState.SHOP);
        }

        // Mouse left click
        if (inputManager.isJustPressed(ctx.mouse.getButton() == 1, "mouseLeft")) {
            handleClicks(ctx, mx, my);
        }
    }

    private void changeScale(GameContext ctx, int amount){
        int newScale = Math.max(MIN_SCALE, Math.min(MAX_SCALE, ctx.scale + amount));
        if (newScale != ctx.scale){
            ctx.scale = newScale;
            ctx.scaleChanged = true;
            ctx.message = "Resolution: " + (SCREEN_WIDTH * ctx.scale) + "x" + (SCREEN_HEIGHT * ctx.scale);
        }
    }

    private void handleClicks(GameContext ctx, int mx, int my){
        // Tool icon bar
        int iconXBase = HUD_TOOL_ICON_X;
        if (my >= HUD_TOOL_CLICK_Y_MIN && my <= HUD_TOOL_CLICK_Y_MAX) {
            if (mx >= iconXBase && mx < iconXBase + TILE_SIZE) {
                ctx.selectedTool = Tool.SEED_SHOP;
                return;
            }
            else if (mx >= iconXBase + HUD_TOOL_SPACING && mx < iconXBase + HUD_TOOL_SPACING + TILE_SIZE) {
                ctx.selectedTool = Tool.HARVEST;
                return;
            }
            else if (mx >= iconXBase + HUD_TOOL_SPACING * 2 && mx < iconXBase + HUD_TOOL_SPACING * 2 + TILE_SIZE) {
                ctx.selectedTool = Tool.WATERING_CAN;
                return;
            }
            else if (mx >= iconXBase + HUD_TOOL_SPACING * 3 && mx < iconXBase + HUD_TOOL_SPACING * 3 + TILE_SIZE) {
                ctx.selectedTool = Tool.SWORD;
                return;
            }
            else if (mx >= iconXBase + HUD_TOOL_SPACING * 4 && mx < iconXBase + HUD_TOOL_SPACING * 4 + TILE_SIZE) {
                ctx.selectedTool = Tool.FERTILIZER;
                return;
            }
        }

        // Advance Day button
        if (mx >= HUD_ADV_BTN_X && mx <= HUD_ADV_BTN_X + HUD_ADV_BTN_W && my >= HUD_ADV_BTN_Y && my <= HUD_ADV_BTN_Y + HUD_ADV_BTN_H) {
            ctx.advanceDay();
            ctx.grid.advanceDay(ctx);
            checkGameOver(ctx);
            return;
        }

        // ESC
        if (mx >= SCREEN_WIDTH - HUD_ESC_BTN_W && mx <= SCREEN_WIDTH && my >= 0 && my <= HUD_ESC_BTN_H) {
            ctx.handler.setState(GameState.MENU);
            return;
        }

        // Weather buttons
        String[] weatherIds = {"WEATHER_SUNNY", "WEATHER_RAINY", "WEATHER_DROUGHT"};
        if (mx >= EVENT_BTN_X && mx <= EVENT_BTN_X + EVENT_BTN_W) {
            for (int i = 0; i < 3; i++) {
                if(my >= WEATHER_BTN_YS[i] && my <= WEATHER_BTN_YS[i] + EVENT_BTN_H){
                    ctx.grid.getTriggerManager().toggleTrigger(weatherIds[i], ctx);
                    return;
                }
            }
        }

        // Pest button
        if (mx >= EVENT_BTN_X && mx <= EVENT_BTN_X + EVENT_BTN_W && my >= PEST_BTN_Y && my <= PEST_BTN_Y + EVENT_BTN_H) {
            ctx.grid.getTriggerManager().toggleTrigger("PEST", ctx);
            return;
        }

        // Grid cell
        int gx = mx / TILE_SIZE;
        int gy = my / TILE_SIZE;
        if (gx >= 0 && gx < ctx.grid.getRows() && gy >= 0 && gy < ctx.grid.getCols()) {
            ctx.selectedX = gx;
            ctx.selectedY = gy;
            applyTool(ctx, gx, gy);
        }
    }

    private void applyTool(GameContext ctx, int gx, int gy){
        FarmCell cell = ctx.grid.getCell(gx, gy);
        if (cell == null) return;

        switch (ctx.selectedTool) {
            case WATERING_CAN -> {
                cell.waterCell();
                ctx.message = "Watered cell " + gx + "," + gy;
            }
            case SWORD -> {
                cell.clearPests();
                ctx.message = "Cleared pests at " + gx + "," + gy;
            }
            case FERTILIZER -> {
                if (ctx.spend(FERTILIZER_COST)) {
                    cell.fertilizeCell();
                    ctx.message = "Fertilized soil (-$" + FERTILIZER_COST + ")";
                } else {
                    ctx.message = "Not enough money!";
                }
            }
            case SEED_SHOP -> {
                if (ctx.grid.getTileType(gx, gy) == 'S' && cell.getCurrentCrop() == null) {
                    int cost = ctx.cropCatalog.get(ctx.seedIndex).cost;
                    if (ctx.spend(cost)) {
                        Crop newCrop = ctx.cropCatalog.get(ctx.seedIndex).createCrop();
                        cell.plantCrop(newCrop);
                        ctx.message = "Planted " + ctx.cropCatalog.get(ctx.seedIndex).name + " (-$" + cost + ")";
                    } else {
                        ctx.message = "Not enough money!";
                    }
                }
            }
            case HARVEST -> {
                Crop crop = cell.getCurrentCrop();
                if (crop != null && crop.getStage() == GrowthStage.MATURE) {
                    int value = crop.harvest();
                    ctx.earn(value);
                    cell.removeCrop();
                    ctx.message = "Harvested (+$" + value + ")";
                } else if (crop != null && crop.getStage() == GrowthStage.DEAD) {
                    cell.removeCrop();
                    ctx.message = "Cleared dead crop";
                }
            }
            default -> {}
        }
    }

    private void checkGameOver(GameContext ctx){
        if (ctx.getBalance() < MIN_SEED_COST) {
            boolean hasActiveCrops = false;
            outer:
            for (int y = 0; y < ctx.grid.getCols(); y++) {
                for (int x = 0; x < ctx.grid.getRows(); x++) {
                    Crop c = ctx.grid.getCell(x, y).getCurrentCrop();
                    if (c != null && c.getStage() != GrowthStage.DEAD) {
                        hasActiveCrops = true;
                        break outer;
                    }
                }
            }
            if (!hasActiveCrops) {
                ctx.handler.setState(GameState.GAMEOVER);
            }
        }
    }

}
