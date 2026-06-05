package game.model.crop;

import game.graphics.Sprite;

public class Pepper extends Crop {
    public Pepper() {
        super(6, 60, 30, 60, 20, 3, 20);
    }

    @Override
    public Sprite getSprite() {
        GrowthStage stage = getStage();
        if (stage == GrowthStage.DEAD) return Sprite.sDead2;
        if (stage == GrowthStage.SEED) return Sprite.sSeedGlobal;
        return switch (stage) {
            case STAGE1 -> Sprite.sPepper1;
            case STAGE2 -> Sprite.sPepper2;
            case STAGE3 -> Sprite.sPepper3;
            case MATURE -> Sprite.sPepper4;
            default     -> Sprite.sSeedGlobal;
        };
    }
}
