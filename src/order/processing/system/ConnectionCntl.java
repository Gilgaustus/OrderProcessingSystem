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
    
    public static void ChangeInventoryData(int inputInventoryID, int quantity) throws SQLException
    {
        Connection conn = ConnectionToMySql();
        conn.setReadOnly(false);
        //Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        //ResultSet rs = stmt.executeQuery("SELECT * FROM Inventory WHERE PK_InventoryID = " + inputInventoryID);
        PreparedStatement pstmt = conn.prepareStatement("UPDATE Inventory SET Quantity = ? WHERE PK_InventoryID = " + inputInventoryID);
        
        pstmt.setInt(1, quantity);
        
        pstmt.executeUpdate();
        //rs.updateInt(4, quantity);
        //rs.updateRow();
        
        conn.close();
    }
    
    //Customer database methods
    public static void GetCustomerData(InventoryListCntl inputILC, ArrayList<Customer> inputCL) throws SQLException
    {
        Connection conn = ConnectionToMySql();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
        while (rs.next()) 
        {
            Customer newCustomer = new Customer(inputILC);
            int customerID = rs.getInt("PK_CustomerID");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String email = rs.getString("Email");
            String shippingAddress = rs.getString("ShippingAddress");
            String billingAddress = rs.getString("BillingAddress");
            char[] pass1 = new char[4];
            pass1 = rs.getCharacterStream("Password").toString().toCharArray();
            char[] CC = new char[3];
            CC = rs.getCharacterStream("CreditCard").toString().toCharArray();
            newCustomer.setCustomerID(customerID);
            newCustomer.setFirstName(firstName);
            newCustomer.setLastName(lastName);
            newCustomer.setShippingAddress(shippingAddress);
            newCustomer.setBillingAddress(billingAddress);
            newCustomer.setPassword(pass1);
            newCustomer.setCreditCard(CC);
            inputCL.add(newCustomer);
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