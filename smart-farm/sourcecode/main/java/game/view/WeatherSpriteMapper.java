package game.view;

import game.graphics.Sprite;
import game.model.weather.TileAppearance;

public class WeatherSpriteMapper {
    public static Sprite getSprite(TileAppearance appearance) {
        if (appearance == null) return Sprite.nGrass1;
        return switch (appearance) {
            case GRASS_NORMAL_1 -> Sprite.nGrass1;
            case GRASS_NORMAL_2 -> Sprite.nGrass2;
            case GRASS_NORMAL_3 -> Sprite.nGrass3;
            case GRASS_WET_1 -> Sprite.nGrass4;
            case GRASS_WET_2 -> Sprite.nGrass5;
            case GRASS_DRY_1 -> Sprite.nGrassDry1;
            case GRASS_DRY_2 -> Sprite.nGrassDry2;
            case GRASS_DRY_3 -> Sprite.nGrassDry3;
            case SOIL_TOP_LEFT -> Sprite.fTopLeft;
            case SOIL_TOP_LEFT_WET_1 -> Sprite.fTopLeftWet1;
            case SOIL_TOP_LEFT_WET_2 -> Sprite.fTopLeftWet2;
            case SOIL_TOP_RIGHT -> Sprite.fTopRight;
            case SOIL_TOP_RIGHT_WET_1 -> Sprite.fTopRightWet1;
            case SOIL_TOP_RIGHT_WET_2 -> Sprite.fTopRightWet2;
            case SOIL_BOT_LEFT -> Sprite.fBotLeft;
            case SOIL_BOT_LEFT_WET_1 -> Sprite.fBotLeftWet1;
            case SOIL_BOT_LEFT_WET_2 -> Sprite.fBotLeftWet2;
            case SOIL_BOT_RIGHT -> Sprite.fBotRight;
            case SOIL_BOT_RIGHT_WET_1 -> Sprite.fBotRightWet1;
            case SOIL_BOT_RIGHT_WET_2 -> Sprite.fBotRightWet2;
            case SOIL_TOP -> Sprite.fTop;
            case SOIL_TOP_WET_1 -> Sprite.fTopWet1;
            case SOIL_TOP_WET_2 -> Sprite.fTopWet2;
            case SOIL_BOT -> Sprite.fBot;
            case SOIL_BOT_WET_1 -> Sprite.fBotWet1;
            case SOIL_BOT_WET_2 -> Sprite.fBotWet2;
            case SOIL_LEFT -> Sprite.fLeft;
            case SOIL_LEFT_WET_1 -> Sprite.fLeftWet1;
            case SOIL_LEFT_WET_2 -> Sprite.fLeftWet2;
            case SOIL_RIGHT -> Sprite.fRight;
            case SOIL_RIGHT_WET_1 -> Sprite.fRightWet1;
            case SOIL_RIGHT_WET_2 -> Sprite.fRightWet2;
            case SOIL_CENTER -> Sprite.fCenter;
            case SOIL_CENTER_WET_1 -> Sprite.fCenterWet1;
            case SOIL_CENTER_WET_2 -> Sprite.fCenterWet2;

            case SOIL_HEAT_TOP_LEFT -> Sprite.fHeatTopLeft;
            case SOIL_HEAT_TOP_LEFT_WET_1 -> Sprite.fHeatTopLeftWet1;
            case SOIL_HEAT_TOP_LEFT_WET_2 -> Sprite.fHeatTopLeftWet2;
            case SOIL_HEAT_TOP_RIGHT -> Sprite.fHeatTopRight;
            case SOIL_HEAT_TOP_RIGHT_WET_1 -> Sprite.fHeatTopRightWet1;
            case SOIL_HEAT_TOP_RIGHT_WET_2 -> Sprite.fHeatTopRightWet2;
            case SOIL_HEAT_BOT_LEFT -> Sprite.fHeatBotLeft;
            case SOIL_HEAT_BOT_LEFT_WET_1 -> Sprite.fHeatBotLeftWet1;
            case SOIL_HEAT_BOT_LEFT_WET_2 -> Sprite.fHeatBotLeftWet2;
            case SOIL_HEAT_BOT_RIGHT -> Sprite.fHeatBotRight;
            case SOIL_HEAT_BOT_RIGHT_WET_1 -> Sprite.fHeatBotRightWet1;
            case SOIL_HEAT_BOT_RIGHT_WET_2 -> Sprite.fHeatBotRightWet2;
            case SOIL_HEAT_TOP -> Sprite.fHeatTop;
            case SOIL_HEAT_TOP_WET_1 -> Sprite.fHeatTopWet1;
            case SOIL_HEAT_TOP_WET_2 -> Sprite.fHeatTopWet2;
            case SOIL_HEAT_BOT -> Sprite.fHeatBot;
            case SOIL_HEAT_BOT_WET_1 -> Sprite.fHeatBotWet1;
            case SOIL_HEAT_BOT_WET_2 -> Sprite.fHeatBotWet2;
            case SOIL_HEAT_LEFT -> Sprite.fHeatLeft;
            case SOIL_HEAT_LEFT_WET_1 -> Sprite.fHeatLeftWet1;
            case SOIL_HEAT_LEFT_WET_2 -> Sprite.fHeatLeftWet2;
            case SOIL_HEAT_RIGHT -> Sprite.fHeatRight;
            case SOIL_HEAT_RIGHT_WET_1 -> Sprite.fHeatRightWet1;
            case SOIL_HEAT_RIGHT_WET_2 -> Sprite.fHeatRightWet2;
            case SOIL_HEAT_CENTER -> Sprite.fHeatCenter;
            case SOIL_HEAT_CENTER_WET_1 -> Sprite.fHeatCenterWet1;
            case SOIL_HEAT_CENTER_WET_2 -> Sprite.fHeatCenterWet2;
        };
    }
}
