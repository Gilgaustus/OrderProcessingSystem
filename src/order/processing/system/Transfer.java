package order.processing.system;

public class Transfer
{
    Inventory invToReturn;
    Inventory invToGet;
    
    int transferID;
    int transferCustomerID;
    int referenceOrderID;
    
    public Transfer(Inventory newInvToReturn, Inventory newInvToGet)
    {
        invToReturn = newInvToReturn;
        invToGet = newInvToGet;
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
}