package game.model.weather;

import game.graphics.Sprite;

public abstract class BaseWeather implements Weather{

    protected static int getMoistureVariant(int moisture) {
        if (moisture >= 80) return 2;
        if (moisture >= 40) return 1;
        return 0;
    }

    protected static Sprite pick(int variant, Sprite dry, Sprite wet1, Sprite wet2) {
        return switch (variant) {
            case 1  -> wet1;
            case 2  -> wet2;
            default -> dry;
        };
    }
    
}
