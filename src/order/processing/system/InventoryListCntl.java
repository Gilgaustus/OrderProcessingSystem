package order.processing.system;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryListCntl extends Transaction
{
    //so long as we implmeent the correct controls in inventory listcntl I see no reason to extend it to inventory.
    int ID = 0;
    InventoryList IL = new InventoryList();
    CustomerListCntl CLC;
    Scanner in = new Scanner(System.in);
    ArrayList<Inventory> invList;
    ArrayList<Inventory> inputList;
    
    public InventoryListCntl()
    {
       
    }
    
    public InventoryListCntl(CustomerListCntl inputCLC)
    {
        CLC = inputCLC;
    }
   
   
    public synchronized void addItem(Inventory newItem)
    {try{
        Thread.sleep(4000);
    }catch(InterruptedException e)
    {}
        this.getIL().add(newItem);
        
    }
    
    public ArrayList<Inventory> getIL()
    {
       return IL.InventoryList;
    }
    
    public synchronized void deleteItem(int index)
    {
    
        try{
            String name = this.getItemName(index);
         // System.out.println("****************************************************************");
            System.out.println(Thread.currentThread().getName()+ " deleting item " + name);
        this.getIL().remove(index);
     
        System.out.println(Thread.currentThread().getName() + "deleted Item " + name);
       // System.out.println("****************************************************************"); 
        Thread.sleep(4000);
        }catch(InterruptedException e)
        {
            System.out.println("No item to delete");
        }
            
    }
    
    //getters
     public Inventory getItem(int index)
    {  
        //This one doesn't sleep as otherwise, synch problems occur and item isn't added to cart so other items can get at it
            //Thread.sleep(4000);
        return this.getIL().get(index);
    }
     
    public synchronized int getItemID(int index)
    {
        
        try
        {
            //not sure what number means
            Thread.sleep(4000);
        }catch(InterruptedException e)
        {
        }
        
        return this.getIL().get(index).getID();
    }
    //don't know if these "need" synchronized, if no item is there the protection exists.
    public String getItemName(int index)
    {
        return this.getIL().get(index).itemName;
    } 
    public String getItemDescription(int index)
    {
        return this.getIL().get(index).description;
    }
    public double getItemPrice(int index)
    {
        return this.getIL().get(index).Price;
    }
    public int getItemQuantity(int index)
    {
        return this.getIL().get(index).Quantity;
    }
    
  
    //setters
    
    public Inventory createItem(String Name, String Desc, Double Price, int quantity)
    {
        Inventory newItem = new Inventory();
        
        newItem.setID(ID);
        ID++;
        
        newItem.setName(Name);
        newItem.setDescription(Desc);
        newItem.setPrice(Price);
        newItem.setQuantity(quantity);
        
        return newItem;
    }

    //I don't think the setters really need synch as they are not used by the customer at any point, however I've done some as a proof of concept
    public synchronized void setItemName(int index, String Name)
    {
        try{
            
            this.getIL().get(index).setName(Name);
            System.out.println(Thread.currentThread().getName() + " reset Item " + this.getIL().get(index) + "n me to " + Name);
        Thread.sleep(4000);
        }catch(InterruptedException e)
        {
            System.out.println(Thread.currentThread().getName() + "Interrupted");
        }
    }
    public void setItemDescription(int index, String input)
    { 
        this.getIL().get(index).setDescription(input);
    }
    
    public void setItemPrice(int index, double Price)
    { 
        this.getIL().get(index).setPrice(Price);
        
    }
    
    public void setItemQuantity(int index, int quantity)
    { 
        this.getIL().get(index).setQuantity(quantity);
    }
    
    public void displayInvList()
    {
        for(int i = 0; i < this.getIL().size(); i++)
        {    
            System.out.println("ID: " + this.getIL().get(i).getID() + " Name: "+ this.getIL().get(i).getName() + " Desc: " + this.getIL().get(i).getDescription() + "  Price: " + this.getIL().get(i).getPrice() + " Quantity: " + this.getIL().get(i).getQuantity());
        }
    }
    
    public void initInvList()
    { 
        this.getIL().add(this.createItem("Ball", "Sphere", 1.00, 5));
        this.getIL().add(this.createItem("Square", "Cube", 2.00, 5));
        this.getIL().add(this.createItem("Triangle", "Pyramid", 3.00, 5));
    }

    @Override
    public void run() {
         
//both threads will select an item with 1 stock, 1 will always win out.
        
        System.out.println(this.getName() + " Printing Inventory..."); 
        System.out.println("Here is a list of our Inventory: ");
          System.out.println("**************************************************************************************************************");
          this.displayInvList();
          System.out.println("**************************************************************************************************************");
      
          System.out.println("Please Enter the ID of the Inventory Item You'd like to add to your cart: ");
      synchronized(this)
      {
          int invId = in.nextInt();
       
           //clears scanner
          in = new Scanner (System.in);
          for(int i = 0; i < this.getIL().size(); i++)
          {   
              if(invId == this.getItemID(i))
                  
              CLC.getCustomerCart(0).addToCart(this.getItem(i));
          }
      }
          System.out.println("Continue Shopping? (Y/N)"); 
          String option = in.nextLine();
            
          if(option.equalsIgnoreCase("Y"))
          {
              this.run();
          }
            
          if(option.equalsIgnoreCase("N"))
          {
              System.out.println("Goodbye");
          }
            
          if(!(option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")))
          {
              System.out.println("Invalid input");
              this.run();
          }
            
            
        }
    }
