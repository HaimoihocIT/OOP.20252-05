package game.model.crop;


public class Carrot extends Crop {
    Carrot() {
        super(4, 30, 20, 50, 10, 3, 10);
    }

    @Override
    public String getCropName() {
        return "Carrot";
    }
}
