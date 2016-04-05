package order.processing.system;

public class OrderProcessingSystem {
  
    public static void  main(String[] args)  {
        
        InteractionCntl interactionCntl = new InteractionCntl();
        synchronized(interactionCntl)
        {
            try
            {
                interactionCntl.start();
                interactionCntl.wait();
            } catch(InterruptedException e) {}
        }
    }
}
