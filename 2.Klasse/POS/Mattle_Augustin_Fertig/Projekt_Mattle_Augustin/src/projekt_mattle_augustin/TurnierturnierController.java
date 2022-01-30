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
public class TurnierturnierController implements Initializable {

    @FXML
    private TextField loch1;
    @FXML
    private TextField loch2;
    @FXML
    private TextField loch3;
    @FXML
    private TextField loch4;
    @FXML
    private TextField loch5;
    @FXML
    private TextField loch6;
    @FXML
    private TextField loch7;
    @FXML
    private TextField loch8;
    @FXML
    private TextField loch9;
    @FXML
    private TextField loch10;
    @FXML
    private TextField loch11;
    @FXML
    private TextField loch12;
    @FXML
    private TextField loch13;
    @FXML
    private TextField loch14;
    @FXML
    private TextField loch15;
    @FXML
    private TextField loch16;
    @FXML
    private TextField loch17;
    @FXML
    private TextField loch18;
    @FXML
    private Label ergebniss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    private Parent root;
    private Scene scene;

    @FXML
    private void berechnenAktion(ActionEvent event) {
        //Hilfe von Lukas!!
        
            String parts = loch1.getText() + " " + loch2.getText() + " " + loch3.getText() + " " + loch4.getText() + " " + loch5.getText() + " " + loch6.getText() + " " + loch7.getText() + " " + loch8.getText() + " " + loch9.getText() + " " + loch10.getText() + " " + loch11.getText() + " " + loch12.getText() + " " + loch13.getText() + " " + loch14.getText() + " " + loch15.getText() + " " + loch16.getText() + " " + loch17.getText() + " " + loch18.getText();
            String[] allParts = parts.split(" ");

            int all = 0;
            int netto = 34 / 18;
            for (int y = 0; y < allParts.length; y++) {
                if (Integer.parseInt(allParts[y]) <= 5 - 3) {
                    all += (5 + netto);
                } else if (Integer.parseInt(allParts[y]) == 5 - 2) {
                    all += (4 + netto);
                } else if (Integer.parseInt(allParts[y]) == 5 - 1) {
                    all += (3 + netto);
                } else if (Integer.parseInt(allParts[y]) == 5) {
                    all += (2 + netto);
                } else if (Integer.parseInt(allParts[y]) == 5 + 1) {
                    all += (1 + netto);
                } else {
                    all += netto;
                }
            }
            ergebniss.setText("Das gesamte Ergebniss ist "+all + "");
            
    }

    @FXML
    private void backAktion(ActionEvent event) {
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
    

