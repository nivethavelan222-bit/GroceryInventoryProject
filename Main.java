import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        while (true) {
            System.out.println("\n=== Grocery Store Inventory ===");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Remove Item");
            System.out.println("4. View All Items");
            System.out.println("5. Search Item");
            System.out.println("6. Calculate Total Inventory Value");
            System.out.println("7. Sort Items by Name");
            System.out.println("8. Sort Items by Price");
            System.out.println("9. Exit");

            try {
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        System.out.print("Enter price: ");
                        double price = sc.nextDouble();
                        manager.addItem(name, qty, price);
                        break;

                    case 2:
                        System.out.print("Enter item name to update: ");
                        name = sc.nextLine();
                        System.out.print("Enter new quantity: ");
                        qty = sc.nextInt();
                        System.out.print("Enter new price: ");
                        price = sc.nextDouble();
                        manager.updateItem(name, qty, price);
                        break;

                    case 3:
                        System.out.print("Enter item name to remove: ");
                        name = sc.nextLine();
                        manager.removeItem(name);
                        break;

                    case 4:
                        manager.displayItems();
                        break;

                    case 5:
                        System.out.print("Enter item name to search: ");
                        name = sc.nextLine();
                        manager.searchItem(name);
                        break;

                    case 6:
                        manager.calculateTotalValue();
                        break;

                    case 7:
                        manager.sortItemsByName();
                        break;

                    case 8:
                        manager.sortItemsByPrice();
                        break;

                    case 9:
                        System.out.println("👋 Exiting... Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠️ Invalid choice! Please enter 1–9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Invalid input! Please enter a number (1–9).");
                sc.nextLine(); // clear wrong input
            }
        }
    }
}
