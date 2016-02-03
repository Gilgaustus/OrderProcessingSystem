package order.processing.system;

import java.util.ArrayList;

public class Order extends Transaction
{

    CustomerListCntl parentCLC;
    InventoryListCntl parentILC;
    //for now cust is just hard-coded to 0
    Cart InventoryItemOrder;
    double shippingPrice;
    double totalPrice = 0;
    int orderID;
    int orderCustomerID;
    String shippingAddress;
    String billingAddress;
    
    
    public Order(CustomerListCntl theCLC, InventoryListCntl theILC)
    {
        
        parentCLC = theCLC;
        parentILC = theILC;
        InventoryItemOrder = parentCLC.getCustomerCart(0);
        orderID = transactionID;
        shippingAddress = parentCLC.getCustomerShipingAddress(0);
        billingAddress = parentCLC.getCustomerBillingAddress(0);
         
    }
      
    
    public void setShippingPrice(double input)
    {
        shippingPrice = input;
        this.process();
    }
    
    public double getShippingPrice()
    {
        return shippingPrice;
    }
    
   public void setBillingAddr(String input)
    {
        billingAddress = input;
       
    }
    
    public String getBillingAddress()
    {
        return billingAddress;
    }
    
    public void setShippingAddr(String input)
    {
        shippingAddress = input;
       
    }
    
    public String getShippingAddress()
    {
        return shippingAddress;
    }
    
    public void setCart(Cart inputCart)
    {
        InventoryItemOrder = inputCart;
    }
    
    public Cart getCart()
    {
        return InventoryItemOrder;
    }
    
    public void displayCartList()
    {
        for(int i = 0; i < InventoryItemOrder.getCartList().size(); i++)
        {    
            System.out.println(i + "." + " ID: " + InventoryItemOrder.getCartList().get(i).getID() + " Name: "+ InventoryItemOrder.getCartList().get(i).getName() + " Desc: " + InventoryItemOrder.getCartList().get(i).getDescription() + "  Price: " + InventoryItemOrder.getCartList().get(i).getPrice());
        }
    }
    
    @Override
    void process() 
    {
        totalPrice = totalPrice + InventoryItemOrder.subtotal;
        totalPrice = totalPrice + shippingPrice;
        
    }


}
