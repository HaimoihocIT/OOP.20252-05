package game.model.weather;

import game.model.FarmCell;

public class Drought extends BaseWeather {

    @Override
    public void apply(FarmCell cell) {
        cell.setMoistureLevel(cell.getMoistureLevel() - 20);
    }

    @Override
    public String getName() {
        return "Drought";
    }

    @Override
    public int getWaterRequirement() { 
        return 10; 
    }

    @Override
    public double getPestSpawnMultiplier() { 
        return 1.5; 
    }

    @Override
    public TileAppearance getGrassAppearance(int baseSeed) {
        int variant = baseSeed % 3;
        return switch (variant) {
            case 0  -> TileAppearance.GRASS_DRY_1;
            case 1  -> TileAppearance.GRASS_DRY_2;
            default -> TileAppearance.GRASS_DRY_3;
        };
    }

    @Override
    public TileAppearance getFarmlandAppearance(boolean u, boolean d, boolean l, boolean r, int moisture) {
        int variant = getMoistureVariant(moisture);
        if (!u && !l) 
            return pick(variant, TileAppearance.SOIL_HEAT_TOP_LEFT,   TileAppearance.SOIL_HEAT_TOP_LEFT_WET_1,   TileAppearance.SOIL_HEAT_TOP_LEFT_WET_2);
        
        if (!u && !r) 
            return pick(variant, TileAppearance.SOIL_HEAT_TOP_RIGHT,  TileAppearance.SOIL_HEAT_TOP_RIGHT_WET_1,  TileAppearance.SOIL_HEAT_TOP_RIGHT_WET_2);
        
        if (!d && !l) 
            return pick(variant, TileAppearance.SOIL_HEAT_BOT_LEFT,   TileAppearance.SOIL_HEAT_BOT_LEFT_WET_1,   TileAppearance.SOIL_HEAT_BOT_LEFT_WET_2);
        
        if (!d && !r) 
            return pick(variant, TileAppearance.SOIL_HEAT_BOT_RIGHT,  TileAppearance.SOIL_HEAT_BOT_RIGHT_WET_1,  TileAppearance.SOIL_HEAT_BOT_RIGHT_WET_2);
        
        if (!u)       
            return pick(variant, TileAppearance.SOIL_HEAT_TOP,       TileAppearance.SOIL_HEAT_TOP_WET_1,       TileAppearance.SOIL_HEAT_TOP_WET_2);
        
        if (!d)       
            return pick(variant, TileAppearance.SOIL_HEAT_BOT,       TileAppearance.SOIL_HEAT_BOT_WET_1,       TileAppearance.SOIL_HEAT_BOT_WET_2);
        
        if (!l)       
            return pick(variant, TileAppearance.SOIL_HEAT_LEFT,      TileAppearance.SOIL_HEAT_LEFT_WET_1,      TileAppearance.SOIL_HEAT_LEFT_WET_2);
        
        if (!r)       
            return pick(variant, TileAppearance.SOIL_HEAT_RIGHT,     TileAppearance.SOIL_HEAT_RIGHT_WET_1,     TileAppearance.SOIL_HEAT_RIGHT_WET_2);
        
        return pick(variant, TileAppearance.SOIL_HEAT_CENTER,    TileAppearance.SOIL_HEAT_CENTER_WET_1,    TileAppearance.SOIL_HEAT_CENTER_WET_2);
    }
}
