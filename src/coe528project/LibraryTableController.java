/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528project;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryTableController implements Initializable {
    //Library temp;
    //Table
    @FXML
    private TableView<Book> tableView;

    //Columns
    @FXML
    //private Label nameColumn;
    private TableColumn<Book, String> nameColumn = new TableColumn<Book, String>();


    @FXML
    //private TableColumn<Book, Integer> priceColumn;
    private TableColumn<Book, Integer> priceColumn = new TableColumn<Book, Integer>();

    //Text input
    @FXML
    private TextField nameInput;
    
    @FXML
    private TextField priceInput;



    private ObservableList<Book> myLibrary = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        //if(this.myLibrary != null && !this.myLibrary.isEmpty() ){
            for(int i = 0; i<Library.getInstance().getLibrary().size();i++){
                //System.out.println()
                myLibrary.add(Library.getInstance().getLibrary().get(i));
                
            }
        
	
		//System.out.println("null");
          //makes a empty customer but since its not added into the list, doesnt matter
          
        
        
        tableView.setItems(myLibrary);
        
    }

    //Submit button
    @FXML
    void addBook(ActionEvent event) {
        Book b = new Book(nameInput.getText(), Integer.parseInt(priceInput.getText()));
        myLibrary = tableView.getItems();
        
        System.out.println("here");
        if (Library.getInstance().verifyBooks(b)){
            myLibrary.add(b);
            Library.getInstance().addBooks(b);}
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
    
    @FXML
    void back(ActionEvent event) throws IOException{
        Parent managerParent = FXMLLoader.load(getClass().getResource("OwnerView.fxml"));   //change when u have the manager scene
        Scene managerScene = new Scene(managerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(managerScene);
        window.show();
    }
}
