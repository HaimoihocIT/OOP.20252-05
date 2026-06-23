package game.model.crop;


public class Radish extends Crop {
    public Radish(){
        super(4, 20, 20, 50, 10, 3, 10);
    }

    @Override
    public String getCropName() {
        return "Radish";
    }
}