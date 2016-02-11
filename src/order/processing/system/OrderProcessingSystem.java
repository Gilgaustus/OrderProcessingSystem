/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.processing.system;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilberto
 */
public class OrderProcessingSystem {

    /**
     * @param args the command line arguments
     */
  
    public static void  main(String[] args)  {
        // TODO code application logic here
       InteractionCntl ILCntl = new InteractionCntl(); 
       
       ILCntl.getILC().initInvList();
       ILCntl.getILC().displayInvList();
       
        //Customer cust1 = new Customer(ILCntl.getILC());
        //Customer cust2 = new Customer(ILCntl.getILC());
        
       // cust1.setFirstName("Ladies");
        //cust2.setFirstName("Gentlman");
      
       
       
       Thread Thread1 = new Customer(ILCntl.getILC());
       Thread Thread2 = new Customer(ILCntl.getILC());
     
       
        System.out.println("The Order Processing System Presents...");
        System.out.println("****************************************");
        System.out.println("*********THREAD***HANDLING**************");
        System.out.println("****************************************");
        
        
       Thread1.start();
        try {
            Thread1.join(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(OrderProcessingSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
       Thread2.start();
       Thread.yield();
       
        //comment this in to print the order processing system
       //ILCntl.welcomeProtocol();
      
       }
    
    
}
