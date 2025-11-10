import java.util.*;
import java.sql.*;

public class InventoryManager {
    private HashMap<String, GroceryItem> inventory = new HashMap<>();

    // Add item
    public void addItem(String name, int quantity, double price) {
        inventory.put(name, new GroceryItem(name, quantity, price));
        System.out.println("✅ Item added (Local): " + name);

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO grocery_items (name, quantity, price) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, quantity);
                ps.setDouble(3, price);
                ps.executeUpdate();
                System.out.println("✅ Item saved in MySQL too!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to insert into MySQL!");
            e.printStackTrace();
        }
    }

    // Update quantity and price
    public void updateItem(String name, int quantity, double price) {
        if (inventory.containsKey(name)) {
            GroceryItem item = inventory.get(name);
            item.setQuantity(quantity);
            item.setPrice(price);
            System.out.println("🔄 Item updated locally: " + name);

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "UPDATE grocery_items SET quantity=?, price=? WHERE name=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setDouble(2, price);
                ps.setString(3, name);
                int rows = ps.executeUpdate();
                if (rows > 0) System.out.println("🔄 Item updated in MySQL too!");
                else System.out.println("⚠️ Item not found in MySQL!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("❌ Item not found locally!");
        }
    }

    // Delete item
    public void removeItem(String name) {
        if (inventory.remove(name) != null) {
            System.out.println("🗑️ Item removed locally: " + name);

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "DELETE FROM grocery_items WHERE name=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.executeUpdate();
                System.out.println("🗑️ Item removed from MySQL too!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("❌ Item not found locally!");
        }
    }

    // Display all items (from MySQL)
    public void displayItems() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM grocery_items";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n--- Grocery Inventory (From MySQL) ---");
            boolean empty = true;
            while (rs.next()) {
                empty = false;
                System.out.println(
                    rs.getString("name") + " - Qty: " +
                    rs.getInt("quantity") + " - Price: ₹" +
                    rs.getDouble("price")
                );
            }
            if (empty) System.out.println("📦 No items in database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search item (MySQL)
    public void searchItem(String name) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM grocery_items WHERE name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("🔍 Found in MySQL: " +
                    rs.getString("name") + " - Qty: " +
                    rs.getInt("quantity") + " - Price: ₹" +
                    rs.getDouble("price"));
            } else {
                System.out.println("❌ Item not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Calculate total inventory value (from MySQL)
    public void calculateTotalValue() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT SUM(quantity * price) AS total FROM grocery_items";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                double total = rs.getDouble("total");
                System.out.println("💰 Total Inventory Value: ₹" + total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Sort items by name (from MySQL)
    public void sortItemsByName() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM grocery_items ORDER BY name ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("\n📋 Items Sorted by Name:");
            while (rs.next()) {
                System.out.println(
                    rs.getString("name") + " - Qty: " +
                    rs.getInt("quantity") + " - Price: ₹" +
                    rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Sort items by price (from MySQL)
    public void sortItemsByPrice() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM grocery_items ORDER BY price ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("\n💲 Items Sorted by Price:");
            while (rs.next()) {
                System.out.println(
                    rs.getString("name") + " - Qty: " +
                    rs.getInt("quantity") + " - Price: ₹" +
                    rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
