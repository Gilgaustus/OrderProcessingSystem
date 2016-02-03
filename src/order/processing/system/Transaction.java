/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.processing.system;

public abstract class Transaction
{
    int transactionID;
    int customerID;
    
    abstract void process();
    
}
