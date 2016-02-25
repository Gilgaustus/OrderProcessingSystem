package order.processing.system;

import java.security.SecureRandom;

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
            ILC.displayInvList();
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(invID == ILC.getItemID(i))
                CLC.addToCustomerCart(customerID, invID);
            }
            this.yield();
            
            invID = rn.nextInt(2);
            
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(invID == ILC.getItemID(i))
                CLC.addToCustomerCart(customerID, invID);
            }
            this.yield();
            
            invID = rn.nextInt(2);
            
            for(int i = 0; i < ILC.getIL().size(); i++)
            {   
                if(invID == ILC.getItemID(i))
                CLC.addToCustomerCart(customerID, invID);
            }
            
            this.yield();
            
            invID = rn.nextInt(2);
            CLC.removeFromCustomerCart(customerID, invID);
            this.yield();
            
            invID = rn.nextInt(2);
            CLC.removeFromCustomerCart(customerID, invID);
            this.yield();
        }
    }
}
