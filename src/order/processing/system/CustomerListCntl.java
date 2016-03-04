package order.processing.system;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerListCntl
{
    protected CustomerList theCustomerList = new CustomerList();
    int invID;
    private int customerID = 0;
    SecureRandom rn;
    protected InventoryListCntl ILC;
    
    public CustomerListCntl(InventoryListCntl inputILC)
    {
        ILC = inputILC;
        rn = new SecureRandom();
        invID = rn.nextInt(2);
    }
    
    public void addCustomer(Customer newCustomer)
    {
        this.getCustomerList().add(newCustomer);
    }
    
    public void deleteCustomer(int index)
    {
        this.getCustomerList().remove(index);
    }
    
    public void displayCartList()
    {
        for(int i = 0; i < this.getCustomerList().get(0).getCart().getCartList().size(); i++)
        {    
            System.out.println(i + "." + " ID: " + this.getCustomerList().get(0).getCart().getCartList().get(i).getID() + " Name: "+ this.getCustomerList().get(0).getCart().getCartList().get(i).getName() + " Desc: " + this.getCustomerList().get(0).getCart().getCartList().get(i).getDescription() + "  Price: " + this.getCustomerList().get(0).getCart().getCartList().get(i).getPrice());
        }
    }
    
    public void addToCustomerCart(int customerIndex, int itemID)
    {
        System.out.println(Thread.currentThread().getName() + " " + this.getCustomerList().get(customerIndex).getFirstName() + " adding Item " + ILC.getIL().get(itemID).getName());

        int index;
        int quantity = ILC.getIL().get(itemID).getQuantity();
        double subtotal = this.getCustomerCart(customerIndex).getSubtotal();
        if(quantity != 0)
        {
            this.getCustomerCart(customerIndex).getCartList().add(ILC.getIL().get(itemID));
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(ILC.getIL().get(itemID).equals(ILC.getIL().get(i)))
                {
                    quantity--;
                    index = i;
                   
                    ILC.setItemQuantity(index, quantity);
                    subtotal = subtotal + ILC.getItemPrice(index);
                    this.getCustomerCart(customerIndex).setSubtotal(subtotal);
                    System.out.println(Thread.currentThread().getName() + " " + this.getCustomerList().get(customerIndex).getFirstName() + " added Item " + ILC.getIL().get(itemID).getName());
                }
            }
        }
        else
            System.out.println("Item not available.");
    }
    
    public void removeFromCustomerCart(int customerIndex, int itemID)
    {
        System.out.println(Thread.currentThread().getName() + " " + this.getCustomerList().get(customerIndex).getFirstName() + " removing Item " + ILC.getIL().get(itemID).getName());
      
        int index;
        int quantity = ILC.getIL().get(itemID).getQuantity();
        boolean changeInCart = false;
        double subtotal = this.getCustomerCart(customerIndex).getSubtotal();
        if (this.getCustomerCart(customerIndex).getCartList().size() > 0)
        {
            for (int i = 0; i < this.getCustomerCart(customerIndex).getCartList().size(); i++)
            {
                if (itemID == this.getCustomerCart(customerIndex).getCartList().get(i).getID())
                {
                    index = this.getCustomerCart(customerIndex).getCartList().get(i).getID();
                    this.getCustomerCart(customerIndex).getCartList().remove(i);
                    changeInCart = true;
                    quantity++;
                    
                    ILC.setItemQuantity(index, quantity);
                    subtotal = subtotal - ILC.getItemPrice(index);
                    this.getCustomerCart(customerIndex).setSubtotal(subtotal);
                    System.out.println("Removed item from cart.");
                    break;
                }
            }
            if (!changeInCart) 
                System.out.println("No such item in cart.");
        } 
        else
            System.out.println("No items in cart.");
    }
   
    public void testCL()
    {
        char[] pass1 = new char[1];
        pass1[0] = 1;
 
        
        char[] CC = new char[3];
        CC[0] = 3;
        CC[1] = 4;
        CC[2] = 5;
     
        this.getCustomerList().add(this.createCustomer("Tom", "Jones", "tj@itsnotunusual.com", "Shipping Address", "Billing Address", pass1, CC));
        this.getCustomerList().add(this.createCustomer("Jake", "StateFarm", "EMAIL", "SA", "BA", pass1, CC));
        this.getCustomerList().add(this.createCustomer("Bob", "Smith", "EMAIL", "Shipping Address", "Billing Address", pass1, CC));
        this.getCustomerList().add(this.createCustomer("John", "Lennon", "EMAIL", "SA", "BA", pass1, CC));
        this.getCustomerList().add(this.createCustomer("Chelle", "Portal", "EMAIL", "SA", "BA", pass1, CC));
        
        for(int i = 0; i < this.getCustomerList().size(); i++)
        {
            this.getCustomerList().get(i).getCart().ILC = ILC;
        }
    }
    
    //getters
    
    public ArrayList<Customer> getCustomerList()
    {
        return theCustomerList.CustomerList;
    }
    
    public void getCustomer(int index)
    {
        this.getCustomerList().get(index);
    }
    
    public int getCustomerID(int index)
    {
        return this.getCustomerList().get(index).getCustomerID();
    }
    
    public String getCustomerFirstName(int index)
    {
        return this.getCustomerList().get(index).getFirstName();
    }
    
    public String getCustomerLastName(int index)
    {
        return this.getCustomerList().get(index).getLastName();
    }
    
    public String getCustomerEmail(int index)
    {
        return this.getCustomerList().get(index).getEmail();
    }
    
    public String getCustomerShipingAddress(int index)
    {
        return this.getCustomerList().get(index).getShippingAddress();
    }
    
    public String getCustomerBillingAddress(int index)
    {
        return this.getCustomerList().get(index).getBillingAddress();
    }
    
    public char[] getCustomerPassword(int index)
    {
        return this.getCustomerList().get(index).getPassword();
    }
    
    public char[] getCustomerCreditCard(int index)
    {
        return this.getCustomerList().get(index).getCreditCard();
    }
    
    public Cart getCustomerCart(int index)
    {
        return this.getCustomerList().get(index).getCart();
    }
   
    //setters
    public Customer createCustomer(String newFirstName, String newLastName, String newEmail, String newShippingAddress, String newBillingAddress, char[] newPassword, char[] newCreditCard)
    {
        Customer newCustomer = new Customer(ILC);
        
        newCustomer.setCustomerID(customerID);
        customerID++;
        newCustomer.setFirstName(newFirstName);
        newCustomer.setLastName(newLastName);
        newCustomer.setEmail(newEmail);
        newCustomer.setShippingAddress(newShippingAddress);
        newCustomer.setBillingAddress(newBillingAddress);
        newCustomer.setPassword(newPassword);
        newCustomer.setCreditCard(newCreditCard);
        
        return newCustomer;
    }
    
    public void setCustomerFirstName(int index, String newCustomerFirstName)
    {
        this.getCustomerList().get(index).setFirstName(newCustomerFirstName);
    }
    
    public void setCustomerLastName(int index, String newCustomerLastName)
    {
        this.getCustomerList().get(index).setLastName(newCustomerLastName);
    }
    
    public void setCustomerEmail(int index, String newCustomerEmail)
    {
        this.getCustomerList().get(index).setEmail(newCustomerEmail);
    }
    
    public void setCustomerShipingAddress(int index, String newCustomerShippingAddress)
    {
        this.getCustomerList().get(index).setShippingAddress(newCustomerShippingAddress);
    }
    
    public void setCustomerBillingAddress(int index, String newCustomerBillingAddress)
    {
        this.getCustomerList().get(index).setBillingAddress(newCustomerBillingAddress);
    }
    
    public void setCustomerPassword(int index, char[] newCustomerPassword)
    {
        this.getCustomerList().get(index).setPassword(newCustomerPassword);
    }
    
    public void setCustomerCreditCard(int index, char[] newCustomerCreditCard)
    {
        this.getCustomerList().get(index).setCreditCard(newCustomerCreditCard);
    }
}
