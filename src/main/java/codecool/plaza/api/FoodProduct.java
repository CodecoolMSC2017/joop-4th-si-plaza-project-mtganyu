package codecool.plaza.api;

import java.util.Date;

public class FoodProduct extends Product {
    int calories;
    Date bestBefore;

    public FoodProduct(long barcode, String manufacturer,int calories,Date bestBefore) {
        super(barcode, manufacturer);
        this.calories= calories;
        this.bestBefore = bestBefore;
    }

    public boolean isStillConsumable(){
        return false;
    }
    public int getCalories(){
        return calories;
    }
    public String toString(){
        return "";
    }
}
