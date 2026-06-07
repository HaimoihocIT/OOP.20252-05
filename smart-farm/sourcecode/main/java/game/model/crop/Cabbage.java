package game.model.crop;


public class Cabbage extends Crop {
    public Cabbage() {
        super(5, 40, 30, 60, 10, 3, 20);
    }

    @Override
    public String getCropName() {
        return "Cabbage";
    }
}
