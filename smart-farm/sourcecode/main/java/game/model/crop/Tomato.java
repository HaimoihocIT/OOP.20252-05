package game.model.crop;


public class Tomato extends Crop {
    public Tomato() {
        super(6, 50, 30, 60, 20, 3, 20);
    }

    @Override
    public String getCropName() {
        return "Tomato";
    }
}