package coe528project;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.geometry.Insets;

public class Owner_state implements Client_state {
    private final Library library = Library.getInstance();
    private TableView<Book> bookTable;
    private TextField bookNameField, bookPriceField;

    @Override
    public Parent getView() {
        // 1. Create Book Table
        bookTable = new TableView<>();
        
        TableColumn<Book, String> nameCol = new TableColumn<>("Book Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Book, Integer> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        bookTable.setItems(FXCollections.observableArrayList(library.getLibrary()));

        // 2. Create Book Management Controls
        bookNameField = new TextField();
        bookNameField.setPromptText("Book Name");
        
        bookPriceField = new TextField();
        bookPriceField.setPromptText("Price");
        
        Button addBtn = new Button("Add Book");
        addBtn.setOnAction(e -> addBook());
        
        Button removeBtn = new Button("Remove Selected");
        removeBtn.setOnAction(e -> removeBook());
        
        HBox bookControls = new HBox(10, bookNameField, bookPriceField, addBtn, removeBtn);
        bookControls.setPadding(new Insets(10));

        // 3. Create Layout
        VBox root = new VBox(10, bookTable, bookControls);
        root.setPadding(new Insets(15));
        
        return root;
    }

    private void addBook() {
        try {
            String name = bookNameField.getText();
            int price = Integer.parseInt(bookPriceField.getText());
            
            Book newBook = new Book(name, price);
            if (!library.verifyBooks(newBook)) {
                library.getLibrary().add(newBook);
                refreshTable();
                bookNameField.clear();
                bookPriceField.clear();
            } else {
                showAlert("Error", "Book already exists!");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid price format!");
        }
    }

    private void removeBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            library.deleteBooks(selected);
            refreshTable();
        }
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
            library.writeToFile("books.txt");
        } catch (IOException e) {
            System.err.println("Failed to save library data");
        }
    }

    @Override
    public void goBackToMenu() {
        refreshTable();
    }
}