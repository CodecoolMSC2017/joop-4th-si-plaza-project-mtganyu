package codecool.plaza.api;

public abstract class Product {
    long barcode;
    String manufacturer;

    public Product(long barcode, String manufacturer) {
        this.barcode = barcode;
        this.manufacturer = manufacturer;
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
