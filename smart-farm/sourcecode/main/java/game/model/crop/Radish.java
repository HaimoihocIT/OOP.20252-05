package game.model.crop;

import game.graphics.Sprite;

public class Radish extends Crop {
    public Radish(){
        super(4, 20, 20, 50, 10, 3, 10);
    }

    @Override
    public Sprite getSprite() {
        GrowthStage stage = getStage();
        if (stage == GrowthStage.DEAD) return Sprite.sDead1;
        if (stage == GrowthStage.SEED) return Sprite.sSeedGlobal;
        return switch (stage) {
            case STAGE1 -> Sprite.sRadish1;
            case STAGE2 -> Sprite.sRadish2;
            case STAGE3 -> Sprite.sRadish3;
            case MATURE -> Sprite.sRadish4;
            default     -> Sprite.sSeedGlobal;
        };
    }
}