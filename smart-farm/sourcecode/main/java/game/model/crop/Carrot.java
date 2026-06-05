package game.model.crop;

import game.graphics.Sprite;

public class Carrot extends Crop {
    public Carrot() {
        super(4, 30, 20, 50, 10, 3, 10);
    }

    @Override
    public Sprite getSprite() {
        GrowthStage stage = getStage();
        if (stage == GrowthStage.DEAD) return Sprite.sDead1;
        if (stage == GrowthStage.SEED) return Sprite.sSeedGlobal;
        return switch (stage) {
            case STAGE1 -> Sprite.sCarrot1;
            case STAGE2 -> Sprite.sCarrot2;
            case STAGE3 -> Sprite.sCarrot3;
            case MATURE -> Sprite.sCarrot4;
            default     -> Sprite.sSeedGlobal;
        };
    }
}
