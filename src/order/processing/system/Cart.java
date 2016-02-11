/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.processing.system;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cart 
{
    InventoryListCntl ILC;
    ArrayList<Inventory> cartContents = new ArrayList<>();
    double subtotal = 0;
    
    public Cart (InventoryListCntl inputILC)
    {
        ILC = inputILC;
    }
    
    //these should really be in a control 
    public synchronized void addToCart (Inventory inv)
    {       
           //System.out.println("****************************************************************");
           System.out.println(Thread.currentThread().getName() + " adding Item " + inv.getName());
          // Thread.currentThread().wait();
        int index;
        int quantity = inv.getQuantity();
        if(quantity != 0)
        {
            cartContents.add(inv);
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(inv.equals(ILC.getIL().get(i)))
                {
                    quantity = inv.getQuantity()-1;
                    index = i;
                   
                    ILC.setItemQuantity(index, quantity);
                    subtotal = subtotal + ILC.getItemPrice(inv.getID());
                
                }
            }
        }
         
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(Thread.currentThread().getName() + " added item " + inv.getName());
        //System.out.println("****************************************************************");

    }
    
    public void removeFromCart (int cartItemID)
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
    
    public ArrayList<Inventory> getCartList ()
    {
        return cartContents;
    }
    
}