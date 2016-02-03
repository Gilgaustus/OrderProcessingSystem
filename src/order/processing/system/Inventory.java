package order.processing.system;

public class Inventory 
{
    protected int InventoryID;
    protected String itemName;
    protected String description;
    double Price;
    protected int Quantity = 0;
    
    //getters
    public int getID()
    {
        return InventoryID;
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
        return Price;
    }
    
    public int getQuantity()
    {
        return Quantity;
    }
    
    public void setID(int ID)
    {
        InventoryID = ID;
    }
    
    public void setName(String Name)
    {
        itemName = Name;
    }
    
    public void setDescription(String Description)
    {
        description = Description;
    }
    
    public void setPrice(double price)
    {
        Price = price;
    }
    
    public void setQuantity(int quantity)
    {
        Quantity = quantity;
    }
}
