package game.model.crop;


public class Corn extends Crop {
    Corn() {
        super(6, 80, 40, 70, 20, 3, 20);
    }

    @Override
    public String getCropName() {
        return "Corn";
    }
}
