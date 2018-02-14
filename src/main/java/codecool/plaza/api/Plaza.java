package codecool.plaza.api;

import codecool.plaza.Exceptions.NoSuchShopException;
import codecool.plaza.Exceptions.PlazaIsClosedException;
import codecool.plaza.Exceptions.ShopAlreadyExistsException;

import java.util.List;

public interface Plaza {

    List<Shop> getShops()throws PlazaIsClosedException;
    void addShop(Shop shop)throws ShopAlreadyExistsException, PlazaIsClosedException;
    void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException;
    boolean isOpen();
    void open();
    void close();
    String toString();
}
