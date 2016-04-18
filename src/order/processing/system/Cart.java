package order.processing.system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cart 
{
    InventoryListCntl ILC;
    ArrayList<Inventory> cartContents = new ArrayList<>();
    double subtotal = 0;
    
    public void addToCart (Inventory inv)
    {       
        cartContents.add(inv);
    }
    
    public void removeFromCart (int cartItemID) throws SQLException
    {
        ILC.setItemQuantity(cartContents.get(cartItemID).getID(), (ILC.getItemQuantity(cartContents.get(cartItemID).getID()))-1);
        subtotal = subtotal - ILC.getItemPrice(cartContents.get(cartItemID).getID());
        cartContents.remove(cartItemID);
    }
    
    public void printContents()
    {
        for(int i = 0; i < cartContents.size(); i++)
        {
            System.out.println(" Name: " + cartContents.get(i).getName() +  " Desc: " + cartContents.get(i).getDescription() + " Price: " + cartContents.get(i).getPrice() + " Quant: " + cartContents.get(i).getQuantity());
        }
    }
    
    public void setSubtotal(double newSubtotal)
    {
        subtotal = newSubtotal;
    }
    
    public ArrayList<Inventory> getCartList ()
    {
        return cartContents;
    }
    
    public double getSubtotal()
    {
        return subtotal;
    }
    
}