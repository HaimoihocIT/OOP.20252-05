package game.model.crop;

import game.model.FarmCell;
import game.model.weather.Weather;

public abstract class Crop {
    private GrowthStage stage;
    private int daysPlanted, growthTime;
    private int baseValue;
    private int minMoistureThreshold, maxMoistureThreshold;
    private int stressDays, maxStressDays;
    private int dailyWaterRequirement, dailyNutrientRequirement;
    private boolean skipNextGrowthCycle;
    private int daysHasPests;

    public Crop(int growthTime, int baseValue, int minMoistureThreshold, int maxMoistureThreshold, int dailyWaterRequirement, int maxStressDays, int dailyNutrientRequirement){
        this.stage = GrowthStage.SEED;
        this.daysPlanted = 0;
        this.growthTime = growthTime;
        this.baseValue = baseValue;
        this.minMoistureThreshold = minMoistureThreshold;
        this.maxMoistureThreshold = maxMoistureThreshold;
        this.stressDays = 0;
        this.maxStressDays = maxStressDays;
        this.dailyWaterRequirement = dailyWaterRequirement;
        this.dailyNutrientRequirement = dailyNutrientRequirement;
        this.skipNextGrowthCycle = false;
        this.daysHasPests = 0;
    }

    public int getDaysPlanted(){
        return daysPlanted;
    }

    public int getGrowthTime(){
        return growthTime;
    }

    public GrowthStage getStage() {
        return stage;
    }

    public int getMaxStressDays(){
        return maxStressDays;
    }

    public int getStressDays(){
        return stressDays;
    }

    public void grow(){
        if(stage == GrowthStage.DEAD || stage == GrowthStage.MATURE) return;

        if(skipNextGrowthCycle){
            skipNextGrowthCycle = false;
            return;
        }

        daysPlanted++;

        double progress = (double) daysPlanted/ growthTime;
        if (progress >= 1.0)   stage = GrowthStage.MATURE;
        else if (progress >= 0.75) stage = GrowthStage.STAGE3;
        else if (progress >= 0.50) stage = GrowthStage.STAGE2;
        else if (progress >= 0.25) stage = GrowthStage.STAGE1;
    }

    public void consumeResources(FarmCell cell, Weather weather){
        if(stage == GrowthStage.DEAD) return;

        int waterUse = this.dailyWaterRequirement + weather.getWaterRequirement();

        int currentCellMoisture = cell.getMoistureLevel();
        if(currentCellMoisture >= minMoistureThreshold && currentCellMoisture <= maxMoistureThreshold){
            this.stressDays = 0;
        }
        else{
            this.stressDays++;
        }
        cell.setMoistureLevel(Math.max(0, currentCellMoisture - waterUse));

        boolean isEnoughNutrition = true;
        int currentCellNutrient = cell.getNutrientLevel();
        if(currentCellNutrient < this.dailyNutrientRequirement) isEnoughNutrition = false;
        cell.setNutrientLevel(Math.max(currentCellNutrient - this.dailyNutrientRequirement, 0));

        if(this.stressDays > 0 || ! (isEnoughNutrition)){
            skipNextGrowthCycle = true;
        }
        else{
            skipNextGrowthCycle = false;
        }
    }

    public int harvest(){
        if(stage == GrowthStage.MATURE) return baseValue;
        return 0;
    }

    private void updateMaxStressDays(){
        if(daysHasPests >= maxStressDays){
            maxStressDays--;
            daysHasPests = 0;
        }
    }

    public void checkSurvival() {
        if (stressDays >= maxStressDays) {
            stage = GrowthStage.DEAD;
        }
    }
    public void pestsAttack(){
        daysHasPests++;
        updateMaxStressDays();
    }

    protected void die(){
        this.stage = GrowthStage.DEAD;
    }

    public String getSpriteKey() {
        if (stage == GrowthStage.SEED) {
            return "Global_SEED";
        }
        return getCropName() + "_" + stage.name();
    }

    public abstract String getCropName();
}
