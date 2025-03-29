/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528project;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.collections.ObservableList;

        
/**
 *
 * @author vkhlu
 */
public class Customer {
    private int points;
    //private CheckBox select;
    
    private String username;
    private String password;
    private static ArrayList<Customer> customers ;
    private String[] Status = {"Silver", "Gold"};
    private ArrayList<Book> SelectedBooks;
    
    public Customer(String username, String password){
        
        this.username = username;
        this.password = password;
        this.points = 0; //starts at 0
        //select = new CheckBox(this.username+" | "+this.password+" | "+this.points+" | ");
        
    }
    
 
    public Customer( String username, String password, int point){
        
        this.username = username;
        this.password = password;
        this.points = point;
       // select = new CheckBox("Customer username: "+this.username);
        
    }
    
    public void addCustomer() throws NullPointerException{
        if(customers.isEmpty()){
            customers.add(this);
        }
        else if(this.verifyPassword()){
            customers.add(this);
        }
        else{
            System.err.println("same password");
        }
        
    }
    
    public void deleteCustomer(){
        if(this.findCustomer()){
            customers.remove(this);
        }
        else{
            System.err.println("No such customer found");
        }
        
    }
    
//    public void display(){      //makes the table
//        
//    }
    
//    public ArrayList<Customer> selectCustomers(){       //change
//        ArrayList<Customer> selected = new ArrayList<Customer>();
//        for(int i = 0; i<customers.size();i++){
//            if (customers.get(i).checkSelected() == true){
//                selected.add(customers.get(i));
//            }
//        }
//        return selected;
//    }
    
//    public void clearCheckBoxes(){
//        for(int i = 0; i<customers.size();i++){
//            customers.get(i).setSelected(false);
//        }
//    }
    
//    public boolean checkSelected(){
//        //check for selected customers
//        return this.select.isSelected();
//    }
    
    public void readToFile(File input) throws FileNotFoundException{
        try{
            Scanner myReader = new Scanner(input);
            while(myReader.hasNextLine()){
                
                String usr = myReader.next();
                String pass = myReader.next();
                int p = myReader.nextInt();
                customers.add( new Customer( usr, pass, p)); //adds to array and intializes the customer 
                myReader.nextLine();//increments to next line
            }
            customers.add(new Customer( "admin", "admin"));   //adds manager to the list for the login
        }
        catch(FileNotFoundException e){
            System.err.println("No File Found");
        }

        
    }
    public void writeToFile(String output) throws IOException{
        try {
            FileWriter updated = new FileWriter(output);
            for (int i = 0; i<customers.size();i++){
                if(!customers.get(i).verifyPassword()){   //skips the manager to put into the customer txt
                    continue;
                }
                updated.write(customers.get(i).getUsername()+"\t"+customers.get(i).getPassword()+"\t"+customers.get(i).checkPoints()); //no need for status since we can find it with points
            }
            updated.close();
            System.out.println("Customers updated");
        }
        catch (IOException e){
            System.err.println("Error occurred");
            
        }
    }
    
    public void clearFile(File input) throws IOException{
        if(input.delete()){
            input.createNewFile();
        }
        else{
            throw new IOException("File not found");
        }
    }
    
    public String toString(){   //i lk forgor why we need a toString()
        return this.username+this.password;
    }
    
    public void addPoint(int cost){
        int p = 10*cost;
        this.points +=p;
    }
    
    public int minusPoint(int total){   //minus point and returns cost
        
        int redeemable = this.points*100;
        
        if (total-redeemable>=0){
            this.points = 0;
            return total-redeemable;
        }
        
        int p = total*100; //total cost of the books
        this.points-= p;
        return 0;
        
    }
    
    public int checkPoints(){
        return this.points;
    }
    
    public String checkStatus(){
        if(this.points>1000){
            return Status[1];
        }
        return Status[0];
    }
    
    public boolean findCustomer(){
        for(int i=0; i< customers.size();i++){
            if(customers.get(i).getUsername().equals(this.username) && customers.get(i).getPassword().equals(this.password) ){
                return true;
            }
        }
        //System.err.println("No such customer found");
        return false;
    }
    
    public String getUsername(){
        return this.username;
    }
    

    
    public String getPassword(){
        return this.password;
    }
    
    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }
        
    
    public boolean verifyPassword() throws NullPointerException{ //verifys a different password so that we can use same username

        for(int i = 0; i< customers.size();i++){
            if(customers.get(i).getPassword().equals("admin")){
                return false;
            }
            else if(customers.get(i).getPassword().equals(this.password)){
                return false;
            }
        }
        return true;
    }
    
//    public void setSelected(boolean check){
//        this.select.setSelected(check);
//    }
 
    
    public ArrayList<Book> getSavedBooks(){
        return this.SelectedBooks;
    }
    
    public void saveBooks(Library myLibrary){   //stay as is
        this.SelectedBooks = myLibrary.selectBooks(); 
    }
    
}
