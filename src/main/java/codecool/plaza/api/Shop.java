package codecool.plaza.api;

import codecool.plaza.Exceptions.NoSuchProductException;
import codecool.plaza.Exceptions.OutOfStockException;
import codecool.plaza.Exceptions.ProductAlreadyExistsException;
import codecool.plaza.Exceptions.ShopIsClosedException;

import java.util.List;

public interface Shop {
    String getName();
    String getOwner();
    boolean isOpen();
    void open();
    void close();
    List<Product> findByName(String name) throws ShopIsClosedException, ShopIsClosedException;
    boolean hasProduct(long barcode) throws ShopIsClosedException;
    void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException, ProductAlreadyExistsException;
    void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException;
    Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException, NoSuchProductException, OutOfStockException;
    List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;
    String toString();
}
