package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;
import game.input.Tool;

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
            ctx.day++;
            ctx.grid.advanceDay();
            checkGameOver(ctx);
        }

        // Weather cheat keys (R/D/S)

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

    }

    private void applyTool(GameContext ctx, int gx, int gy){

    }

    private void checkGameOver(GameContext ctx){

    }

}
