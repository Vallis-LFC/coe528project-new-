/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528project;

import javafx.scene.Parent;
import java.io.IOException;
/**
 *
 * @author munta
 */
public interface Client_state {
    public void requestLogout();
    public void goBackToMenu();
    Parent getView() throws IOException;
}
