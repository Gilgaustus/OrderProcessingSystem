/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.processing.system;

import java.util.ArrayList;

public class InventoryListCntl 
{
    int ID = 0;
    InventoryList IL = new InventoryList();
    
    public void addItem(Inventory newItem)
    {
        this.getIL().add(newItem);
    }
    
    public ArrayList<Inventory> getIL()
    {
       return IL.InventoryList;
    }
    
    public void deleteItem(Inventory inv)
    {
        this.getIL().remove(inv);
    }
    
    //getters
     public Inventory getItem(int index)
    {  
        return this.getIL().get(index);
    }
     
    public int getItemID(int index)
    {
        return this.getIL().get(index).getID();
    }
    
    public String getItemName(int index)
    {
        int q = this.getIL().size();
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

    public void setItemName(int index, String Name)
    {
        this.getIL().get(index).setName(Name);
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
        this.getIL().add(this.createItem("Ball", "Sphere", 1.00, 3));
        this.getIL().add(this.createItem("Square", "Cube", 2.00, 2));
        this.getIL().add(this.createItem("Triangle", "Pyramid", 3.00, 1));
    }
}
