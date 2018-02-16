package codecool.plaza.api;

import java.io.*;

public abstract class Product implements Serializable {

    String name;
    long barcode;
    String manufacturer;
    Product(long barcode, String name, String manufacturer){
        this.barcode = barcode;
        this.name = name;
        this.manufacturer = manufacturer;
    }


    public String getName(){
        return name;
    }

    public long getBarcode() {
        return barcode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String toString() {
        return "";
    }


}