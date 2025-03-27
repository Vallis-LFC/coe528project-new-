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
/**
 *
 * @author vkhlu
 */
public class Book {
    private String name;
    private int price;
    //private CheckBox selected;
    
    public Book(String name, int price){
        this.name = name;
        this.price = price;
        //selected = new CheckBox(this.name+" | "+this.price+" | "); //changed so when displaying it would look like a table
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getPrice(){
        return this.price;
    }
    
//    public void setSelected(boolean check){
//        (this.selected).setSelected(check);
//    }
    
//    public CheckBox getSelected(){
//        return this.selected;
//    }
//    
//    public boolean checkSelected(){
//        return this.selected.isSelected();
//    }
}
