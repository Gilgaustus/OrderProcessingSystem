/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.processing.system;

import java.util.ArrayList;

/**
 *
 * @author esm5175
 */
public class OrderListCntl extends Transaction
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
    
    public Order createOrder(CustomerListCntl inputCLC, InventoryListCntl inputILC, Cart inputCart, double inputPrice, String ISA, String BSA)    
    {
        Order O = new Order(inputCLC, inputILC);
        
        O.setBillingAddr(BSA);
        O.setShippingAddr(ISA);
        O.setShippingPrice(inputPrice);
        O.setCart(inputCart);
        
        return O;
    }
    
    public ArrayList<Order> getOrderList()
    {
        return OL.orderList;
    }
    
    public void addOrder(Order order)
    {
        this.getOrderList().add(order);
    }
    
    public void getOrder(int index)
    {
        this.getOrderList().get(index);
    }
    
    public void setOrder(int index, Order order)
    {
       this.getOrderList().set(index, order);
    }
    
    public void deleteOrder(Order order)
    {
        this.getOrderList().remove(order);
    }
    
    public void compileOdrList()
    {
      System.out.println("Order List");
      Order order1 = new Order(CLC, ILC);
      order1.setShippingPrice(1.00);
      order1.process();
       
      this.getOrderList().add(order1); 
      
      for(int i = 0; i< this.getOrderList().size(); i++)
        {
            System.out.println("ID: " + this.getOrderList().get(i).transactionID + " Total Price: " + this.getOrderList().get(i).totalPrice + " Shipping Price: " + this.getOrderList().get(i).shippingPrice + " Shipping Address: " + this.getOrderList().get(i).shippingAddress + " Billing Address: " + this.getOrderList().get(i).billingAddress + " Item" + this.getOrderList().get(i).InventoryItemOrder.ILC.getItemName(i));
        }
    }
    
    @Override
    void process()
    {
        transactionID++;
    }
    
}
