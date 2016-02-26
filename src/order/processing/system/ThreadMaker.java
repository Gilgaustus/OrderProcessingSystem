package order.processing.system;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMaker extends Thread
{
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    OrderListCntl OLC;
    TransferListCntl TLC;
    int customerID;
    int invID;
    SecureRandom rn = new SecureRandom();
    
    ThreadMaker(CustomerListCntl theCLC, InventoryListCntl theILC, OrderListCntl theOLC, TransferListCntl theTLC, int theCustomerID, int theInvID)
    {
        CLC = theCLC;
        ILC = theILC;
        OLC = theOLC;
        TLC = theTLC;
        customerID = theCustomerID;
        invID = theInvID;
    }
    
    public void run()
    {
        
        synchronized(ILC.getIL().get(invID))
        {
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(invID == ILC.getItemID(i))
                {
                    CLC.addToCustomerCart(customerID, invID);
                    System.out.println("Inventory: ");
                    ILC.displayInvList();
                }
            }
            Thread.yield();
        }  
            
        invID = rn.nextInt(2);
            
        for(int i = 0; i < ILC.getIL().size(); i++)
        {   
        if(invID == ILC.getItemID(i))
            {
                CLC.addToCustomerCart(customerID, invID);
                System.out.println("Inventory: ");
                ILC.displayInvList();
            }
        }
        Thread.yield();
            
        invID = rn.nextInt(2);
        for(int i = 0; i < ILC.getIL().size(); i++)
        {   
            if(invID == ILC.getItemID(i))
            {
                CLC.addToCustomerCart(customerID, invID);
                ILC.displayInvList();
            }
        }
        Thread.yield();
            
        invID = rn.nextInt(2);
        CLC.removeFromCustomerCart(customerID, invID);
        System.out.println("Inventory: ");
        ILC.displayInvList();
        Thread.yield();
            
        invID = rn.nextInt(2);
        CLC.removeFromCustomerCart(customerID, invID);
        System.out.println("Inventory: ");
        ILC.displayInvList();
        Thread.yield();  
        
        System.out.println(this.getName() + " " + "creating order");
        OLC.addOrder(OLC.createOrder(CLC, ILC, CLC.getCustomerCart(customerID), invID, "Addressy", "addressy"));
        OLC.showCustomerOrder(customerID);
    }
}
