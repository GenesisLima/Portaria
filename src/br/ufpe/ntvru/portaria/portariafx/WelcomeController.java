/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpe.ntvru.portaria.portariafx;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Gênesis Lima
 */
public class WelcomeController implements Initializable {

    @FXML
    private JFXButton btnLogOut;
    @FXML
    private TextField txtLoggedUser;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // display logged user
        
    }    

    @FXML
    private void logOut(ActionEvent event) {
    }
    
}
