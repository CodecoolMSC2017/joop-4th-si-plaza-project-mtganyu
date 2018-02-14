package codecool.plaza.api;

public class ClothingProduct extends Product {
    String material;
    String type;
    public ClothingProduct(long barcode, String manufacturer,String material,String type) {
        super(barcode, manufacturer);
        this.material = material;
        this.type=type;
    }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }
    public String toString(){
        return "";
    }
}
