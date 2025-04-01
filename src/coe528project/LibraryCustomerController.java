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
    private TableColumn<Book, CheckBox> selectColumn;

    //Text input
    @FXML
    private TextField nameInput;
    
    @FXML
    private Label welcomeLabel;
    //private TextField priceInput;

    private Customer buyer = new Customer();
    
    private ObservableList<Book> myLibrary;


    public void initData(Customer customer ){   //use this on the login screen so we can get info about the customer if login succeeds so the state mode actually
        this.buyer = customer;
        welcomeLabel.setText("Welcome "+this.buyer.getUsername()+". You have "+this.buyer.getPoints()+" points. Your status is "+this.buyer.checkStatus());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        
        welcomeLabel.setWrapText(true);
        welcomeLabel.setPrefWidth(500);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("selected"));
        for(int i = 0; i<Library.getInstance().getLibrary().size();i++){    //change to remove selected books
            myLibrary.add(Library.getInstance().getLibrary().get(i));
        }
        tableView.setItems(myLibrary);
    }

    //Submit button
    @FXML
    void buy(ActionEvent event) throws IOException{
        buyer.saveBooks(Library.getInstance()); //checks for the check boxes
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("confirm checkout");
        alert.setHeaderText("You are about to pay");
        alert.setContentText("the books selected will be saved if you leave now");
        if(alert.showAndWait().get()==ButtonType.OK){
            //check if books are still available
            for(int i = 0;i<buyer.getSavedBooks().size();i++){
                if(!Library.getInstance().verifyBooks(buyer.getSavedBooks().get(i))){
                    buyer.getSavedBooks().remove(i);
                    System.out.println("Out of this book :"+buyer.getSavedBooks().get(i).getName());
                }
            }
            
            
            int cost = BuyBooks.getInstance().totalCost(buyer);
            BuyBooks.getInstance().buy(Library.getInstance(), buyer, cost);
            tableView.setItems(myLibrary);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CostView.fxml"));     //buy-screen
            Parent CostParent = loader.load();
            Scene costScene = new Scene(CostParent);
            CostViewController controller = loader.getController(); //will make this later dun wanna td
            controller.initData(cost, buyer);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(costScene);
            window.show();
        }

        
        
        
        
        
    }

    @FXML
    void redeemAndBuy(ActionEvent event) throws IOException{
        buyer.saveBooks(Library.getInstance()); //checks for the check boxes
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("confirm checkout");
        alert.setHeaderText("You are about to pay");
        alert.setContentText("the books selected will be saved if you leave now");
        if(alert.showAndWait().get()==ButtonType.OK){
            //check if books are still available
            for(int i = 0;i<buyer.getSavedBooks().size();i++){
                if(!Library.getInstance().verifyBooks(buyer.getSavedBooks().get(i))){
                    buyer.getSavedBooks().remove(i);
                    System.out.println("Out of this book :"+buyer.getSavedBooks().get(i).getName());
                }
            }
            int cost = BuyBooks.getInstance().totalCost(buyer);
            BuyBooks.getInstance().redeemAndBuy(Library.getInstance(), buyer, cost);
            tableView.setItems(myLibrary);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CostView.fxml"));     //buy-screen
            Parent CostParent = loader.load();
            Scene costScene = new Scene(CostParent);
            CostViewController controller = loader.getController(); //will make this later dun wanna td
            controller.initData(cost, buyer);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(costScene);
            window.show();
        }
    }
    
    @FXML
    void logout(ActionEvent event)throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to log out");
        alert.setContentText("the books selected will be saved when you leave");
        if(alert.showAndWait().get()==ButtonType.OK){
            buyer.writeToFile("customers.txt");      //change file name
            Library.getInstance().writeToFile("books.txt");  //change file name
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));   //login screen
            Scene loginScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
            
            System.out.println("You successfully logged out");
        }
        
    }
}