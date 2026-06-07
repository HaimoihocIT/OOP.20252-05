package game.model.weather;

import game.model.FarmCell;

public class Sunny extends BaseWeather {

    @Override
    public void apply(FarmCell cell) {
        cell.setMoistureLevel(cell.getMoistureLevel() - 5);
    }

    @Override
    public String getName() {
        return "Sunny";
    }

    @Override
    public TileAppearance getGrassAppearance(int baseSeed) {
        int variant = baseSeed % 3;
        return switch (variant) {
            case 0 -> TileAppearance.GRASS_NORMAL_1;
            case 1 -> TileAppearance.GRASS_NORMAL_2;
            default -> TileAppearance.GRASS_NORMAL_3;
        };
    }

}
