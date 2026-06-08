package game.view;

import game.GameContext;
import game.graphics.Sprite;
import game.model.crop.CropData;

import static game.GameConstants.*;

public class ShopRenderer extends BaseRenderer {

    @Override
    public void render(GameContext ctx) {
        ctx.screen.renderSprite(0, 0, Sprite.bgBlur, false);

        // Title board
        ctx.screen.renderSprite(SHOP_TITLE_X, SHOP_TITLE_Y, Sprite.titleBoard, false);
        ctx.guiFont.render(ctx.screen, "SEED SHOP", SHOP_TITLE_TEXT_X, SHOP_TITLE_TEXT_Y, COLOR_BOARD_TEXT, 1, false, false);

        int totalPages = (ctx.cropCatalog.size() + CROPS_PER_PAGE - 1) / CROPS_PER_PAGE;

        for (int i = 0; i < CROPS_PER_PAGE; i++) {
            int cropIdx = ctx.shopPage * CROPS_PER_PAGE + i;
            if (cropIdx >= ctx.cropCatalog.size()) break;

            CropData crop = ctx.cropCatalog.get(cropIdx);

            ctx.screen.renderSprite(SHOP_BOARD_X, SHOP_BOARD_YS[i], Sprite.woodenBoard, false);

            int spriteX   = SHOP_BOARD_X + SHOP_SPRITE_OFFSET_X;
            int spriteY   = SHOP_BOARD_YS[i] + SHOP_SPRITE_OFFSET_Y;
            ctx.screen.renderSprite(spriteX, spriteY, CropSpriteMapper.getSprite(crop.name + "_MATURE"), false);

            int textX     = SHOP_BOARD_X + SHOP_TEXT_OFFSET_X;
            int textBaseY = SHOP_BOARD_YS[i] + SHOP_TEXT_OFFSET_Y;

            ctx.guiFont.render(ctx.screen, "[" + crop.name.toUpperCase() + "]" + "  COST: $" + crop.cost + "  SELL: $" + crop.harvestValue,
                textX, textBaseY, COLOR_BOARD_TEXT, 1, false, false);
            ctx.guiFont.render(ctx.screen, "GROWS: " + crop.growthDays + " DAYS" + "  MOISTURE: " + crop.minMoistureThreshold + "-" + crop.maxMoistureThreshold,
                textX, textBaseY + SHOP_TEXT_LINE_SPACING, COLOR_BOARD_TEXT, 1, false, false);
            ctx.guiFont.render(ctx.screen,   "WATER: " + crop.dailyWaterRequirement + "/D" + "  NUT: " + crop.dailyNutrientRequirement + "/D",
                textX, textBaseY + SHOP_TEXT_LINE_SPACING * 2, COLOR_BOARD_TEXT, 1, false, false);
        }

        // Navigation buttons
        ctx.screen.renderSprite(SHOP_PREV_X, SHOP_NAV_Y, Sprite.prevPage, false);
        ctx.screen.renderSprite(SHOP_NEXT_X, SHOP_NAV_Y, Sprite.nextPage, false);

        String pageText = "PAGE " + (ctx.shopPage + 1) + "/" + totalPages;
        ctx.guiFont.render(ctx.screen, pageText, SHOP_PAGE_TEXT_X, SHOP_PAGE_TEXT_Y, COLOR_BOARD_TEXT, 1, false, false);
        ctx.guiFont.render(ctx.screen, "CLICK TO EQUIP  |  P/ESC: CLOSE", SHOP_HINT_X, SHOP_HINT_Y, COLOR_BOARD_TEXT, 1, false, false);
    }
}
