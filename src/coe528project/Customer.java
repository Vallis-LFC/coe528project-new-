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

        
/**
 *
 * @author vkhlu
 */
public class Customer {
    private int points;
    private CheckBox select;
    private String username;
    private String password;
    private ArrayList<Customer> customers;
    private String[] Status = {"Silver", "Gold"};
    private ArrayList<Book> SelectedBooks;
    
    public Customer(String username, String password){
        this.username = username;
        this.password = password;
        this.points = 0; //starts at 0
        select = new CheckBox("Customer username: "+this.username);
        
    }
    
    public Customer(String username, String password, int point){
        this.username = username;
        this.password = password;
        this.points = point;
        select = new CheckBox("Customer username: "+this.username);
        
    }
    
    public void addCustomer(){
        customers.add(this);
    }
    
    public void deleteCustomer(){
        customers.remove(this);
    }
    
    public void display(){
        
    }
    
    public ArrayList<Customer> selectCustomers(){
        ArrayList<Customer> selected = new ArrayList<Customer>();
        for(int i = 0; i<customers.size();i++){
            if (customers.get(i).checkSelected() == true){
                selected.add(customers.get(i));
            }
        }
        return selected;
    }
    
    public void clearCheckBoxes(){
        for(int i = 0; i<customers.size();i++){
            customers.get(i).setSelected(false);
        }
    }
    
    public boolean checkSelected(){
        //check for selected customers
        return this.select.isSelected();
    }
    
    public void readToFile(File input) throws FileNotFoundException{
        try{
            Scanner myReader = new Scanner(input);
            while(myReader.hasNextLine()){
                String usr = myReader.next();
                String pass = myReader.next();
                int p = myReader.nextInt();
                customers.add( new Customer(usr, pass, p)); //adds to array and intializes the customer 
                myReader.nextLine();//increments to next line
            }
        }
        catch(FileNotFoundException e){
            System.err.println("No File Found");
        }

        
    }
    public void writeToFile(String output) throws IOException{
        try {
            FileWriter updated = new FileWriter(output);
            for (int i = 0; i<customers.size();i++){
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
    
    public void addPoint(int p){
        this.points +=p;
    }
    
    public void minusPoint(int p){
        this.points-= p;
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
    
    public Customer findCustomer(String username, String password){
        for(int i=0; i< customers.size();i++){
            if(customers.get(i).getUsername().equals(username) && customers.get(i).getUsername().equals(password)){
                return customers.get(i);
            }
        }
        System.err.println("No such customer found");
        return null;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public boolean verifyPassword(String password){ //verifys a different password so that we can use same username
        for(int i = 0; i< customers.size();i++){
            if(customers.get(i).getPassword().equals(password)){
                return false;
            }
        }
        return true;
    }
    
    public void setSelected(boolean check){
        this.select.setSelected(check);
    }
}
