package order.processing.system;

public class Transfer extends Transaction
{

    
    CustomerListCntl parentCLC;
    InventoryListCntl parentILC;
    OrderListCntl parentOLC;
    //for now cust is just hard-coded to 0
    Order theOrder;
    double totalPrice = 0;
    int transferID = 0;
    int transferCustomerID;
    
    public Transfer(CustomerListCntl theCLC, InventoryListCntl theILC, OrderListCntl theOLC)
    {
        parentCLC = theCLC;
        parentILC = theILC;
        parentOLC = theOLC;
    }
    
    public void setTransfer()
    {
        
    }
    
    
    
    public void displayCartList()
    {
        for(int i = 0; i < InventoryItemOrder.getCartList().size(); i++)
        {    
            System.out.println(i + "." + " ID: " + InventoryItemOrder.getCartList().get(i).getID() + " Name: "+ InventoryItemOrder.getCartList().get(i).getName() + " Desc: " + InventoryItemOrder.getCartList().get(i).getDescription() + "  Price: " + InventoryItemOrder.getCartList().get(i).getPrice());
        }
    }
    
    @Override
    void process() 
    {
        
    }


}