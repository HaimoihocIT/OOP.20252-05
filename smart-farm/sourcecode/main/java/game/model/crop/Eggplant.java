package game.model.crop;


public class Eggplant extends Crop {
    public Eggplant() {
        super(7, 90, 40, 70, 20, 2, 30);
    }

    @Override
    public String getCropName() {
        return "Eggplant";
    }
}
