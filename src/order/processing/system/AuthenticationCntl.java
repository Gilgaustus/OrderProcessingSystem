package order.processing.system;

import java.util.ArrayList;

public class AuthenticationCntl 
{
    private int loginAttempts = 0;
    CustomerListCntl theCLC;
    
    public AuthenticationCntl(CustomerListCntl inputCLC)
    {
        this.showLoginUI();
        theCLC = inputCLC;
    }
    
    public void showLoginUI()
    {
	LoginUI loggy = new LoginUI(this);
        loggy.setVisible(true); 
    }
    
    public boolean authenticate(String newUsername, char[] newPassword)
    {
        loginAttempts++;
        boolean isAuthenticated = false;
            
        System.out.println(newUsername);
        System.out.println(newPassword);
            
        for(int i = 0; i < theCLC.getCustomerList().size(); i++)
        {
            if(newUsername.equals(theCLC.getCustomerList().get(i).getEmail()) && this.checkPassword(newPassword, theCLC.getCustomerList())){
                isAuthenticated = true;
                break;
            }
        }
        System.out.println(isAuthenticated);
        
        return isAuthenticated;
    }
    
    public boolean checkPassword(char[] thePassword, ArrayList<Customer> theCustomerList)
    {
        boolean isTrue = false;
        String passwordAsString = String.valueOf(thePassword);
        String currentCustomerPassword;
            
        for(int i = 0; i < theCustomerList.size(); i++)
        {
            currentCustomerPassword = String.valueOf(theCustomerList.get(i).getPassword());
            if(passwordAsString.equals(currentCustomerPassword))
            {
                isTrue = true;
            }
        }
        return isTrue;
    }
    
    public int getLoginAttempts()
    {
        return loginAttempts; 
    }
          
    public void resetLoginAttempts()
    {
        loginAttempts = 0; 
    }
    
}
