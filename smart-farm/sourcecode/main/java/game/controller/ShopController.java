package game.controller;

import game.GameContext;
import game.GameState;
import game.input.InputManager;
import game.input.Tool;

import static game.GameConstants.*;

public class ShopController implements StateUpdater{
    @Override
    public void update(GameContext ctx, InputManager inputManager){
        int mx = ctx.mouse.getX() / ctx.scale;
        int my = ctx.mouse.getY() / ctx.scale;

        // Close shop
        if (inputManager.isJustPressed(ctx.keyboard.kP, "pKeyTriggered") || inputManager.isJustPressed(ctx.keyboard.escape, "escapeTriggered")) {
            ctx.handler.setState(GameState.GAME);
        }

        int totalPages = (ctx.cropCatalog.size() + CROPS_PER_PAGE - 1) / CROPS_PER_PAGE;

        // Keyboard pagination
        if (inputManager.isJustPressed(ctx.keyboard.left, "arrowLeft")) {
            ctx.shopPage = (ctx.shopPage - 1 + totalPages) % totalPages;
        } else if (inputManager.isJustPressed(ctx.keyboard.right, "arrowRight")) {
            ctx.shopPage = (ctx.shopPage + 1) % totalPages;
        }

        if (inputManager.isJustPressed(ctx.mouse.getButton() == 1, "mouseLeft")) {
            // Prev page button
            if (mx >= SHOP_PREV_X && mx < SHOP_PREV_X + SHOP_NAV_W && my >= SHOP_NAV_Y && my < SHOP_NAV_Y + SHOP_NAV_H) {
                ctx.shopPage = (ctx.shopPage - 1 + totalPages) % totalPages;
            }
            // Next page button
            else if (mx >= SHOP_NEXT_X && mx < SHOP_NEXT_X + SHOP_NAV_W && my >= SHOP_NAV_Y && my < SHOP_NAV_Y + SHOP_NAV_H) {
                ctx.shopPage = (ctx.shopPage + 1) % totalPages;
            }
            // Click on a crop board
            else {
                for (int i = 0; i < CROPS_PER_PAGE; i++) {
                    int cropIdx = ctx.shopPage * CROPS_PER_PAGE + i;
                    if (cropIdx >= ctx.cropCatalog.size()) break;
                    if (mx >= SHOP_BOARD_X && mx < SHOP_BOARD_X + SHOP_BOARD_W && my >= SHOP_BOARD_YS[i] && my < SHOP_BOARD_YS[i] + SHOP_BOARD_H) {
                        ctx.seedIndex = cropIdx;
                        ctx.selectedTool = Tool.SEED_SHOP;
                        ctx.message = "Equipped: " + ctx.cropCatalog.get(cropIdx).name;
                        ctx.handler.setState(GameState.GAME);
                        break;
                    }
                }
            }
        }
    }
}
