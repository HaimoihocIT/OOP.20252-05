package game.model.crop;

import game.graphics.Sprite;

public class Rice extends Crop {
    public Rice() {
        super(8, 100, 50, 80, 20, 2, 20);
    }

    @Override
    public Sprite getSprite() {
        return null;
    }
}
