package order.processing.system;

import java.security.SecureRandom;

public class ThreadMaker extends Thread
{
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    OrderListCntl OLC;
    TransferListCntl TLC;
    
    ThreadMaker(CustomerListCntl theCLC, InventoryListCntl theILC, OrderListCntl theOLC, TransferListCntl theTLC)
    {
        CLC = theCLC;
        ILC = theILC;
        OLC = theOLC;
        TLC = theTLC;
    }
    
    @Override
    public void run()
    {
        
    }
}
