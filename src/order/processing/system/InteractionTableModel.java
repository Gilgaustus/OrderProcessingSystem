package order.processing.system;

import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;

public class InteractionTableModel extends AbstractTableModel
{
    private static String[] columnNames = {"ID", "Name", "Description", "Price", "Quantity"};
    private InventoryListCntl ILC;
    private ArrayList<Inventory> inventoryTableData;
    
    public InteractionTableModel(InventoryListCntl inputILC)
    {
        ILC = inputILC;
        inventoryTableData = ILC.getIL();
    }
    
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    public int getRowCount() 
    {
        return inventoryTableData.size();
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
                objectToReturn = inventoryTableData.get(row).getID();
                break;
            case 1: 
                objectToReturn = inventoryTableData.get(row).getName();
                break;
            case 2: 
                objectToReturn = inventoryTableData.get(row).getDescription();
                break;
            case 3: 
                objectToReturn = inventoryTableData.get(row).getPrice();
                break;
            case 4: 
                objectToReturn = inventoryTableData.get(row).getQuantity();
                break;
        }
        return objectToReturn;
    }
    
    public Inventory getItem(int itemID)
    {
        return inventoryTableData.get(itemID);
    }
}