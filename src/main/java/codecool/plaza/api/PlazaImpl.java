package codecool.plaza.api;

import codecool.plaza.Exceptions.*;
import codecool.plaza.api.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlazaImpl implements Plaza, Serializable {

    private List<Shop> shops;
    private final String name;
    private final String owner;
    private boolean open;

    public PlazaImpl(String name, String owner) {
        shops = new ArrayList<Shop>();
        this.name = name;
        this.owner = owner;
        open = false;
    }

    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        if (isOpen()) {
            return shops;
        }
        throw new PlazaIsClosedException();
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if (isOpen()) {
            for (Shop s : shops) {
                if (shop.getName().equals(s.getName())) {
                    throw new ShopAlreadyExistsException();
                }
            }
            shops.add(shop);
            return;
        }
        throw new PlazaIsClosedException();
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen()) {
            for (Shop s : shops) {
                if (shop.getName().equals(s.getName()) && shop.getOwner().equals(s.getOwner())) {
                    shops.remove(s);
                    return;
                }
            }
            throw new NoSuchShopException();
        }
        throw new PlazaIsClosedException();
    }

    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen()) {
            for (Shop shop : shops) {
                if ((shop.getName()).equals(name)) {
                    return shop;
                }
            }
            throw new NoSuchShopException();
        }
        throw new PlazaIsClosedException();
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

    public String getName() {
        return name;
    }

    public String toString() {
        String finalString = "";
        try {
            if (getShops().size() == 0) {
                return "There are no shops in the plaza yet.";
            }
        } catch (PlazaIsClosedException plazaIsClosed) {
            return plazaIsClosed.getMessage();
        }
        for (Shop shop : shops) {
            finalString += "Shop's name: " + shop.getName() + " || shop's owner: " + shop.getOwner() + "\n";
        }
        return finalString;
    }


    public void serializePlaza() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./src/main/java/codecool/plaza/data/plazaser.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in plazaser.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static PlazaImpl deserializePlaza() {
        try {
            FileInputStream fileIn = new FileInputStream("./src/main/java/codecool/plaza/data/plazaser.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            PlazaImpl plaza = (PlazaImpl) in.readObject();
            in.close();
            fileIn.close();
            return plaza;

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Plaza class not found");
            c.printStackTrace();
        }
        return null;
    }

}
