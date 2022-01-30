/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bmattle19
 */
public class ViewController implements Initializable {
    
    private Label label;
    
    private Stage stage;
    private Parent root;
    private Parent root2;
    private  Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
    }    

    @FXML
    private void onActionBeenden(ActionEvent event) {
        Platform.exit();
    }

    

    @FXML
    private void onActionNeuesTurnier(ActionEvent event) {
       scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TurnierView.fxml"));
       try {
            root2 = (Parent) loader.load();
            scene.setRoot(root2);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    public void setPrimaryStage2(Stage stage) {
        this.stage = stage;
        
    }

    @FXML
    private void onActionNeuesMitglied(ActionEvent event) {
        scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMitgliderHinzufügen.fxml"));
            
            
        try {
            root2 = (Parent) loader.load();
            scene.setRoot(root2);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionEntfernen(ActionEvent event) {
        scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MitgliederEntfernen.fxml"));
            
        try {
            root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionEinstellungen(ActionEvent event) {
        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EinstellungenView.fxml"));
            root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}