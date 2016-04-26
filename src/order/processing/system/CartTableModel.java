package order.processing.system;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CartTableModel extends AbstractTableModel
{
    private static String[] columnNames = {"ID", "Name", "Description", "Price"};
    private CustomerListCntl CLC;
    private ArrayList<Inventory> cartTableData;
    
    public CartTableModel (CustomerListCntl inputCLC, int customerID)
    {
        CLC = inputCLC;
        cartTableData = CLC.getCustomerCart(customerID).getCartList();
    }
    
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    public int getRowCount() 
    {
        return cartTableData.size();
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
                objectToReturn = cartTableData.get(row).getID();
                break;
            case 1: 
                objectToReturn = cartTableData.get(row).getName();
                break;
            case 2: 
                objectToReturn = cartTableData.get(row).getDescription();
                break;
            case 3: 
                objectToReturn = cartTableData.get(row).getPrice();
                break;
        }
        return objectToReturn;
    }
    
    public Inventory getItem(int itemPosition)
    {
        return cartTableData.get(itemPosition);
    }
    
}
