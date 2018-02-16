package codecool.plaza.api;

import java.io.*;

public class ClothingProduct extends Product implements Serializable{

    private  String material;
    private  String type;
    public ClothingProduct(long barcode, String name, String manufacturer, String material, String type){
        super(barcode, name, manufacturer);
        this.material = material;
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Product information: "+"\nproduct name: "+name+" | barcode: "+barcode+" | manufacturer: "+manufacturer+" | product material: "+material+" | type: "+type;
    }

}