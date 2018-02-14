package codecool.plaza.api;

import codecool.plaza.Exceptions.NoSuchShopException;
import codecool.plaza.Exceptions.PlazaIsClosedException;
import codecool.plaza.Exceptions.ShopAlreadyExistsException;
import codecool.plaza.api.Shop;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements codecool.plaza.api.Plaza {
    private final List<Shop>shops;
    private final String owner;
    private final String name;
    private boolean open;

    public PlazaImpl(String owner, String name) {
        shops = new ArrayList<>();
        this.owner = owner;
        this.name = name;
        open = false;
    }

    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
       if(isOpen()){
           return shops;
       }
       throw new PlazaIsClosedException("The plaza is closed");
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if(isOpen()){
            if(shops.contains(shop)){
                throw new ShopAlreadyExistsException("The shop already exists");
            }else{
                shops.add(shop);
        }
        }
        throw new PlazaIsClosedException("The plaza is closed");
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
    if(isOpen()){
        if(shops.contains(shop)){
            shops.remove(shop);
        }else{
            throw new NoSuchShopException("You cannot remove a shop if it's isnt there.");
        }
    }throw new PlazaIsClosedException("The plaza is closed");
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

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }
    public String toString(){
     return "Welcome to the"+getName()+"plaza!";
    }
}
