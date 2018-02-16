package codecool.plaza.api;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodProduct extends Product implements Serializable {

    private int calories;
    private Date bestBefore;

    public FoodProduct(long barcode, String name, String manufacturer, int calories, Date bestBefore) {
        super(barcode, name, manufacturer);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }


    public int getCalories() {
        return calories;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public String toString() {
        return "Product information: " + "\nbarcode: " + barcode + " | manufacturer: " + manufacturer + " | calories: " + calories + " | best before: " + bestBefore;
    }
}
