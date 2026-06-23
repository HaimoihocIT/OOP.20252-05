package game.model.crop;


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
    public String getCropName() {
        return "Pumpkin";
    }
}
