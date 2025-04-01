/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vkhlu
 */
public class OwnerViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    
    
     @FXML
    void customerButton(ActionEvent event) throws IOException{
        Parent managerParent = FXMLLoader.load(getClass().getResource("CustomerTable.fxml"));   //change when u have the manager scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene managerScene = new Scene(managerParent);
        window.setScene(managerScene);
        window.show();
    }
    
    @FXML
    void libraryButton(ActionEvent event) throws IOException{
        Parent managerParent = FXMLLoader.load(getClass().getResource("LibraryTable.fxml"));   //change when u have the manager scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene managerScene = new Scene(managerParent);
        window.setScene(managerScene);
        window.show();
    }
    
   
    @FXML
    void logout(ActionEvent event)throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to log out");
        //alert.setContentText("the books selected will be saved when you leave");
        if(alert.showAndWait().get()==ButtonType.OK){
            Library.getInstance().writeToFile("books.txt");  //change file name
            Customer tmp = new Customer("","");
            tmp.writeToFile("customers.txt");
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));   //login screen
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(tableViewParent);
            window.setScene(loginScene);
            window.show();
            
            System.out.println("You successfully logged out");
        }
        
    }
}
