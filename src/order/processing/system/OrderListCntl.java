package order.processing.system;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderListCntl 
{
    ConnectionCntl CNC;
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    int orderID = 0;
    
    OrderList OL = new OrderList();
    
    public OrderListCntl(ConnectionCntl inputCNC, CustomerListCntl inputCLC, InventoryListCntl inputILC)
    {
        CNC = inputCNC;
        CLC = inputCLC;
        ILC = inputILC;
    }
    
    public synchronized void createOrder(CustomerListCntl inputCLC, InventoryListCntl inputILC, int customerID, Cart inputCart, double inputPrice, String ISA, String BSA) throws ClassNotFoundException, SQLException    
    {
        
        System.out.println(Thread.currentThread().getName() + "created Order");
        Order O = new Order(inputCLC, inputILC);
        
        O.setOrderID(orderID);
        O.setCustomerID(customerID);
        O.setBillingAddr(BSA);
        O.setShippingAddr(ISA);
        O.setShippingPrice(inputPrice);
        O.setCart(inputCart);
        O.getCart().printContents();
        this.process();
        
        this.getOrderList().add(O);
        //buy return exchange
        if(O.getCart().getCartList().size() != 0)
        {
            CNC.AddOrderData(O);
            System.out.println("Added order");
        }
        else if(O.getCart().getCartList().size() == 0)
        {
            System.out.println(Thread.currentThread() + "'s cart is empty");
        }
        
    }
    
    public ArrayList<Order> getOrderList()
    {
        return OL.orderList;
    }
   
    
    public Order getOrder(int index)
    {
        return this.getOrderList().get(index);
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
        else if(this.getOrderList().size() == 0)
        {
            System.out.println(Thread.currentThread().getName() + " Doesn't have anything in the cart.");
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
