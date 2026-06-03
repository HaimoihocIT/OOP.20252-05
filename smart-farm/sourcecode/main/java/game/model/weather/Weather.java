package game.model.weather;

import game.graphics.Sprite;
import game.model.FarmCell;

public interface Weather {
    void apply(FarmCell cell);

    String getName();

    Sprite getGrassSprite(int baseSeed);

    Sprite getFarmlandSprite(boolean up, boolean down, boolean left, boolean right, int moisture);
}
