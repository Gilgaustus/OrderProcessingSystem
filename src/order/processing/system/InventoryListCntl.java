package order.processing.system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryListCntl extends Transaction
{
    //so long as we implmeent the correct controls in inventory listcntl I see no reason to extend it to inventory.
    int ID = 0;
    
    InventoryList IL = new InventoryList();
    ConnectionCntl CNC;
    Scanner in = new Scanner(System.in);
    
    public InventoryListCntl(ConnectionCntl inputCNC)
    {
        CNC = inputCNC;
    }
   
    public void addItem(Inventory newItem)
    {
        this.getIL().add(newItem);
    }
    
    public ArrayList<Inventory> getIL()
    {
       return IL.InventoryList;
    }
    
    public void deleteItem(int index)
    {
    
        try{
            String name = this.getItemName(index);
         // System.out.println("****************************************************************");
            System.out.println(Thread.currentThread().getName()+ " deleting item " + name);
        this.getIL().remove(index);
     
        System.out.println(Thread.currentThread().getName() + "deleted Item " + name);
       // System.out.println("****************************************************************"); 
        Thread.sleep(4000);
        }catch(InterruptedException e)
        {
            System.out.println("No item to delete");
        }
            
    }
    
    //getters
     public Inventory getItem(int index)
    {  
        return this.getIL().get(index);
    }
     
    public int getItemID(int index)
    {
        return this.getIL().get(index).getID();
    }
    
    public String getItemName(int index)
    {
        return this.getIL().get(index).itemName;
    } 
    
    public String getItemDescription(int index)
    {
        return this.getIL().get(index).description;
    }
    
    public double getItemPrice(int index)
    {
        return this.getIL().get(index).price;
    }
    
    public int getItemQuantity(int index)
    {
        return this.getIL().get(index).quantity;
    }
    
    
    
  
    //setters
    //I don't think the setters really need synch as they are not used by the customer at any point, however I've done some as a proof of concept
    public synchronized void setItemName(int index, String Name)
    {
        this.getIL().get(index).setName(Name);
    }
    
    public void setItemDescription(int index, String input)
    { 
        this.getIL().get(index).setDescription(input);
    }
    
    public void setItemPrice(int index, double Price)
    { 
        this.getIL().get(index).setPrice(Price);
        
    }
    
    public void setItemQuantity(int index, int quantity) throws SQLException
    { 
        this.getIL().get(index).setQuantity(quantity);
        CNC.ChangeInventoryData(index, quantity);
    }
    
    public void displayInvList()
    {
        for(int i = 0; i < this.getIL().size(); i++)
        {    
            System.out.println("ID: " + this.getIL().get(i).getID() + " Name: "+ this.getIL().get(i).getName() + " Desc: " + this.getIL().get(i).getDescription() + "  Price: " + this.getIL().get(i).getPrice() + " Quantity: " + this.getIL().get(i).getQuantity());
        }
    }
    
    public void initInvList() throws SQLException
    {
        CNC.GetInventoryData(this.getIL());
        this.displayInvList();
    }
}
