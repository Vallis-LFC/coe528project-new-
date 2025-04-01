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
    
    @FXML
    void SwitchToCustomerScreen(ActionEvent event) throws IOException{
        
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        Customer c = new Customer();
        c.setUsername(username);
        c.setPassword(password);
        System.out.println(username);
        System.out.println(password);
        if(username.equals("admin") && password.equals("admin")){
            System.out.println("test");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerView.fxml"));
            root = loader.load();
            
            
        }
        else if (c.findCustomer() == true){
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LibraryCustomer.fxml"));
            System.out.println("in");
            root = loader.load();
            LibraryCustomerController controller = loader.getController();
            controller.initData(c);
            
        }
        else{ //window pop up for incorrect user and pass combination
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
            System.err.println("Wrong login");
            root = loader.load();
        }
        
        
        
        //Parent root = FXMLLoader.load(getClass().getResource("LibraryCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
    
}