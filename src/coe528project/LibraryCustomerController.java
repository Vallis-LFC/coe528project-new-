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
    private TableView<Book> BookView;

    //Columns
    @FXML
    private TableColumn<Book, String> nameColumn;


    @FXML
    private TableColumn<Book, Integer> priceColumn;
    
    @FXML
    private TableColumn<Book, String> selectColumn;

    //Text input
//    @FXML
//    private TextField nameInput;
    
    @FXML
    private Label welcomeLabel;
    //private TextField priceInput;

    private Customer buyer;
    
    @FXML
    private static ObservableList<Book> myLibrary = FXCollections.observableArrayList() ;

    @FXML
    public void initData(Customer customer ){   //use this on the login screen so we can get info about the customer if login succeeds so the state mode actually
        this.buyer = customer;
        System.out.println(this.buyer.getPoints());
        welcomeLabel.setText("Welcome "+this.buyer.getCustomer().getUsername()+". You have "+this.buyer.getCustomer().getPoints()+" points. Your status is "+this.buyer.getCustomer().checkStatus());
        welcomeLabel.setWrapText(true);
        welcomeLabel.setPrefWidth(500);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {
        
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("selected"));
        for(int i = 0; i<Library.getInstance().getLibrary().size();i++){    //change to remove selected books
            if(!myLibrary.contains(Library.getInstance().getLibrary().get(i))){
                myLibrary.add(Library.getInstance().getLibrary().get(i));
            }
            
            System.out.println(Library.getInstance().getLibrary().get(i).getName());
        }
        BookView.setItems(myLibrary);
    }

    //Submit button
    @FXML
    void buy(ActionEvent event) throws IOException{
        int cost = 0;
        buyer.getCustomer().saveBooks(Library.getInstance());
        //buyer.saveBooks(Library.getInstance());//checks for the check boxes
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("confirm checkout");
        alert.setHeaderText("You are about to pay");
        alert.setContentText("the books selected will be saved if you leave now");
        if(alert.showAndWait().get()==ButtonType.OK){
            //check if books are still available
            
            for(int i = 0;i<buyer.getCustomer().getSavedBooks().size();i++){
                if(Library.getInstance().verifyBooks(buyer.getCustomer().getSavedBooks().get(i))){
                    buyer.getCustomer().getSavedBooks().remove(i);
                    System.out.println("Out of this book :"+buyer.getCustomer().getSavedBooks().get(i).getName());
                }
            }
           
            
            cost = BuyBooks.getInstance().totalCost(buyer.getCustomer());
            BuyBooks.getInstance().buy(Library.getInstance(), buyer.getCustomer(), cost);
            ObservableList<Book> removed = FXCollections.observableArrayList();
            for(Book b: myLibrary){
                if(b.checkSelected()){
                    System.out.println("test");
                    removed.add(b);
                }
            }
            myLibrary.removeAll(removed);
            for(int i = 0; i<Library.getInstance().getLibrary().size();i++){    //change to remove selected books
            
            System.out.println(Library.getInstance().getLibrary().get(i).getName());
        }
            //BookView.setItems(myLibrary);
            //BookView.setItems(myLibrary);
            FXMLLoader loader = new FXMLLoader((getClass().getResource("CostView.fxml")));     //buy-screen
            Parent CostParent = loader.load();          
            CostViewController controller = loader.getController(); //will make this later dun wanna td
            controller.initData(cost, buyer.getCustomer());
            
            //System.out.println(cost);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene costScene = new Scene(CostParent);
            window.setScene(costScene);
            window.show();
        }

        
        
        
        
        
    }

    @FXML
    void redeemAndBuy(ActionEvent event) throws IOException{
        buyer.getCustomer().saveBooks(Library.getInstance()); //checks for the check boxes
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("confirm checkout");
        alert.setHeaderText("You are about to pay");
        alert.setContentText("the books selected will be saved if you leave now");
        if(alert.showAndWait().get()==ButtonType.OK){
            //check if books are still available
            for(int i = 0;i<buyer.getCustomer().getSavedBooks().size();i++){
                if(Library.getInstance().verifyBooks(buyer.getCustomer().getSavedBooks().get(i))){
                    buyer.getCustomer().getSavedBooks().remove(i);
                    System.out.println("Out of this book :"+buyer.getCustomer().getSavedBooks().get(i).getName());
                }
            }
            int cost = BuyBooks.getInstance().totalCost(buyer.getCustomer());
            cost = BuyBooks.getInstance().redeemAndBuy(Library.getInstance(), buyer.getCustomer(), cost);
            ObservableList<Book> removed = FXCollections.observableArrayList();
            for(Book b: myLibrary){
                if(b.checkSelected()){
                    removed.add(b);
                }
            }
            myLibrary.removeAll(removed);
            //BookView.setItems(myLibrary);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CostView.fxml"));     //buy-screen
            Parent CostParent = loader.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene costScene = new Scene(CostParent);
            CostViewController controller = loader.getController(); //will make this later dun wanna td
            controller.initData(cost, buyer.getCustomer());

            
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
            buyer.getCustomer().writeToFile("customers.txt");      //change file name
            //Library.getInstance().writeToFile("books.txt");  //change file name
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));   //login screen
            Scene loginScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
            
            System.out.println("You successfully logged out");
        }
        
    }
}