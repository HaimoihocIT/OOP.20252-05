package game.model.crop;


public class Rice extends Crop {
    Rice() {
        super(6, 65, 50, 80, 20, 2, 20);
    }

    @Override
    public String getCropName() {
        return "Rice";
    }
}
