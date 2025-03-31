/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/*
 *
 * @author vkhlu
 */
public class Main extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        File books = new File("books.txt");
        File customers = new File("customers.txt");
        Library.getInstance().readToFile(books);
        Customer tmp = new Customer("","");
        tmp.readToFile(customers);
        
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        
      
        
        primaryStage.setTitle("BookStore");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
//    private void read() {//read customers file to put usernames and passwords into array
//        BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));
//        String line = reader.readLine();
//
//        while (line != null) {
//            
//            
//        }
//    }
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}