package order.processing.system;

import java.sql.SQLException;

public class OrderProcessingSystem {
  
    public static void  main(String[] args) throws SQLException  {
        
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
