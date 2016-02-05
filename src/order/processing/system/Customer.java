package order.processing.system;

import java.util.ArrayList;

public class Customer 
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
    
    public void addToCart (Inventory Item)
    {
        theCart.addToCart(Item); //aah
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
    
}
