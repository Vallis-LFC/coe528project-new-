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
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryCustomerController implements Initializable {
    //Library temp;
    //Table
    @FXML
    private TableView<Book> tableView;

    //Columns
    @FXML
    private TableColumn<Book, String> nameColumn;


    @FXML
    private TableColumn<Book, Integer> priceColumn;
    
    @FXML
    private TableColumn<Book, CheckBox> selected;

    //Text input
    @FXML
    private TextField nameInput;
    
    @FXML
    private TextField priceInput;



    private ObservableList<Book> myLibrary;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Book Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("Price"));
        selected.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("Select"));
        for(int i = 0; i<Library.getInstance().getLibrary().size();i++){    //change to remove selected books
            myLibrary.add(Library.getInstance().getLibrary().get(i));
        }
    }

    //Submit button
    @FXML
    void buy(ActionEvent event) {
        buyBooks()
        
        
        
        
        
        tableView.setItems(myLibrary);
        
        
    }

    @FXML
    void redeemAndBuy(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        Book del = tableView.getItems().get(selectedID);
        tableView.getItems().remove(selectedID);
        Library.getInstance().deleteBooks(del);
        myLibrary.remove(del);
    }
}