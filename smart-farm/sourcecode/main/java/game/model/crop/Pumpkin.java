package game.model.crop;

import game.graphics.Sprite;

public class Pumpkin extends Crop {
    public Pumpkin() {
        super(9, 140, 50, 80, 30, 4, 30);
    }

    @Override
    public void checkSurvival() {
        if (getStressDays() >= getMaxStressDays() + 1) {
            die();
        }
    }

    @Override
    public Sprite getSprite() {
        GrowthStage stage = getStage();
        if (stage == GrowthStage.DEAD) return Sprite.sDead1;
        if (stage == GrowthStage.SEED) return Sprite.sSeedGlobal;
        return switch (stage) {
            case STAGE1 -> Sprite.sPumpkin1;
            case STAGE2 -> Sprite.sPumpkin2;
            case STAGE3 -> Sprite.sPumpkin3;
            case MATURE -> Sprite.sPumpkin4;
            default     -> Sprite.sSeedGlobal;
        };
    }
}
