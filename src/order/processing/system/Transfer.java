package order.processing.system;

public class Transfer extends Transaction
{
    Inventory invToReturn;
    Inventory invToGet;
    
    int transferID;
    int transferCustomerID;
    int referenceOrderID;
    
    InventoryListCntl ILC;
    CustomerListCntl CLC;
    
    
    public Transfer(Inventory newInvToReturn, Inventory newInvToGet, InventoryListCntl inputILC, CustomerListCntl inputCLC)
    {
        invToReturn = newInvToReturn;
        invToGet = newInvToGet;
        //ILC = inputILC;
        //CLC = inputCLC;
    }
    
    public void setTransferID(int newTransferID)
    {
        transferID = newTransferID;
    }
    
    public void setTransferCustomerID(int newCustomerID)
    {
        transferCustomerID = newCustomerID;
    }
    
    public void setReferenceOrderID(int theOrderID)
    {
        referenceOrderID = theOrderID;
    }
    
    public Inventory getItemReturned()
    {
        return invToReturn;
    }
    
    public Inventory getItemToGet()
    {
        return invToGet;
    }
    
    public int getTransferID()
    {
        return transferID;
    }
    
    public int getTransferCustomerID()
    {
        return transferCustomerID;
    }
    
    public int getReferencedOrderID()
    {
        return referenceOrderID;
    }

    
    public void run() {
       
       
    }
}