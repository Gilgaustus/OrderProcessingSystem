package order.processing.system;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionCntl 
{
    public ConnectionCntl()
    {
        ConnectionToMySql();
    }
    
    public static void connection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }


    public static Connection ConnectionToMySql()
    {    
        connection();
        String host = "jdbc:mysql://localhost:8889/OrderProcessingSystem"; 
        String Username = "root";
        String Password = "root";
        try {
            Connection connect = DriverManager.getConnection(host, Username, Password);
            System.out.println("Connection Successful");
            return connect;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Inventory database methods
    public static void GetInventoryData(ArrayList<Inventory> inputIL) throws SQLException
    {
        Connection conn = ConnectionToMySql();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Inventory");
        while (rs.next()) 
        {
            Inventory newItem = new Inventory();
            int inventoryID = rs.getInt("PK_InventoryID");
            String itemName = rs.getString("ItemName");
            String description = rs.getString("Description");
            double price = rs.getDouble("Price");
            int quantity = rs.getInt("Quantity");
            newItem.setID(inventoryID);
            newItem.setName(itemName);
            newItem.setDescription(description);
            newItem.setPrice(price);
            newItem.setQuantity(quantity);
            inputIL.add(newItem);
        }
        conn.close();
    }
}
/*
            inputIL.get(i).inventoryID = rs.getInt("PK_InventoryID");
            inputIL.get(i).itemName = rs.getString("ItemName");
            inputIL.get(i).description = rs.getString("Description");
            inputIL.get(i).price = rs.getDouble("Price");
            inputIL.get(i).quantity = rs.getInt("Quantity");
*/