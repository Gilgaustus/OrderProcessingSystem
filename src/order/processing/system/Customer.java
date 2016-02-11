package order.processing.system;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer extends Thread
{
    protected int customerID;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String shippingAddress;
    protected String billingAddress;
    protected char[] password;
    protected char[] creditCard;
    protected Cart theCart;
    protected ArrayList<Order> ordersPlaced; //array of Order IDs of orders customer has placed
    protected int ordersSize = 0;
    protected InventoryListCntl ILC;
    
    public Customer(InventoryListCntl inputILC)
    {
        ILC = inputILC;
        theCart = new Cart(ILC);
    }
    
    public synchronized void addToCart (Inventory Item)
    {
        
    try{
        Thread.sleep(4000);
    }catch(InterruptedException e4)
    {
           theCart.addToCart(Item);
    }
        
    }
    
    public void removeFromCart (int cartItemID)
    {
        theCart.removeFromCart(cartItemID);
    }
    
    public void addOrder (Order newOrder)
    {
        ordersPlaced.add(newOrder);
    }
    
    //getters
    public int getCustomerID()
    {
        return customerID;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getShippingAddress()
    {
        return shippingAddress;
    }
    
    public String getBillingAddress()
    {
        return billingAddress;
    }
    
    public char[] getPassword()
    {
        return password;
    }
    
    public char[] getCreditCard()
    {
        return creditCard;
    }
    
    public Cart getCart()
    {
        return theCart;
    }
    
    public ArrayList<Order> getOrdersPlaced()
    {
        return ordersPlaced;
    }
    
    //setters
    public void setCustomerID (int newCustomerID)
    {
        customerID = newCustomerID;
    }
    
    public void setFirstName (String newFirstName)
    {
        firstName = newFirstName;
    }
    
    public void setLastName (String newLastName)
    {
        lastName = newLastName;
    }
    
    public void setEmail (String newEmail)
    {
        email = newEmail;
    }
    
    public void setShippingAddress (String newShippingAddress)
    {
        shippingAddress = newShippingAddress;
    }
    
    public void setBillingAddress (String newBillingAddress)
    {
        billingAddress = newBillingAddress;
    }
    
    public void setPassword (char[] newPassword)
    {
        password = newPassword;
    }
    
    public void setCreditCard (char[] newCreditCard)
    {
        creditCard = newCreditCard;
    }

    @Override
    public void run() {
   
//both threads will select an item with 1 stock, 1 will always win out.
    try{
        
              this.theCart.addToCart(ILC.getItem(2));
        //this.theCart.removeFromCart(0);
        //System.out.println(this.getName() + " removed Item 0");
       }catch(IndexOutOfBoundsException e)
       {
           System.out.println(this.getName() + " found no item at 2");
           
           //giving thread 2 an item so it can participate
          // System.out.println(this.getName() + " adding item 1");
           //try{
           //this.theCart.addToCart(this.ILC.getIL().get(1));
           //}catch(IndexOutOfBoundsException e33)
           //{
           //System.out.println(this.getName() + "added Item 1");
           //}
           

       }
    //item names will change across carts as all carts share items with same name ect.  what should be unique to the cart is the quantity of ite,s
    try{   
       //There should only be 1 Square item in inventory list now
           this.ILC.deleteItem(1);         
           this.theCart.addToCart(this.ILC.getItem(1));

         
    }catch(IndexOutOfBoundsException e2)
    {
        System.out.println(this.getName() + "found nothing to add");
    }
    }

    
}
