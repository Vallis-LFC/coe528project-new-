/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528project;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
 *
 * @author vkhlu
 */
public class Library {
    private ArrayList<Book> myLibrary;
    private static Library singleton;
    private Library(){
        this.myLibrary = new ArrayList<Book>();
    }
    
    public static Library getInstance(){
        if(singleton == null){
            singleton = new Library();
        }
        return singleton;
    }
    
    public boolean verifyBooks(Book toBeVerified){  //i believe verifybooks is to check if book is in arraylist
        for(int i = 0; i< myLibrary.size(); i++){
            Book current = myLibrary.get(i);
            if((current.getName()).equals(toBeVerified.getName()) && current.getPrice() == toBeVerified.getPrice()){
                return true;
            }
        }
        return false;   //change
    }
    
    public void deleteBooks(Book d){
        if(this.verifyBooks(d)){
            myLibrary.remove(d);
        }
        
    }
    
    public void addBooks(Book a){
        if(this.verifyBooks(a)){
            myLibrary.add(a);
        }
        
    }
    
    public void readToFile(File input) throws FileNotFoundException{
        try{
            Scanner myReader = new Scanner(input);
            while(myReader.hasNextLine()){
                String name = myReader.next();
                int price = myReader.nextInt();
                myLibrary.add(new Book(name, price));
                myReader.nextLine();
            }
        }
        catch(FileNotFoundException e){
            System.err.println("No File Found");
        }

        
    }
    
    public void writeToFile(File output){
        
    }
    
    public void clearFile(File input){
        
    }
    
    public void display(){
        
    }
    
    public ArrayList<Book> selectBooks(){
        //check for books checkmarked
        return //placeholder;
    }
    
    public void clearCheckBoxes(){
        //clear all checkboxes in the book
    }
}
