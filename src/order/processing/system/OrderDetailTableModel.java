package order.processing.system;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class OrderDetailTableModel extends AbstractTableModel
{
    private static String[] columnNames = {"ID", "Name", "Description", "Price"};
    private OrderListCntl OLC;
    private ArrayList<Inventory> orderDetailTableData = new ArrayList<>();
    
    public OrderDetailTableModel(ArrayList<Inventory> orderCart)
    {
        orderDetailTableData = orderCart;
    }
    
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    public int getRowCount() 
    {
        return orderDetailTableData.size();
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
                objectToReturn = orderDetailTableData.get(row).getID();
                break;
            case 1: 
                objectToReturn = orderDetailTableData.get(row).getName();
                break;
            case 2: 
                objectToReturn = orderDetailTableData.get(row).getDescription();
                break;
            case 3: 
                objectToReturn = orderDetailTableData.get(row).getPrice();
                break;
        }
        return objectToReturn;
    }
}
