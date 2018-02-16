package codecool.plaza.api;

import codecool.plaza.Exceptions.*;

import java.util.List;

interface Plaza {

    List<Shop> getShops()throws PlazaIsClosedException;
    void addShop(Shop shop)throws ShopAlreadyExistsException, PlazaIsClosedException;
    void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException;
    Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException;
    boolean isOpen();
    void open() throws PlazaAlreadyOpenException;
    void close() throws PlazaAlreadyClosedException;
    String toString();
}
