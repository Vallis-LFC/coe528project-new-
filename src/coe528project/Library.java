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
    
    public boolean verifyBooks(Book toBeVerified){
        return false;   //change
    }
    
    public void deleteBooks(Book d){
        
    }
    
    public void addBooks(Book a){
        
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
