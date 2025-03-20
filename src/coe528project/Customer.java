/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528project;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 
import java.util.ArrayList;

        
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
    private ArrayList<String> Status;
    private ArrayList<Book> SelectedBooks;
    
    public Customer(String username, String password){
        this.username = username;
        this.password = password;
        this.points = 0; //starts at 0
        select = new CheckBox("Customer username: "+this.username);
        customers.add(this);
    }
    
    public void addCustomer(Customer e){
        
    }
    
    public void deleteCustomer(){
        
    }
    
    public void display(){
        
    }
    
    public void selectCustomers(){
        
    }
    
    public void clearCheckBoxes(){
        
    }
    
    public ArrayList<Customer> checkSelected(){
        //check for selected customers
        return;
    }
    
    public void writeToFile(){
        
    }
    
    public void clearFile(){
        
    }
    
    public String toString(){
        return "test";
    }
    
    public void addPoint(int p){
        
    }
    
    public void minusPoint(int p){
        
    }
    
    public String checkStatus(){
        return "";
    }
    
    public Customer findCustomer(String username, String password){
        return;
    }
    
    public String getUsername(){
        return ";";
    }
    
    public String getPassword(){
        return "";
    }
}
