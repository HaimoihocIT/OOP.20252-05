package game.model.weather;

import game.graphics.Sprite;
import game.model.FarmCell;

public class Rainy extends BaseWeather {

    @Override
    public void apply(FarmCell cell) {
        cell.setMoistureLevel(cell.getMoistureLevel() + 20);
    }

    @Override
    public String getName() {
        return "Rainy";
    }

    @Override
    public Sprite getGrassSprite(int baseSeed) {
        return null;
    }

    @Override
    public Sprite getFarmlandSprite(boolean u, boolean d, boolean l, boolean r, int moisture) {
        return null;
    }
}
