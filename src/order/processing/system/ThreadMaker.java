package order.processing.system;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMaker extends Thread 
{
    //AuthenticationCntl authCntl;
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    OrderListCntl OLC;
    TransferListCntl TLC;
    int customerID;
    SecureRandom rn = new SecureRandom();
    
    ThreadMaker(CustomerListCntl theCLC, InventoryListCntl theILC, OrderListCntl theOLC, TransferListCntl theTLC, int inputCustomerID)
    {
        CLC = theCLC;
        ILC = theILC;
        OLC = theOLC;
        TLC = theTLC;
        customerID = inputCustomerID;
    }
    
    public void run() 
    {
       ILC.showInventoryUI(customerID);
        
        /*
        synchronized(ILC.getIL().get(invID))
        {
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(invID == ILC.getItemID(i))
                {
                    try {
                        CLC.addToCustomerCart(customerID, invID);
                    } catch (SQLException ex) {
                        Logger.getLogger(ThreadMaker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Inventory: ");
                    ILC.displayInvList();
                }
            }
        } 
        Thread.yield();
        
        
        invID = rn.nextInt(2);
        synchronized(ILC.getIL().get(invID))
        {
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
            if(invID == ILC.getItemID(i))
                {
                try {
                    CLC.addToCustomerCart(customerID, invID);
                } catch (SQLException ex) {
                    Logger.getLogger(ThreadMaker.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("Inventory: ");
                    ILC.displayInvList();
                }
            }
        }
        Thread.yield();
          
        
        
        invID = rn.nextInt(2);
        synchronized(ILC.getIL().get(invID))
        {
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(invID == ILC.getItemID(i))
                {
                    try {
                        CLC.addToCustomerCart(customerID, invID);
                    } catch (SQLException ex) {
                        Logger.getLogger(ThreadMaker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ILC.displayInvList();
                }
            }
        }
        Thread.yield();
        
        
        
        invID = rn.nextInt(2);
        synchronized(ILC.getIL().get(invID))
        {
            try {
                CLC.removeFromCustomerCart(customerID, invID);
            } catch (SQLException ex) {
                Logger.getLogger(ThreadMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Inventory: ");
            ILC.displayInvList();
        }
        Thread.yield();
        
            
        invID = rn.nextInt(2);
        synchronized(ILC.getIL().get(invID))
        {
            try {
                CLC.removeFromCustomerCart(customerID, invID);
            } catch (SQLException ex) {
                Logger.getLogger(ThreadMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Inventory: ");
            ILC.displayInvList();
        }
        Thread.yield();  
        
        ILC.displayInvList();
        System.out.println(this.getName() + " " + "creating order");
        OLC.createOrder(CLC, ILC, customerID, CLC.getCustomerCart(customerID), 1.00, CLC.getCustomerShipingAddress(customerID), CLC.getCustomerBillingAddress(customerID));
        OLC.showCustomerOrder(customerID);
        System.out.println("End order.");
                */
    }
}
