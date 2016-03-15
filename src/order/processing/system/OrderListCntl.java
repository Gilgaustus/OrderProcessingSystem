package order.processing.system;

import java.util.ArrayList;

public class OrderListCntl 
{
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    int orderID = 0;
    
    OrderList OL = new OrderList();
    
    public OrderListCntl(CustomerListCntl inputCLC, InventoryListCntl inputILC)
    {
        CLC = inputCLC;
        ILC = inputILC;
    }
    
    public synchronized void createOrder(CustomerListCntl inputCLC, InventoryListCntl inputILC, int customerID, Cart inputCart, double inputPrice, String ISA, String BSA)    
    {
        Order O = new Order(inputCLC, inputILC);
        
        O.setOrderID(orderID);
        O.setCustomerID(customerID);
        O.setBillingAddr(BSA);
        O.setShippingAddr(ISA);
        O.setShippingPrice(inputPrice);
        O.setCart(inputCart);
        this.process();
        
        this.getOrderList().add(O);
        System.out.println("Added order");
    }
    
    public ArrayList<Order> getOrderList()
    {
        return OL.orderList;
    }
    
    public void getOrder(int index)
    {
        this.getOrderList().get(index);
    }
    
    public void showCustomerOrder(int customerID)
    {
        for (int i = 0; i < this.getOrderList().size(); i++) 
        {
            if (customerID == this.getOrderList().get(i).orderCustomerID) 
            {
                System.out.println(Thread.currentThread().getName() + " " + CLC.getCustomerFirstName(customerID) + " Order ID: " + this.getOrderList().get(i).orderID);
                
                //i loops through order list 
                for (int j = 0; j < this.getOrderList().get(i).getCart().cartContents.size(); j++)
                {
                    System.out.println("Cart Position: " + j + " ID: " + this.getOrderList().get(i).getCart().getCartList().get(j).getID() + " Name: " + this.getOrderList().get(i).InventoryItemOrder.getCartList().get(j).getName() + " Desc: " + this.getOrderList().get(i).InventoryItemOrder.getCartList().get(j).getDescription() + " Price: " + this.getOrderList().get(i).InventoryItemOrder.getCartList().get(j).getPrice() + "\n");
                } 
            }
        }
    }
    
    public void setOrder(int index, Order order)
    {
        System.out.println("Index: "+ index);
        if(this.getOrderList().size() != 0)
        {
           this.getOrderList().set(index, order);
        }
    }
    
    public void deleteOrder(Order order)
    {
        this.getOrderList().remove(order);
    }
    
    void process()
    {
        orderID++;
    }
}
