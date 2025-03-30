package coe528project;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Insets;

public class Customer_state implements Client_state {
    private final Customer customer;
    private final Library library = Library.getInstance();
    private TableView<Book> bookTable;

    public Customer_state(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Parent getView() {
        // 1. Create Book Table with Checkboxes
        bookTable = new TableView<>();
        
        TableColumn<Book, String> nameCol = new TableColumn<>("Book");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Book, Integer> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Book, CheckBox> selectCol = new TableColumn<>("Select");
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selected"));

        bookTable.setItems(FXCollections.observableArrayList(library.getLibrary()));

        // 2. Create Action Buttons
        Button buyBtn = new Button("Buy Selected");
        buyBtn.setOnAction(e -> handleBuy());
        
        Button redeemBtn = new Button("Redeem Points");
        redeemBtn.setOnAction(e -> handleRedeem());
        
        Button pointsBtn = new Button("View Points");
        pointsBtn.setOnAction(e -> showPoints());
        
        HBox buttonBox = new HBox(10, buyBtn, redeemBtn, pointsBtn);
        buttonBox.setPadding(new Insets(10));

        // 3. Create Layout
        VBox root = new VBox(10, bookTable, buttonBox);
        root.setPadding(new Insets(15));
        
        return root;
    }

    private void handleBuy() {
        library.clearCheckBoxes();
        int total = BuyBooks.getInstance().totalCost(customer);
        if (total > 0) {
            BuyBooks.getInstance().buy(library, customer, total);
            refreshTable();
            showAlert("Purchase Complete", 
                     "Earned " + total + " points!" + 
                     "\nNew Balance: " + customer.checkPoints() + 
                     " (" + customer.checkStatus() + ")");
        }
    }
    private void handleRedeem() {
        library.clearCheckBoxes();
        int total = BuyBooks.getInstance().totalCost(customer);
        ArrayList<Book> selectedBooks = library.selectBooks();
        customer.saveBooks(library); 
        if (total > 0) {
            int remainingCost = BuyBooks.getInstance().redeemAndBuy(library, customer, total);
            
            refreshTable();
            String message;
            if (remainingCost > 0) {
                message = "Partially redeemed!\n" +
                          "Remaining Cost: $" + remainingCost +
                          "\nNew Points: " + customer.checkPoints();
            } else {
                message = "Fully redeemed with points!\n" +
                          "New Points: " + customer.checkPoints();
            }
            showAlert("Purchase Complete", message);
        }
    }

    private void showPoints() {
        showAlert("Your Status", 
            "Points: " + customer.checkPoints() + 
            "\nStatus: " + customer.checkStatus());
    }

    private void refreshTable() {
        bookTable.setItems(FXCollections.observableArrayList(library.getLibrary()));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void requestLogout() {
        try {
            customer.writeToFile("customers.txt");
        } catch (IOException e) {
            System.err.println("Failed to save customer data");
        }
    }

    @Override
    public void goBackToMenu() {
        refreshTable();
    }
}