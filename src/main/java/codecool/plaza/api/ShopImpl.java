package codecool.plaza.api;

import java.io.*;
import java.util.*;

import codecool.plaza.Exceptions.*;

public class ShopImpl implements Shop, Serializable {

    private String name;
    private String owner;
    private boolean open;
    private Map<Long, ShopEntry> products;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
        open = false;
        products = new HashMap<Long, ShopEntry>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void open() {
        open = true;
    }

    @Override
    public void close() {
        open = false;
    }

    public List<Product> allProducts() throws ShopIsClosedException {
        List<Product> allProducts = new ArrayList<>();
        if (!isOpen()) {
            throw new ShopIsClosedException();
        }
        for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
            allProducts.add(entry.getValue().getProduct());
        }
        return allProducts;
    }

    @Override
    public List<Product> findByName(String name) throws ShopIsClosedException {
        if (isOpen()) {
            List<Product> productList = new ArrayList<Product>();
            for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
                if (entry.getValue().getProduct().getName().equals(name)) {
                    productList.add(entry.getValue().getProduct());
                }
            }
            return productList;
        }

        throw new ShopIsClosedException();
    }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    return true;
                }
            }
            return false;
        }
        throw new ShopIsClosedException();
    }

    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
                if (entry.getValue().getProduct().getBarcode() == product.getBarcode()) {
                    throw new ProductAlreadyExistsException();
                }
            }
            products.put(product.getBarcode(), new ShopEntry(product, quantity, price));

        }
        throw new ShopIsClosedException();
    }

    @Override
    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
                if (entry.getValue().getProduct().getBarcode() == barcode) {
                    entry.getValue().increaseQuantity(quantity);
                    return;
                }
                throw new NoSuchProductException();
            }
        }
        throw new ShopIsClosedException();
    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, ShopIsClosedException, OutOfStockException {
        return null;
    }

    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    return entry.getValue().getPrice();
                }
            }
            throw new NoSuchProductException();
        }
        throw new ShopIsClosedException();
    }

    @Override
    public int getQuantity(long barcode) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    return entry.getValue().getQuantity();
                }
            }
            throw new NoSuchProductException();
        }
        throw new ShopIsClosedException();
    }


    public String toString() {
        String allProducts = "";
        allProducts += "ClothingProducts: \n======================";
        for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
            if (entry.getValue().getProduct() instanceof ClothingProduct) {
                allProducts += "\n" + entry.getValue().getProduct().toString() + " | price: " + entry.getValue().getPrice() + " ft";
            }
        }

        allProducts += "\nFoodProducts: \n======================";
        for (Map.Entry<Long, ShopEntry> entry : products.entrySet()) {
            if (entry.getValue().getProduct() instanceof FoodProduct) {
                allProducts += "\n" + entry.getValue().getProduct().toString() + " | price: " + entry.getValue().getPrice() + " ft";
            }

        }
        return allProducts;
    }

    public void serializeShop() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./src/main/java/codecool/plaza/data/shopser.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in shopser.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ShopImpl deserializeShop() {
        try {
            FileInputStream fileIn = new FileInputStream("./src/main/java/codecool/plaza/data/shopser.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ShopImpl shop = (ShopImpl) in.readObject();
            in.close();
            fileIn.close();
            return shop;

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Shop class not found");
            c.printStackTrace();
        }
        return null;
    }

    class ShopEntry implements Serializable {

        private Product product;
        private int quantity;
        private float price;

        ShopEntry(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        void increaseQuantity(int amount) {
            quantity += amount;
        }

        void decreaseQuantity(int amount) {
            quantity -= amount;
        }

        float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }
}


