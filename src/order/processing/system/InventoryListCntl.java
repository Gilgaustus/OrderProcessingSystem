/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.processing.system;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryListCntl implements Runnable
{
    //so long as we implmeent the correct controls in inventory listcntl I see no reason to extend it to inventory.
    int ID = 0;
    InventoryList IL = new InventoryList();
    CustomerListCntl CLC;
    Scanner in = new Scanner(System.in);
    
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
    public  synchronized void setItemDescription(int index, String input)
    { try{
            Thread.sleep(4000);
        this.getIL().get(index).setDescription(input);
            }catch(InterruptedException e)
        {
            System.out.println(Thread.currentThread().getName() + "Interrupted");
        }
    }
    public synchronized  void setItemPrice(int index, double Price)
    { try{
            Thread.sleep(4000);
        this.getIL().get(index).setPrice(Price);
            }catch(InterruptedException e)
        {
            System.out.println(Thread.currentThread().getName() + "Interrupted");
        }
    }
    
    public  synchronized void setItemQuantity(int index, int quantity)
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
        this.getIL().add(this.createItem("Ball", "Sphere", 1.00, 3));
        this.getIL().add(this.createItem("Square", "Cube", 2.00, 2));
        this.getIL().add(this.createItem("Triangle", "Pyramid", 3.00, 1));
    }

    @Override
    
    //eventually, these should freeze the inventory item and then turn it over to the customer thread so it can put the item in there
    //probably will add conditions to make the inventory thread run differently, (eg 
    public void run() {
               
        
          System.out.println("Here is a list of our Inventory: ");
         // System.out.println("**************************************************************************************************************");
          this.displayInvList();
         // System.out.println("**************************************************************************************************************");
        
    
    }
    }

