package order.processing.system;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderProcessingSystem {
  
    public static void  main(String[] args)  {
      //Comment this line in to get our interface. 
      //  ILCntl.welcomeProtocol();
        
       //I used CustomerListCntl and then InteractionCntl as a bucket for the ILC this way both customers can access the same list
      InteractionCntl interactionCntl = new InteractionCntl();
      
      synchronized(interactionCntl)
      {
          try
          {
              interactionCntl.start();
              interactionCntl.wait();
          } catch(InterruptedException e) {}
      }
      
      /*Old INCORRECT THREADING, I'm keeping it around in case it becomes useful.  
       ILCntl.getILC().initInvList();
       ILCntl.getILC().displayInvList();
       
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
       
    */
       
       }
    
    
}
