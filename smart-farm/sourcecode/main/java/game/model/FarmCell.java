package game.model;
import game.model.crop.Crop;
import game.model.pest.Pest;

public class FarmCell {
    private int moistureLevel;
    private int nutrientLevel;
    private Pest pest;
    private Crop currentCrop;

    public FarmCell() {
        this.moistureLevel = 0;
        this.nutrientLevel = 0;
        this.pest = null;
        this.currentCrop = null;
    }

    public void waterCell() {
        this.moistureLevel += 10;
        if (moistureLevel > 100) 
            moistureLevel = 100;
    }

    public void fertilizeCell() {
        this.nutrientLevel += 10;
        if (nutrientLevel > 100) 
            nutrientLevel = 100;
    }

    public void clearPests() {
        this.pest = null;
    }

    public boolean hasPests() {
        return this.pest != null;
    }

    public void addPest(Pest pest) {
        this.pest = pest;
    }

    public Pest getPest() {
        return this.pest;
    }

    public boolean isAvailable() {
        return currentCrop == null;
    }

    public int getMoistureLevel() {
        return moistureLevel;
    }

    public int getNutrientLevel() {
        return nutrientLevel;
    }

    public void setNutrientLevel(int nutrientLevel) {
        if (nutrientLevel < 0) 
            nutrientLevel = 0;

        if (nutrientLevel > 100) 
            nutrientLevel = 100;
        this.nutrientLevel = nutrientLevel;
    }

    public void setMoistureLevel(int moistureLevel) {
        if (moistureLevel < 0) 
            moistureLevel = 0;
        
        if (moistureLevel > 100) 
            moistureLevel = 100;

        this.moistureLevel = moistureLevel;
    }

    public Crop getCurrentCrop() {
        return currentCrop;
    }

    public void plantCrop(Crop crop) {
        if (isAvailable()) {
            this.currentCrop = crop;
        }
    }

    public void removeCrop() {
        this.currentCrop = null;
    }
}
