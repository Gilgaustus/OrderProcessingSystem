package order.processing.system;

import static java.lang.System.in;
import java.util.Scanner;

public class InteractionCntl extends Thread
{    
    InventoryListCntl ILC = new InventoryListCntl();
    CustomerListCntl CLC = new CustomerListCntl(ILC);
    InventoryListCntl ILC2 = new InventoryListCntl(CLC);
    OrderListCntl OLC = new OrderListCntl(CLC, ILC2);
    TransferListCntl TLC = new TransferListCntl(CLC, ILC2, OLC);
    Scanner in = new Scanner(System.in);   

    public void initCustomer()
    {
        CLC.testCL();
    }
    public void welcomeProtocol()
    {
        
        this.initCustomer();
        
        //Initializes Inventory
        ILC2.initInvList();
         
        System.out.println("Welcome to the Order Proc System!");
        System.out.println("You are currently logged in a customer: " + CLC.getCustomerFirstName(0) + " " + CLC.getCustomerLastName(0));
  
        //Displays Selection Grid
        this.displaySelection(this.mainMenu());
 
        System.out.print("Here are the contents of your Cart: ");
        CLC.getCustomerCart(0).printContents();
        System.out.println("The cart will now process your order: ");
        OLC.compileOdrList();
        System.out.println("Have a nice day!");
        System.exit(0);   
    }
    
    public int mainMenu()
    {
        //we want scanner to clear after clearing menu so each is declared in a method
        System.out.println("Main Menu");
       // System.out.println("**************************************************************************************************");
        System.out.println("Select the number in the bracket () to navigate to its respective option");
        System.out.println(" (1) View Inventory ");
        System.out.println(" (2) View Cart ");
        System.out.println(" (3) Checkout ");
        System.out.println(" (4) Transfer ");
     //   System.out.println("**************************************************************************************************");
        System.out.println("Enter Number:  ");
        int selection = in.nextInt();
        return selection;
    }
    
    public void displaySelection(int selection)
    {
        //we want scanner to clear after clearing menu so each is declared in a method
        in = new Scanner(System.in);
        
        if(selection == 1)
        {
          System.out.println("Here is a list of our Inventory: ");
          System.out.println("**************************************************************************************************************");
          ILC2.displayInvList();
          System.out.println("**************************************************************************************************************");
      
          System.out.println("Please Enter the ID of the Inventory Item You'd like to add to your cart: ");
          
          int invId = in.nextInt();
          //clears scanner
          in = new Scanner (System.in);
            
          for(int i = 0; i < ILC2.getIL().size(); i++)
          {   
              if(invId == ILC2.getItemID(i))
                  
              CLC.getCustomerCart(0).addToCart(ILC2.getItem(i));
          }
         
          
          System.out.println("Continue Shopping? (Y/N)"); 
          String option = in.nextLine();
            
          if(option.equalsIgnoreCase("Y"))
          {
              this.displaySelection(1);
          }
            
          if(option.equalsIgnoreCase("N"))
          {
              this.displaySelection(this.mainMenu());
          }
            
          if(!(option.equalsIgnoreCase("Y") && option.equalsIgnoreCase("N")))
          {
              System.out.println("Invalid input");
              this.displaySelection(1);
          }
            
            
        }
        
        if(selection == 2)
        {
            int decision;
            System.out.println("Here is a list of your cart: ");
            //System.out.println("**************************************************************************************************************");
            CLC.displayCartList();
          //  System.out.println("**************************************************************************************************************");
          
            //decide if you want to remove an item, increase quantity, or go back to the menu
            System.out.println("Select the number in the bracket () to activate its respective option");
            System.out.println(" (1) Increase quantity ");
            System.out.println(" (2) Remove from cart ");
            System.out.println(" (3) Go back ");
            decision = in.nextInt();
            
            if (decision == 1)
            {
                System.out.println("Select the item you want to increase from the cart list above: ");
                int addItem = in.nextInt();
                if (addItem >= 0 && addItem <= CLC.getCustomerCart(0).getCartList().size())
                    CLC.getCustomerCart(0).addToCart(ILC2.getItem(CLC.getCustomerList().get(0).getCart().getCartList().get(addItem).getID()));
                else
                {
                    System.out.println("Invalid input");
                    this.displaySelection(2);
                }
            }
            
            if (decision == 2)
            {
                System.out.println("Select the item you want to increase from the cart list above: ");
                int removeItem = in.nextInt();
                if (removeItem >= 0 && removeItem <= CLC.getCustomerCart(0).getCartList().size())
                    CLC.getCustomerCart(0).removeFromCart(removeItem);
                else
                {
                    System.out.println("Invalid input");
                    this.displaySelection(2);
                }
            }
            
            if (decision == 3)
            {
                this.displaySelection(this.mainMenu());
            }
            
            this.displaySelection(2);
        }
        
        if(selection == 3)
        {
            double shippingPrice = 1.00;
            int decision;
            System.out.println("Here is your current order");
            System.out.println("*****************************************");
            
            
            //may be obselete after threads
            CLC.displayCartList();
            System.out.println(" Shipping Address: " + CLC.getCustomerShipingAddress(0));
            System.out.println(" Billing Address: " + CLC.getCustomerBillingAddress(0));
            System.out.println(" Subtotal: " + CLC.getCustomerCart(0).subtotal);
            System.out.println(" Shipping Price: " + shippingPrice);
            System.out.println("\nAny changes before ordering? ");
            System.out.println(" (1) Edit cart ");
            System.out.println(" (2) Change shipping address ");
            System.out.println(" (3) Change billing address ");
            System.out.println(" (4) Proceed  ");
            decision = in.nextInt();
            
            if (decision == 1) 
            {
                this.displaySelection(2);
            }
            
            if (decision == 2) 
            {
                System.out.println("What address would you like this shipped to? ");
                in = new Scanner (System.in);
                String newShippingAddress = in.nextLine();
                CLC.getCustomerList().get(0).shippingAddress = newShippingAddress;
                this.displaySelection(selection);
            }
            
            if (decision == 3) 
            {
                System.out.println("What address would you like this billed to? ");
                in = new Scanner (System.in);
                String newBillingAddress = in.nextLine();
                CLC.getCustomerList().get(0).billingAddress = newBillingAddress;
                this.displaySelection(selection);
            }
            
            if (decision == 4)
            {
                OLC.getOrderList().add(OLC.createOrder(CLC, ILC2, CLC.getCustomerCart(0), shippingPrice, CLC.getCustomerShipingAddress(0), CLC.getCustomerShipingAddress(0)));
                OLC.process();
                CLC.getCustomerList().get(0).getCart().cartContents.clear();
                System.out.println(OLC.getOrderList().get(0).transactionID);
                System.out.println("Order placed!");
                this.displaySelection(this.mainMenu());
            }
        }
        
        if (selection == 4)
        {
            OLC.showCustomerOrder(0);
            System.out.println("From which order do you want to transfer an item from?");
            in = new Scanner (System.in);
            int orderNumber = in.nextInt();
            
            System.out.println("Which item do you want to return? (Choose the position of the item within the order, not the ID)");
            in = new Scanner (System.in);
            int itemToReturn = in.nextInt();
            
            System.out.println("Now choose which item would you like in exchange.");
            System.out.println("Here is a list of our Inventory: ");
            System.out.println("**************************************************************************************************************");
            ILC2.displayInvList();
            System.out.println("**************************************************************************************************************");
            in = new Scanner (System.in);
            int exchangeItem = in.nextInt();
            
            TLC.createTransfer(orderNumber, itemToReturn, exchangeItem);
            System.out.println("Transfer placed!");
            this.displaySelection(this.mainMenu());
        }
    
    }

    public InventoryListCntl getILC()
    {
        return ILC2;
    }
    
     
    
    
}
