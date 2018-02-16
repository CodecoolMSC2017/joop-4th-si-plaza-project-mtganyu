package codecool.plaza.cmdprog;

import codecool.plaza.api.*;
import codecool.plaza.Exceptions.*;

import java.text.SimpleDateFormat;
import java.util.*;

class CmdProgram {

    private List<Product> cart = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private final String[] plazaMenu = new String[]{"List all shops",
            "Add a new shop",
            "Remove existing shop",
            "Enter a shop by name",
            "Open the plaza",
            "Close the plaza",
            "Check if the plaza is open or not",
            "Save plaza"};
    private final String[] shopMenu = new String[]{"List all available products",
            "Find products by name",
            "Display shop's owner",
            "Open the shop",
            "Close the shop",
            "Add new product to the shop",
            "Add existing products to the shop",
            "Buy a product by barcode",
            "Buy multiple products to cart",
            "Display price by barcode",
            "Display quantity by barcode",
            "Cashout",
            "Save shop"};
    private String plazaName;
    private ShopImpl shop;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Product product;

    public void Program(String[] args) {

    }

    public void run() {
        System.out.println("[1] Create plaza\n[2] Load plaza\"\n[0] Exit");
        boolean running = true;
        while (running) {

            System.out.print("\nSelect option: ");
            String choice = scan.nextLine();
            try {
                int intChoice = Integer.parseInt(choice);
                switch (intChoice) {
                    case 1:
                        System.out.print("Enter your plaza's name: ");
                        plazaName = scan.nextLine();
                        System.out.print("Enter the owner's name: ");
                        String ownerName = scan.nextLine();
                        PlazaImpl plaza = new PlazaImpl(plazaName, ownerName);
                        System.out.println("Your plaza has been successfully created.");
                        handlePlazaMenu(plaza);
                        running = false;
                        break;
                    case 2:
                        plaza = PlazaImpl.deserializePlaza();
                        shop = ShopImpl.deserializeShop();
                        handlePlazaMenu(plaza);
                    case 0:
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid menupoint!");
                }
            } catch (NumberFormatException ne) {
                ne.getMessage();
                System.out.println("Please enter the number of the menupoint!");
            }
        }
    }


    private void handlePlazaMenu(PlazaImpl plaza) {
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the " + plaza.getName() + "!");
            System.out.println("====================================");
            for (int i = 0; i < plazaMenu.length; i++) {
                System.out.println("[" + (i + 1) + "] " + plazaMenu[i]);
            }
            System.out.println("[0] Exit");
            System.out.print("\nSelect option: ");
            String choice = scan.nextLine();
            try {
                int intChoice = Integer.parseInt(choice);
                switch (intChoice) {
                    case 1:
                        System.out.println("\nShops in the plaza:");
                        System.out.println("===================");
                        System.out.println(plaza.toString());
                        break;
                    case 2:
                        System.out.print("Enter shop's name: ");
                        String shopName = scan.nextLine();
                        System.out.print("Enter shop's owner: ");
                        String ownerName = scan.nextLine();
                        shop = new ShopImpl(shopName, ownerName);
                        try {
                            plaza.addShop(shop);
                            System.out.println("shop created!");
                        } catch (PlazaIsClosedException pisClosed) {
                            System.out.println(pisClosed.getMessage());
                        } catch (ShopAlreadyExistsException sAlreadyExists) {
                            System.out.println(sAlreadyExists.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter shop's name to remove: ");
                        shopName = scan.nextLine();
                        System.out.print("Enter the shop owner's name: ");
                        ownerName = scan.nextLine();
                        shop = new ShopImpl(shopName, ownerName);
                        try {
                            plaza.removeShop(shop);
                            System.out.println("shop removed!");
                        } catch (NoSuchShopException noSuchShop) {
                            System.out.println(noSuchShop.getMessage());
                        } catch (PlazaIsClosedException plazaIsClosed) {
                            System.out.println(plazaIsClosed.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter shop's name: ");
                        shopName = scan.nextLine();
                        try {

                            handleShopMenu((ShopImpl) plaza.findShopByName(shopName));
                        } catch (NoSuchShopException noSuchShop) {
                            System.out.println(noSuchShop.getMessage());
                        } catch (PlazaIsClosedException plazaIsClosed) {
                            System.out.println(plazaIsClosed.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            plaza.open();
                            System.out.println("plaza opened!");
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        break;
                    case 6:
                        try {
                            plaza.close();
                            System.out.println("plaza closed!");
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        break;
                    case 7:
                        System.out.print("The plaza is currently ");
                        System.out.println(plaza.isOpen() ? "open." : "closed.");
                        break;
                    case 8:
                        plaza.serializePlaza();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid menupoint!");


                }
            } catch (NumberFormatException ne) {
                ne.getMessage();
                System.out.println("Please enter the number of the menupoint!");
            }
        }
    }

    private void handleShopMenu(ShopImpl shop) {
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the " + shop.getName() + " shop!");
            System.out.println("====================================");
            for (int i = 0; i < shopMenu.length; i++) {
                System.out.println("[" + (i + 1) + "] " + shopMenu[i]);
            }
            System.out.println("[0] Return to the " + plazaName);
            System.out.print("\nSelect option: ");
            String choice = scan.nextLine();
            try {
                int intChoice = Integer.parseInt(choice);
                switch (intChoice) {
                    case 1:
                        System.out.println(shop.toString());
                        break;
                    case 2:
                        System.out.println("Enter the item's name:");
                        String item = scan.nextLine();
                        System.out.println(shop.findByName(item).get(0).toString());
                        break;
                    case 3:
                        System.out.println("Shops owner: " + shop.getOwner());
                        break;
                    case 4:
                        shop.open();
                        System.out.println("shop opened!");
                        break;
                    case 5:
                        shop.close();
                        System.out.println("shop closed!");
                        break;
                    case 6:
                        if (!shop.isOpen()) {
                            Exception notOpen = new ShopIsClosedException();
                            System.out.println(notOpen.getMessage());
                            break;
                        }
                        Product product = null;
                        boolean creating = true;
                        while (creating) {
                            String[] headerFood = new String[]{"Please enter the name of the food: ", "Please enter a manufacturer name: ", "Please enter a barcode: ", "Please enter a calorie number: ", "Please enter a date(yyyy-mm-dd): "};
                            String[] headerClothing = new String[]{"Please enter the name of the Clothes: ", "Please enter a manufacturer name: ", "Please enter a barcode: ", "Please enter a material: ", "Please enter a type: "};
                            System.out.print("What kind of product would you like to add (Food or Clothing) : ");
                            String foodOrClothing = scan.nextLine().toUpperCase();
                            if (foodOrClothing.equals("FOOD")) {
                                String[] attributes = new String[headerFood.length];
                                for (int i = 0; i < headerFood.length; i++) {
                                    System.out.println(headerFood[i]);
                                    attributes[i] = scan.nextLine().toUpperCase();
                                }
                                String tempName = attributes[0];
                                String tempManufacturer = attributes[1];
                                try {
                                    long tempBar = Long.parseLong(attributes[2]);
                                    int tempCalorie = Integer.parseInt(attributes[3]);
                                    Date tempDate = new Date();
                                    tempDate = sdf.parse(attributes[4]);
                                    product = new FoodProduct(tempBar, tempName, tempManufacturer, tempCalorie, tempDate);
                                    //  product.serializeProd();
                                    creating = false;
                                } catch (NumberFormatException nf) {
                                    System.out.println(nf.getMessage());
                                }
                            } else if (foodOrClothing.equals("CLOTHING")) {
                                String[] attributes = new String[headerClothing.length];
                                for (int i = 0; i < headerClothing.length; i++) {
                                    System.out.println(headerClothing[i]);
                                    attributes[i] = scan.nextLine().toUpperCase();
                                }
                                String tempName = attributes[0];
                                String tempManufacturer = attributes[1];
                                String tempMaterial = attributes[3];
                                String tempType = attributes[4];
                                try {
                                    long tempBar = Long.parseLong(attributes[2]);
                                    product = new ClothingProduct(tempBar, tempName, tempManufacturer, tempMaterial, tempType);
                                    //  product.serializeProd();
                                    creating = false;
                                } catch (NumberFormatException nf) {
                                    System.out.println(nf.getMessage());
                                }
                            } else {
                                System.out.println("Please enter a valid option!");
                            }
                        }
                        System.out.println("Please enter a price: ");
                        String tempPrice = scan.nextLine();
                        System.out.println("Please enter a quantity: ");
                        String tempQuantity = scan.nextLine();
                        try {
                            float tP = Float.parseFloat(tempPrice);
                            int tQ = Integer.parseInt(tempQuantity);
                            shop.addNewProduct(product, tQ, tP);
                        } catch (ProductAlreadyExistsException pa) {
                            System.out.println(pa.getMessage());
                        }

                        break;
                    case 7:
                        System.out.println(shop.toString());
                        System.out.println("Please give me an existing items barcode: ");
                        String tempBarC = scan.nextLine();
                        System.out.println("Please give me a quantity: ");
                        String tempQ = scan.nextLine();
                        try {
                            long tb = Long.parseLong(tempBarC);
                            int tQ = Integer.parseInt(tempQ);
                            shop.addProduct(tb, tQ);
                        } catch (NoSuchProductException noSuchP) {
                            System.out.println(noSuchP.getMessage());
                        } catch (ShopIsClosedException shpClosed) {
                            System.out.println(shpClosed.getMessage());
                        }
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        System.out.println("Available products: ");
                        System.out.println(shop.toString());
                        System.out.println("Please enter a barcode: ");
                        String tempBc3 = scan.nextLine();
                        try {
                            long barCodeForPrice = Long.parseLong(tempBc3);
                            System.out.println(shop.getPrice(barCodeForPrice));
                        } catch (NumberFormatException nf) {
                            System.out.println(nf.getMessage());
                        }
                        break;
                    case 11:
                        System.out.println("Available products: ");
                        System.out.println(shop.toString());
                        System.out.println("Please enter a barcode: ");
                        String tempBc4 = scan.nextLine();
                        try {
                            long barCodeForPrice = Long.parseLong(tempBc4);
                            System.out.println("The quantity of this item in stock is: ");
                            System.out.println(shop.getQuantity(barCodeForPrice));
                        } catch (NumberFormatException nf) {
                            System.out.println(nf.getMessage());
                        }
                        break;
                    case 12:
                        break;
                    case 13:
                        shop.serializeShop();
                        break;
                    case 0:
                        running = false;

                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}