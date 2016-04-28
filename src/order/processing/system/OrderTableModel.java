package order.processing.system;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class OrderTableModel extends AbstractTableModel
{
    private static String[] columnNames = {"ID", "Billing Address", "Shipping Address", "Cost"};
    private ArrayList<Order> orderTableData;
    
    public OrderTableModel(ArrayList<Order> inputCustomerOrderData) throws SQLException
    {
        orderTableData = inputCustomerOrderData;
    }
    
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    public int getRowCount() 
    {
        return orderTableData.size();
    }

    public String getColumnName(int col) 
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) 
    {
        Object objectToReturn = new Object();
        switch(col){
            case 0: 
                objectToReturn = orderTableData.get(row).getOrderID();
                break;
            case 1: 
                objectToReturn = orderTableData.get(row).getBillingAddress();
                break;
            case 2: 
                objectToReturn = orderTableData.get(row).getShippingAddress();
                break;
            case 3: 
                objectToReturn = orderTableData.get(row).getTotalPrice();
                break;
        }
        return objectToReturn;
    }
    
    public Order getOrder(int orderID)
    {
        return orderTableData.get(orderID);
    }
}
