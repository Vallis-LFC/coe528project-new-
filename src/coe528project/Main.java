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

/*
 *
 * @author vkhlu
 */
public class Main extends Application {
    
    private static ArrayList<String> Usernames;
    private static ArrayList<String> Passwords;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        
        Parent root = FXMLLoader.load(getClass().getResource("CustomerTable.fxml"));
        
      
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    private void read() {//read customers file to put usernames and passwords into array
        BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));
        String line = reader.readLine();

        while (line != null) {
            
            
        }
    }
    
    public static int verifyLogin (String u, String p){
        int check = 0;
        int i;
        for (i=0; i < Usernames.size(); i++){
            if(Usernames.get(0).equals(u) && Passwords.get(0).equals(p)){//i'm just assuming the first username and password in the array list belongs to the owner
                check = 1;
            }
            if(Usernames.get(i).equals(u) && Passwords.get(i).equals(p)){
                check = 2;
            }
        }
        
        return check;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}