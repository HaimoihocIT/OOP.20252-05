package game.model.crop;

import game.graphics.Sprite;

import java.util.List;
import java.util.function.Supplier;

public class CropData {
    public final String name;
    public final int cost;
    public final int growthDays;
    public final int dailyWaterRequirement;
    public final int minMoistureThreshold;
    public final int maxMoistureThreshold;
    public final int dailyNutrientRequirement;
    public final int harvestValue;
    public final Sprite matureSprite;

    private final Supplier<Crop> factory;

    private static final List<CropData> ALL_CROPS = List.of(
        new CropData("Rice", 36, 8, 20, 50, 80, 20, 100, Sprite.sRice4, Rice::new),
        new CropData("Cabbage", 12, 5, 10, 30, 60, 20, 40, Sprite.sCabbage4, Cabbage::new),
        new CropData("Corn", 28, 6, 20, 40, 70, 20, 80, Sprite.sCorn4, Corn::new),
        new CropData("Carrot", 8, 4, 10, 20, 50, 10, 30, Sprite.sCarrot4, Carrot::new),
        new CropData("Radish", 5, 4, 10, 20, 50, 10, 20, Sprite.sRadish4, Radish::new),
        new CropData("Tomato", 16, 6, 20, 30, 60, 20, 50, Sprite.sTomato4, Tomato::new),
        new CropData("Pumpkin", 50, 9, 30, 50, 80, 30, 140, Sprite.sPumpkin4, Pumpkin::new),
        new CropData("Eggplant", 32, 7, 20, 40, 70, 30, 90, Sprite.sEggplant4, Eggplant::new),
        new CropData("Chilli", 24, 6, 20, 30, 60, 30, 70, Sprite.sChili4, Chili::new),
        new CropData("Pepper", 20, 6, 20, 30, 60, 20, 60, Sprite.sPepper4, Pepper::new)
    );

    public CropData(String name, int cost, int growthDays, int dailyWaterRequirement, int minMoistureThreshold, int maxMoistureThreshold, int dailyNutrientRequirement, int harvestValue, Sprite matureSprite, Supplier<Crop> factory){
        this.name = name;
        this.cost = cost;
        this.growthDays = growthDays;
        this.dailyWaterRequirement = dailyWaterRequirement;
        this.minMoistureThreshold = minMoistureThreshold;
        this.maxMoistureThreshold = maxMoistureThreshold;
        this.dailyNutrientRequirement = dailyNutrientRequirement;
        this.harvestValue = harvestValue;
        this.matureSprite = matureSprite;
        this.factory = factory;
    }

    public Crop createCrop() {
        return factory.get();
    }

    public static List<CropData> getAllCrops() {
        return ALL_CROPS;
    }
}
