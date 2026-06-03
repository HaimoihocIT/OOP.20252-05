package game.model;


public class FarmCell {
    private int moistureLevel;
    private int nutrientLevel;
    private boolean hasPests;

    public FarmCell() {
        this.moistureLevel = 0;
        this.nutrientLevel = 0;
        this.hasPests = false;
    }

    public void waterCell() {
        this.moistureLevel += 10;
        if (moistureLevel > 100) moistureLevel = 100;
    }

    public void fertilizeCell() {
        this.nutrientLevel += 20;
        if (nutrientLevel > 100) nutrientLevel = 100;
    }

    public void clearPests() {
        this.hasPests = false;
    }

    public boolean hasPests() {
        return hasPests;
    }

    public void setPests(boolean hasPests) {
        this.hasPests = hasPests;
    }

    public int getMoistureLevel() {
        return moistureLevel;
    }

    public int getNutrientLevel() {
        return nutrientLevel;
    }

    public void setNutrientLevel(int nutrientLevel) {
        if (nutrientLevel < 0) nutrientLevel = 0;
        if (nutrientLevel > 100) nutrientLevel = 100;
        this.nutrientLevel = nutrientLevel;
    }

    public void setMoistureLevel(int moistureLevel) {
        if (moistureLevel < 0) moistureLevel = 0;
        if (moistureLevel > 100) moistureLevel = 100;
        this.moistureLevel = moistureLevel;
    }

}
