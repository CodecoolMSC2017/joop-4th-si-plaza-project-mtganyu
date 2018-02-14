package codecool.plaza.api;

import codecool.plaza.Exceptions.NoSuchProductException;
import codecool.plaza.Exceptions.OutOfStockException;
import codecool.plaza.Exceptions.ProductAlreadyExistsException;
import codecool.plaza.Exceptions.ShopIsClosedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    private String name;
    private String owner;
    private Product product;
    private boolean open;
    private final Map<Long,ShopEntry> products;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
        open = false;
        products = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;

    }

    public void close() {
    open = false;
    }

    public List<Product> findByName(String name) throws ShopIsClosedException {
        return null;
    }

    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        return false;
    }

    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {

    }

    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {

    }

    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    private class ShopEntry {
    }
}
