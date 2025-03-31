/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528project;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.util.ResourceBundle;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.lang.NullPointerException;
import javafx.stage.Stage;

public class CustomerTableController implements Initializable {
    //Table
    @FXML
    private TableView<Customer> tableView;

    //Columns
    @FXML
    private TableColumn<Customer, String> usrColumn;

    @FXML
    private TableColumn<Customer, String> passColumn;

    @FXML
    private TableColumn<Customer, Integer> ptsColumn;

    //Text input
    
    @FXML
    private TextField usrInput;

    @FXML
    private TextField passInput;

    @FXML
    private ObservableList<Customer> customers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException{
        usrColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        passColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        ptsColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("points"));
        Customer tmp = new Customer("","");
        if(this.customers != null && !this.customers.isEmpty() ){
            for(int i = 0; i<tmp.getCustomers().size();i++){
            customers.add(tmp.getCustomers().get(i));
        }
        }
   
          //makes a empty customer but since its not added into the list, doesnt matter
        
        //tableView.setItems(customers);
    }

    //Submit button
    @FXML
    void addCustomer(ActionEvent event) {
        Customer customer = new Customer( usrInput.getText(),passInput.getText());
        System.out.println(customer.getPoints());
        customers = tableView.getItems();
        
           if(customer.verifyPassword()){
            customers.add(customer);
            customer.addCustomer();
            
        }
        
        tableView.setItems(customers);
        

        
        
       
    }

    @FXML
    void removeCustomer(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        Customer del = tableView.getItems().get(selectedID);
        tableView.getItems().remove(selectedID);
        customers.remove(del);
        del.deleteCustomer();
    }
    
    @FXML
    void back(ActionEvent event) throws IOException{
        Parent managerParent = FXMLLoader.load(getClass().getResource(""));   //change when u have the manager scene
        Scene managerScene = new Scene(managerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(managerScene);
        window.show();
    }
}