package game.model.crop;

import game.graphics.Sprite;
import game.model.crop.Crop;
import game.model.crop.GrowthStage;

public class Tomato extends Crop {
    public Tomato() {
        super(6, 50, 30, 60, 20, 3, 20);
    }

    @Override
    public Sprite getSprite() {
        GrowthStage stage = getStage();
        if (stage == GrowthStage.DEAD) return Sprite.sDead2;
        if (stage == GrowthStage.SEED) return Sprite.sSeedGlobal;
        return switch (stage) {
            case STAGE1 -> Sprite.sTomato1;
            case STAGE2 -> Sprite.sTomato2;
            case STAGE3 -> Sprite.sTomato3;
            case MATURE -> Sprite.sTomato4;
            default     -> Sprite.sSeedGlobal;
        };
    }
}