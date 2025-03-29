/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author vkhlu
 */
public class CostViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Customer buyer;
    private int totalCost;
    
    public void initData(int cost, Customer customer ){   //use this on the login screen so we can get info about the customer if login succeeds so the state mode actually
        this.buyer = customer;
       this.totalCost = cost;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
