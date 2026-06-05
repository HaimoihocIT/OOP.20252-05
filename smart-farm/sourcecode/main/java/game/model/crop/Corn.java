package game.model.crop;

import game.graphics.Sprite;

public class Corn extends Crop {
    public Corn() {
        super(6, 80, 40, 70, 20, 3, 20);
    }

    @Override
    public Sprite getSprite() {
        GrowthStage stage = getStage();
        if (stage == GrowthStage.DEAD) return Sprite.sDead2;
        if (stage == GrowthStage.SEED) return Sprite.sSeedGlobal;
        return switch (stage) {
            case STAGE1 -> Sprite.sCorn1;
            case STAGE2 -> Sprite.sCorn2;
            case STAGE3 -> Sprite.sCorn3;
            case MATURE -> Sprite.sCorn4;
            default     -> Sprite.sSeedGlobal;
        };
    }
}
