package game.model.weather;

public abstract class BaseWeather implements Weather{
    @Override
    public double getPestSpawnMultiplier(){
        return 1.0;
    }

    @Override
    public int getWaterRequirement() { 
        return 0; 
    }

    @Override
    public TileAppearance getFarmlandAppearance(boolean u, boolean d, boolean l, boolean r, int moisture) {
        int variant = getMoistureVariant(moisture);
        if (!u && !l) 
            return pick(variant, TileAppearance.SOIL_TOP_LEFT,    TileAppearance.SOIL_TOP_LEFT_WET_1,    TileAppearance.SOIL_TOP_LEFT_WET_2);
        
        if (!u && !r) 
            return pick(variant, TileAppearance.SOIL_TOP_RIGHT,   TileAppearance.SOIL_TOP_RIGHT_WET_1,   TileAppearance.SOIL_TOP_RIGHT_WET_2);
        
        if (!d && !l) 
            return pick(variant, TileAppearance.SOIL_BOT_LEFT,    TileAppearance.SOIL_BOT_LEFT_WET_1,    TileAppearance.SOIL_BOT_LEFT_WET_2);
        
        if (!d && !r) 
            return pick(variant, TileAppearance.SOIL_BOT_RIGHT,   TileAppearance.SOIL_BOT_RIGHT_WET_1,   TileAppearance.SOIL_BOT_RIGHT_WET_2);
        
        if (!u)       
            return pick(variant, TileAppearance.SOIL_TOP,         TileAppearance.SOIL_TOP_WET_1,         TileAppearance.SOIL_TOP_WET_2);
        
        if (!d)       
            return pick(variant, TileAppearance.SOIL_BOT,         TileAppearance.SOIL_BOT_WET_1,         TileAppearance.SOIL_BOT_WET_2);
        
        if (!l)       
            return pick(variant, TileAppearance.SOIL_LEFT,        TileAppearance.SOIL_LEFT_WET_1,        TileAppearance.SOIL_LEFT_WET_2);
        
        if (!r)       
            return pick(variant, TileAppearance.SOIL_RIGHT,       TileAppearance.SOIL_RIGHT_WET_1,       TileAppearance.SOIL_RIGHT_WET_2);
        
        return pick(variant, TileAppearance.SOIL_CENTER,      TileAppearance.SOIL_CENTER_WET_1,      TileAppearance.SOIL_CENTER_WET_2);
    }

    // helper method
    protected static int getMoistureVariant(int moisture) {
        if (moisture >= 80) 
            return 2;
        if (moisture >= 40) 
            return 1;
        return 0;
    }

    protected static TileAppearance pick(int variant, TileAppearance dry, TileAppearance wet1, TileAppearance wet2) {
        return switch (variant) {
            case 1  -> wet1;
            case 2  -> wet2;
            default -> dry;
        };
    }
    
}
