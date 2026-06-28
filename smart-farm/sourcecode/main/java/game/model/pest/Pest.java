package game.model.pest;

import game.model.crop.Crop;

public class Pest {

    public void attack(Crop crop) {
        if (crop != null) {
            crop.pestsAttack(1); // damage 1 is default 
        }
    }
}
