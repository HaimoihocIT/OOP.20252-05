package game.model.crop;

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

    private final Supplier<Crop> factory;

    private static final List<CropData> ALL_CROPS = List.of(
            new CropData("Rice", 20, 6, 20, 50, 80, 20, 65, Rice::new),
            new CropData("Cabbage", 12, 5, 10, 30, 60, 20, 40, Cabbage::new),
            new CropData("Corn", 28, 6, 20, 40, 70, 20, 80, Corn::new),
            new CropData("Carrot", 8, 4, 10, 20, 50, 10, 30, Carrot::new),
            new CropData("Radish", 5, 4, 10, 20, 50, 10, 20, Radish::new),
            new CropData("Tomato", 16, 6, 20, 30, 60, 20, 50, Tomato::new),
            new CropData("Pumpkin", 50, 9, 30, 50, 80, 30, 140, Pumpkin::new),
            new CropData("Eggplant", 32, 7, 20, 40, 70, 30, 90, Eggplant::new),
            new CropData("Chili", 24, 6, 20, 30, 60, 30, 70, Chili::new),
            new CropData("Pepper", 20, 6, 20, 30, 60, 20, 60, Pepper::new)
    );

    public CropData(String name, int cost, int growthDays, int dailyWaterRequirement, int minMoistureThreshold, int maxMoistureThreshold, int dailyNutrientRequirement, int harvestValue, Supplier<Crop> factory){
        this.name = name;
        this.cost = cost;
        this.growthDays = growthDays;
        this.dailyWaterRequirement = dailyWaterRequirement;
        this.minMoistureThreshold = minMoistureThreshold;
        this.maxMoistureThreshold = maxMoistureThreshold;
        this.dailyNutrientRequirement = dailyNutrientRequirement;
        this.harvestValue = harvestValue;
        this.factory = factory;
    }

    public Crop createCrop() {
        return factory.get();
    }

    public static List<CropData> getAllCrops() {
        return ALL_CROPS;
    }
}
