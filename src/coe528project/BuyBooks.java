/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528project;
//import java.util.ArrayList;
/**
 *
 * @author vkhlu
 */
public class BuyBooks {
    private static BuyBooks instance; //singleton to make the constructor unreachable so functions can be used without risk
//    private BuyBooks(){
//        
//        
//    }
    
    public static BuyBooks getInstance(){
        if(instance == null){
            instance = new BuyBooks();
        }
        return instance;
    }
    
    public int totalCost(Customer buyer){
        int total = 0;
        
        for(int i = 0; i<buyer.getSavedBooks().size();i++){
            total+=buyer.getSavedBooks().get(i).getPrice();
        }
        return total;
    }
    
    public void buy(Library myLibrary, Customer buyer, int total){
        buyer.addPoint(total);
        for(int i = 0; i<buyer.getSavedBooks().size(); i++){
            myLibrary.deleteBooks(buyer.getSavedBooks().get(i));
        }
        buyer.getSavedBooks().clear();
        Library.getInstance().clearCheckBoxes();
        
        
    }
    
    
    public int redeemAndBuy(Library myLibrary, Customer buyer, int total) {
        int remainingCost = buyer.minusPoint(total); // This now returns remaining cost
        if (remainingCost > 0) {
            buyer.addPoint(remainingCost); // Add points for remaining amount paid
        }
        
        for (Book book : buyer.getSavedBooks()) {
            myLibrary.deleteBooks(book);
        }
        buyer.getSavedBooks().clear();
        myLibrary.clearCheckBoxes();
        
        return remainingCost; // Return the remaining cost after redemption
    }
}
