package order.processing.system;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransferListCntl 
{
    ConnectionCntl CNC;
    CustomerListCntl CLC;
    InventoryListCntl ILC;
    OrderListCntl OLC;
    int transferID = 0;
    TransferList TL = new TransferList();

    
    public TransferListCntl(ConnectionCntl inputCNC, CustomerListCntl inputCLC, InventoryListCntl inputILC, OrderListCntl inputOLC)
    {
        CNC = inputCNC;
        CLC = inputCLC;
        ILC = inputILC;
        OLC = inputOLC;
    }
    
    public void Return(int orderReference, int OrderItemIndex) throws SQLException, ClassNotFoundException
    {
        System.out.println(Thread.currentThread() + " is executing a return");
        
        CNC.ChangeInventoryData(OLC.getOrderList().get(orderReference).getCart().getCartList().get(OrderItemIndex).getID(), ILC.getItemQuantity(OLC.getOrderList().get(orderReference).getCart().getCartList().get(OrderItemIndex).getID())+1);
        // OLC.getOrderList().get(orderReference).getCart().getCartList().remove(OrderItemIndex);
    }
    
    public void createTransfer(int orderReference, int OrderItemIndex, int InvItemIndex) throws SQLException, ClassNotFoundException    
    {   
        if(!(OLC.getOrderList().size() <= InvItemIndex))  
        {
            System.out.println(Thread.currentThread().getName() + " is executing Transfer");
        
            //I decrement the index to interface with the list more easily because the display is always+1 of the list
            //InvItemIndex = InvItemIndex-1;
        
            Inventory returnItem;
            System.out.println(orderReference);
            System.out.println(OLC.getOrder(orderReference).getCart().getCartList().size());
            returnItem = OLC.getOrder(orderReference).getCart().getCartList().get(OrderItemIndex);
        
        
        
            //deletes order
            //OLC.getOrder(orderReference).getCart().getCartList().remove(OrderItemIndex);
         
            //adds item to the inv list
            ILC.getIL().get(OLC.getOrder(orderReference).getCart().getCartList().get(OrderItemIndex).getID()).setQuantity(ILC.getIL().get(OLC.getOrder(orderReference).getCart().getCartList().get(OrderItemIndex).getID()).getQuantity()+1);
        
            //reduces quantity of item in inv list
            Inventory newItem;
            newItem = ILC.getIL().get(InvItemIndex);
            ILC.getIL().get(InvItemIndex).setQuantity(ILC.getIL().get(InvItemIndex).getQuantity()-1);
        
            Transfer t = new Transfer(transferID, returnItem, newItem);
            this.process();
            this.addTransfer(t);
            CNC.AddTransferData(t, this);
            System.out.println("Transfer complete.");
        }
    }
    
    public void performTransfer(int customerID, int desiredItem) throws SQLException
    {
        OrderUI orderUI = new OrderUI(this, OLC, customerID, desiredItem);
        orderUI.setVisible(true);
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
    
    public void process()
    {
        transferID++;
    }
}
