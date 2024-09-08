import java.util.ArrayList;
import java.util.Scanner;

// Class for individual menu items
class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

// Class for restaurant orders
class Order {
    private ArrayList<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void displayOrder() {
        if (items.isEmpty()) {
            System.out.println("No items in the order.");
        } else {
            System.out.println("Order summary:");
            for (MenuItem item : items) {
                System.out.println(item);
            }
            System.out.println("Total: $" + calculateTotal());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

public class RestaurantManagementSystem {
    private static ArrayList<MenuItem> menu = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMenu(); // Initialize the menu
        Order currentOrder = new Order(); // New order for a customer

        int choice;
        do {
            System.out.println("\nRestaurant Management System");
            System.out.println("1. View Menu");
            System.out.println("2. Add Item to Order");
            System.out.println("3. View Order");
            System.out.println("4. Calculate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayMenu();
                case 2 -> addItemToOrder(currentOrder);
                case 3 -> currentOrder.displayOrder();
                case 4 -> System.out.println("Total Bill: $" + currentOrder.calculateTotal());
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    private static void initializeMenu() {
        menu.add(new MenuItem("Burger", 5.99));
        menu.add(new MenuItem("Pizza", 8.99));
        menu.add(new MenuItem("Pasta", 7.50));
        menu.add(new MenuItem("Soda", 1.99));
        menu.add(new MenuItem("Coffee", 2.50));
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    private static void addItemToOrder(Order order) {
        displayMenu();
        System.out.print("Enter the item number to add to the order: ");
        int itemNumber = scanner.nextInt();
        if (itemNumber > 0 && itemNumber <= menu.size()) {
            MenuItem item = menu.get(itemNumber - 1);
            order.addItem(item);
            System.out.println(item.getName() + " added to the order.");
        } else {
            System.out.println("Invalid item number.");
        }
    }
}