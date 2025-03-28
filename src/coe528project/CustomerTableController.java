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

import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField nameInput;
    
    @FXML
    private TextField usrInput;

    @FXML
    private TextField passInput;

    private ObservableList<Customer> customers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usrColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Username"));
        passColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Password"));
        ptsColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("Points"));
    }

    //Submit button
    @FXML
    void addCustomer(ActionEvent event) {
        Customer customer = new Customer(nameInput.getText(), usrInput.getText(),passInput.getText());
        customers = customer.getCustomers();
        customer.addCustomer();
        tableView.setItems(customers);
    }

    @FXML
    void removeCustomer(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        Customer del = tableView.getItems().get(selectedID);
        tableView.getItems().remove(selectedID);
        del.deleteCustomer();
    }
}