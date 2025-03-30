/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528project;



import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author vkhlu
 */
public class CostViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Customer buyer;
    private int totalCost;
    private Label cost;
    private Label ptstatus;
    
    public void initData(int cost, Customer customer ){   //use this on the login screen so we can get info about the customer if login succeeds so the state mode actually
        this.buyer = customer;
       this.totalCost = cost;
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cost.setText("Total Cost: "+totalCost);
        cost.setWrapText(true);
        cost.prefWidth(500);
        ptstatus.setText("Points: "+buyer.checkPoints()+ ", Status: "+buyer.checkStatus());
        ptstatus.setWrapText(true);
        ptstatus.prefWidth(500);
    }   
    
    @FXML
    void logout(ActionEvent event)throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to log out");
        alert.setContentText("the books selected will be saved when you leave");
        if(alert.showAndWait().get()==ButtonType.OK){
            buyer.writeToFile("");      //change file name
            Library.getInstance().writeToFile("");  //change file name
            Parent tableViewParent = FXMLLoader.load(getClass().getResource(""));   //login screen
            Scene loginScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
            
            System.out.println("You successfully logged out");
        }
        
    }
}
