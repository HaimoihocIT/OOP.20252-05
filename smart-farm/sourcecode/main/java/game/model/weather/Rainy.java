package game.model.weather;

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
    public double getPestSpawnMultiplier() { 
        return 0.5; 
    }

    @Override
    public TileAppearance getGrassAppearance(int baseSeed) {
        // 70% chance of wet, 30% chance of normal 
        int chance = baseSeed % 10;
        if (chance < 7) {
            return (baseSeed % 2 == 0) ? TileAppearance.GRASS_WET_1 : TileAppearance.GRASS_WET_2;
        } else {
            
            int variant = baseSeed % 3;
            return switch (variant) {
                case 0  -> TileAppearance.GRASS_NORMAL_1;
                case 1  -> TileAppearance.GRASS_NORMAL_2;
                default -> TileAppearance.GRASS_NORMAL_3;
            };
        }
    }

}
