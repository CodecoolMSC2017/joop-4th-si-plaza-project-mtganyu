package codecool.plaza.api;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodProduct extends Product implements Serializable {

    private int calories;
    private Date bestBefore;
    public FoodProduct(long barcode,String name, String manufacturer, int calories, Date bestBefore){
        super(barcode, name, manufacturer);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }

// --Commented out by Inspection START (2018. 02. 15. 23:10):
//    public boolean isStillConsumable() {
//        Date date = new Date();
//        String bestBeforeDate = new SimpleDateFormat("yyyy-MM-dd").format(bestBefore);
//        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
//        int compareDates = bestBeforeDate.compareTo(currentDate);
//        return compareDates >= 0;
//
//    }
// --Commented out by Inspection STOP (2018. 02. 15. 23:10)

    public int getCalories() {
        return calories;
    }

    public Date getBestBefore(){
        return bestBefore;
    }

    public String toString(){
        return "Product information: "+"\nbarcode: "+barcode+" | manufacturer: "+manufacturer+" | calories: "+calories+" | best before: "+bestBefore;
    }
}
