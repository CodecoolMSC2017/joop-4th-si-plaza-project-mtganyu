package codecool.plaza.api;

import java.io.*;

public abstract class Product {

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
    public void serializeProd() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./src/main/java/codecool/plaza/data/productser.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in productser.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

  /*  public static Product deserializeProd() {
        try {
            FileInputStream fileIn = new FileInputStream("./src/main/java/codecool/plaza/data/productser.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Product p = (Product) in.readObject();
            in.close();
            fileIn.close();
            return p;

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("product class not found");
            c.printStackTrace();
        }
        return null;
    }*/

}