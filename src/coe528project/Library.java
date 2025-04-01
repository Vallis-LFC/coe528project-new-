/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528project;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
        if(this.myLibrary != null && !this.myLibrary.isEmpty() ){
            for(int i = 0; i< myLibrary.size(); i++){
                Book current = myLibrary.get(i);
                if((current.getName()).equals(toBeVerified.getName()) && current.getPrice() == toBeVerified.getPrice()){
                    return false;
                }
            }
        }
        return true;   //change
    }
    
    public void deleteBooks(Book d){
        if(!this.verifyBooks(d)){
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
            while(myReader.hasNextLine() && myReader.hasNext()){
                String name = myReader.next();
                int price = myReader.nextInt();
                myLibrary.add(new Book(name, price));
                //myReader.nextLine();
            }
            myReader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("No File Found");
        }

        
    }
    
    public void writeToFile(String output) throws IOException{
        try {
            FileWriter updated = new FileWriter(output);
            for (int i = 0; i<myLibrary.size();i++){
                System.out.println(myLibrary.get(i).getName()+"\t"+myLibrary.get(i).getPrice());
                updated.write(myLibrary.get(i).getName()+"\t"+myLibrary.get(i).getPrice());
            }
            updated.close();
            System.out.println("Library updated");
        }
        catch (IOException e){
            System.err.println("Error occurred");
            
        }
    }
    
    public void clearFile(File input) throws IOException{
        //deletes the file and creates a new one with same name
        if(input.delete()){
            input.createNewFile();
        }
        else{
            throw new IOException("File not found");
        }
        
    }
    
//    public void display(){
//        for (int i = 0; i< myLibrary.size();i++){
//            //display the checkbox with the label already written down
//            //no idea hwo to display the checkbox or it comes like that tho
//            
//           // myLibrary.get(i).getSelected(); //might do it????? 
//        }
//    }
//    
    public ArrayList<Book> selectBooks(){
        //check for books checkmarked
        ArrayList<Book>selected = new ArrayList<Book>();
        for(int i = 0; i<myLibrary.size();i++){
            if(myLibrary.get(i).checkSelected()== true){
                selected.add(myLibrary.get(i));
            }
        }
        return selected;//placeholder;
    }
    
    public ArrayList<Book> getLibrary(){
        return myLibrary;
    }
    
    public void clearCheckBoxes(){
        //clear all checkboxes in the book
        for(int i = 0; i<myLibrary.size();i++){
            myLibrary.get(i).setSelected(false);
        }
    }
}
