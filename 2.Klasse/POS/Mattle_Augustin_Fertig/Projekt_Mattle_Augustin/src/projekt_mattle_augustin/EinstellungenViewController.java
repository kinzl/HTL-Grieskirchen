/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mattl
 */
public class EinstellungenViewController implements Initializable {

    @FXML
    private Label aktuellergolfnameID;
    @FXML
    private TextField neuerGolfnameID;
    @FXML
    private Label aktuellergolfkeyID;
    @FXML
    private TextField neuerGolfSchlüselID;

    private Parent root;
    private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ViewLoginController m = new ViewLoginController();
                Map<String, String>map =  m.getClubs();
                
                
            }
        });
        
        
    }    

    @FXML
    private void nameSwitchAktion(ActionEvent event) {
    
    }

    @FXML
    private void keySwitshAktion(ActionEvent event) {
    
    }

    @FXML
    private void bckAktion(ActionEvent event) {
        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
            root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
