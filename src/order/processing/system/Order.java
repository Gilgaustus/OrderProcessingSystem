package order.processing.system;

public class Order
{
    CustomerListCntl CLC;
    InventoryListCntl ILC;
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
        CLC = theCLC;
        ILC = theILC;
        InventoryItemOrder = new Cart(ILC);
    }
    
    public void setOrderID(int newOrderID)
    {
        orderID = newOrderID;
    }
    
    public void setCustomerID(int newCustomerID)
    {
        orderCustomerID = newCustomerID;
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
   
   public void setTotalPrice(double newTotalPrice)
   {
       totalPrice = newTotalPrice;
   }
    
   public int getOrderID()
   {
       return orderID;
   }
   
   public int getCustomerID()
   {
       return orderCustomerID;
   }
   
   public double getTotalPrice()
   {
       return totalPrice;
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
    
    void process() 
    {
        totalPrice = totalPrice + InventoryItemOrder.subtotal;
        totalPrice = totalPrice + shippingPrice;
    }
}
