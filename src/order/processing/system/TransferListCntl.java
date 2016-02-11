package order.processing.system;

public class TransferListCntl 
{
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    OrderListCntl OLC;
    int transferID = 0;
    
    TransferList TL = new TransferList();
    
    public TransferListCntl(CustomerListCntl inputCLC, InventoryListCntl inputILC, OrderListCntl inputOLC)
    {
        CLC = inputCLC;
        ILC = inputILC;
        OLC = inputOLC;
    }
}
