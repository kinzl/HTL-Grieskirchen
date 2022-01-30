/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mattl
 */
public class TurnierViewController implements Initializable {

    @FXML
    private ListView<Player> alleMitspiler;
    @FXML
    private ListView<Player> alteMitspieler;

    private Parent root;

    private Scene scene;

    ObservableList<Player> observableList2 = FXCollections.observableArrayList();
    @FXML
    private TextField namedesTurniers;
    @FXML
    private TextField LöscherID;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                 String turnierName = namedesTurniers.getText();
                 String löcher = LöscherID.getText();
                observableList2 = Projekt_Mattle_Augustin.observablelist;
                alteMitspieler.setItems(observableList2);
                alteMitspieler.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
                    @Override
                    public void changed(ObservableValue<? extends Player> ov, Player t, Player t1) {
                        alleMitspiler.getItems().add(t1);
                    }

                });
            }
        });
        
    }

    private void fertigAction(ActionEvent event) {
        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
            root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public ListView<Player> getAlleMitspiler() {
        return alleMitspiler;
    }

    public void setAlleMitspiler(ListView<Player> alleMitspiler) {
        this.alleMitspiler = alleMitspiler;
    }

    public ListView<Player> getAlteMitspieler() {
        return alteMitspieler;
    }

    public void setAlteMitspieler(ListView<Player> alteMitspieler) {
        this.alteMitspieler = alteMitspieler;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ObservableList<Player> getObservableList2() {
        return observableList2;
    }

    public void setObservableList2(ObservableList<Player> observableList2) {
        this.observableList2 = observableList2;
    }

    @FXML
    private void zurückAcktion(ActionEvent event) {
        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
            root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void fertigAcktion(ActionEvent event) {
        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Turnierturnier.fxml"));
            root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }

}
