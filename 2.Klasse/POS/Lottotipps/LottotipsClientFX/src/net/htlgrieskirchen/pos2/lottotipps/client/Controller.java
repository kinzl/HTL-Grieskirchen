/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.lottotipps.client;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author kimme
 */
public class Controller implements Initializable {

    @FXML
    private TextField textFieldAddress;
    @FXML
    private ComboBox<Integer> comboBoxNumberOfTips;
    @FXML
    private ListView<Tip> listViewTips;
    ObservableList<Tip> ol = FXCollections.observableArrayList();
    
    private boolean isSaved = true;
    private Model model;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
        comboBoxNumberOfTips.setItems(FXCollections.observableList(Arrays.asList(1,2,3,4,5,6,7,8,9)));
        listViewTips.setItems(ol);
    }    

    @FXML
    private void handleMenuItemLoad(ActionEvent event) {
        try {
            model.loadTips();
            ol.setAll(model.getTips());
        } catch (RuntimeException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    private void handleMenuItemSave(ActionEvent event) {
        try {
            model.saveTips();
            isSaved = true;
        } catch (RuntimeException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    private void handleMenuItemQuit(ActionEvent event) {
        if (!isSaved) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Änderungen speichern?");
            alert.setHeaderText("Änderungen speichern?");
            alert.setContentText("Wollen Sie Ihre Änderungen speichern?");
            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeNo = new ButtonType("Nein");
            ButtonType buttonTypeCancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes){
                model.saveTips();
                Platform.exit();
            } else if (result.get() == buttonTypeNo) {
                Platform.exit();
            }
        } else {
            Platform.exit();
        }
    }

    @FXML
    private void handleComboBoxAction(ActionEvent event) {
        if (textFieldAddress.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText(null);
            alert.setContentText("Bitte geben Sie die Adresse ein.");

            alert.showAndWait();
            
            textFieldAddress.requestFocus();
            return;
        }
        model.requestTipsFromServer(textFieldAddress.getText(), comboBoxNumberOfTips.getValue());
        ol.setAll(model.getTips());
        isSaved = false;
    }
    
}
