package game.view;

import game.graphics.Sprite;
import java.util.HashMap;
import java.util.Map;

public class CropSpriteMapper {
    private static final Map<String, Sprite> spriteCropMap = new HashMap<>();

    static {
        spriteCropMap.put("Global_SEED", Sprite.sSeedGlobal);
        
        spriteCropMap.put("Cabbage_DEAD", Sprite.sDead1);
        spriteCropMap.put("Cabbage_STAGE1", Sprite.sCabbage1);
        spriteCropMap.put("Cabbage_STAGE2", Sprite.sCabbage2);
        spriteCropMap.put("Cabbage_STAGE3", Sprite.sCabbage3);
        spriteCropMap.put("Cabbage_MATURE", Sprite.sCabbage4);
        
        spriteCropMap.put("Carrot_DEAD", Sprite.sDead1);
        spriteCropMap.put("Carrot_STAGE1", Sprite.sCarrot1);
        spriteCropMap.put("Carrot_STAGE2", Sprite.sCarrot2);
        spriteCropMap.put("Carrot_STAGE3", Sprite.sCarrot3);
        spriteCropMap.put("Carrot_MATURE", Sprite.sCarrot4);
        
        spriteCropMap.put("Chili_DEAD", Sprite.sDead1);
        spriteCropMap.put("Chili_STAGE1", Sprite.sChili1);
        spriteCropMap.put("Chili_STAGE2", Sprite.sChili2);
        spriteCropMap.put("Chili_STAGE3", Sprite.sChili3);
        spriteCropMap.put("Chili_MATURE", Sprite.sChili4);

        spriteCropMap.put("Corn_DEAD", Sprite.sDead2);
        spriteCropMap.put("Corn_STAGE1", Sprite.sCorn1);
        spriteCropMap.put("Corn_STAGE2", Sprite.sCorn2);
        spriteCropMap.put("Corn_STAGE3", Sprite.sCorn3);
        spriteCropMap.put("Corn_MATURE", Sprite.sCorn4);

        spriteCropMap.put("Eggplant_DEAD", Sprite.sDead1);
        spriteCropMap.put("Eggplant_STAGE1", Sprite.sEggplant1);
        spriteCropMap.put("Eggplant_STAGE2", Sprite.sEggplant2);
        spriteCropMap.put("Eggplant_STAGE3", Sprite.sEggplant3);
        spriteCropMap.put("Eggplant_MATURE", Sprite.sEggplant4);

        spriteCropMap.put("Pepper_DEAD", Sprite.sDead2);
        spriteCropMap.put("Pepper_STAGE1", Sprite.sPepper1);
        spriteCropMap.put("Pepper_STAGE2", Sprite.sPepper2);
        spriteCropMap.put("Pepper_STAGE3", Sprite.sPepper3);
        spriteCropMap.put("Pepper_MATURE", Sprite.sPepper4);

        spriteCropMap.put("Pumpkin_DEAD", Sprite.sDead1);
        spriteCropMap.put("Pumpkin_STAGE1", Sprite.sPumpkin1);
        spriteCropMap.put("Pumpkin_STAGE2", Sprite.sPumpkin2);
        spriteCropMap.put("Pumpkin_STAGE3", Sprite.sPumpkin3);
        spriteCropMap.put("Pumpkin_MATURE", Sprite.sPumpkin4);

        spriteCropMap.put("Radish_DEAD", Sprite.sDead1);
        spriteCropMap.put("Radish_STAGE1", Sprite.sRadish1);
        spriteCropMap.put("Radish_STAGE2", Sprite.sRadish2);
        spriteCropMap.put("Radish_STAGE3", Sprite.sRadish3);
        spriteCropMap.put("Radish_MATURE", Sprite.sRadish4);

        spriteCropMap.put("Rice_DEAD", Sprite.sDead2);
        spriteCropMap.put("Rice_STAGE1", Sprite.sRice1);
        spriteCropMap.put("Rice_STAGE2", Sprite.sRice2);
        spriteCropMap.put("Rice_STAGE3", Sprite.sRice3);
        spriteCropMap.put("Rice_MATURE", Sprite.sRice4);

        spriteCropMap.put("Tomato_DEAD", Sprite.sDead2);
        spriteCropMap.put("Tomato_STAGE1", Sprite.sTomato1);
        spriteCropMap.put("Tomato_STAGE2", Sprite.sTomato2);
        spriteCropMap.put("Tomato_STAGE3", Sprite.sTomato3);
        spriteCropMap.put("Tomato_MATURE", Sprite.sTomato4);
    }

    public static Sprite getSprite(String key) {
        return spriteCropMap.getOrDefault(key, Sprite.sSeedGlobal);
    }
}
