package game.model.crop;


public class Pepper extends Crop {
    Pepper() {
        super(6, 60, 30, 60, 20, 3, 20);
    }

    @Override
    public String getCropName() {
        return "Pepper";
    }
}
