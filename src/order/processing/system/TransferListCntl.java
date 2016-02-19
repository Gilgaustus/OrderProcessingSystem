package order.processing.system;

import java.util.ArrayList;

public class TransferListCntl extends Transaction
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
    
    public void createTransfer(int orderReference, int returningItemIndex, int newItemIndex)    
    {
        Transfer newTransfer = new Transfer(OLC.getOrderList().get(orderReference).getCart().getCartList().get(returningItemIndex), ILC.getIL().get(newItemIndex));
        newTransfer.setTransferID(transferID);
        newTransfer.setTransferCustomerID(0);
        newTransfer.setReferenceOrderID(OLC.getOrderList().get(orderReference).getOrderID());
        this.process(OLC.getOrderList().get(orderReference).getCart().getCartList().get(returningItemIndex).getID(), newItemIndex);
        
        this.addTransfer(newTransfer);
    }
    
    void process(int returningItem, int newItem)
    {
        int quantity = ILC.getIL().get(newItem).getQuantity()-1;
        ILC.setItemQuantity(newItem, quantity);
        quantity = ILC.getIL().get(returningItem).getQuantity()+1;
        ILC.setItemQuantity(newItem, quantity);
        transferID++;
    }
    
    public ArrayList<Transfer> getTransferList()
    {
        return TL.TransferList;
    }
    
    public void addTransfer(Transfer transfer)
    {
        this.getTransferList().add(transfer);
    }
    
    public void deleteTransfer(int index)
    {
        this.getTransferList().remove(index);
    }
    
    public void getTransfer(int index)
    {
        this.getTransferList().get(index);
    }
    
    public void run() 
    {
        
    }
}
