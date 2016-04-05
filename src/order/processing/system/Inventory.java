package order.processing.system;

public class Inventory
{
    protected int inventoryID;
    protected String itemName;
    protected String description;
    double price;
    protected int quantity = 0;
    
    //getters
    public int getID()
    {
        return inventoryID;
    }
    
    public String getName()
    {
        
        return itemName;
    }
            
    public String getDescription()
    {
        return description;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setID(int ID)
    {
        inventoryID = ID;
    }
    
    public synchronized void setName(String Name)
    {
     
        itemName = Name;
     
    
        }
    
    public void setDescription(String Description)
    {
        description = Description;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
