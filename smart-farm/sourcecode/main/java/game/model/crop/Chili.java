package game.model.crop;


public class Chili extends Crop {
    public Chili() {
        super(6, 70, 30, 60, 20, 2, 30);
    }

    @Override
    public String getCropName() {
        return "Chili";
    }
}
