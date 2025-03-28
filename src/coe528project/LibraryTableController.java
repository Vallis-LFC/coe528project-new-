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

public class LibraryTableController implements Initializable {
    //Library temp;
    //Table
    @FXML
    private TableView<Book> tableView;

    //Columns
    @FXML
    private TableColumn<Book, String> nameColumn;


    @FXML
    private TableColumn<Book, Integer> priceColumn;

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
        for(int i = 0; i<Library.getInstance().getLibrary().size();i++){
            myLibrary.add(Library.getInstance().getLibrary().get(i));
        }
        tableView.setItems(myLibrary);
    }

    //Submit button
    @FXML
    void addBook(ActionEvent event) {
        Book b = new Book(nameInput.getText(), Integer.parseInt(priceInput.getText()));

        myLibrary.add(b);
        Library.getInstance().addBooks(b);
        tableView.setItems(myLibrary);
    }

    @FXML
    void removeBook(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        Book del = tableView.getItems().get(selectedID);
        tableView.getItems().remove(selectedID);
        Library.getInstance().deleteBooks(del);
        myLibrary.remove(del);
    }
}