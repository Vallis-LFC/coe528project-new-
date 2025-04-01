/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528project;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author viern
 */
public class LoginScreenController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField usernameTextField;
    
    @FXML
    private TextField passwordTextField;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }   
        
    public void SwitchToCustomerScreen(ActionEvent event) throws IOException{
        
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        Customer c = new Customer(username, password);
         
        
        if(username.equals("admin") && password.equals("admin")){
            root = FXMLLoader.load(getClass().getResource("OwnerView.fxml"));
        }
        else if (c.findCustomer() == true){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerTable.fxml"));
		LibraryCustomerController controller = loader.getController();
		controller.initData(c);
            root = loader.load();
        }
        else{ //window pop up for incorrect user and pass combination
            System.err.println("Wrong login");
        }
        
        
        //Parent root = FXMLLoader.load(getClass().getResource("LibraryCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
    
}