/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mattl
 */
public class MitgliederEntfernenController implements Initializable {

    @FXML
    private TextField IDid;
    @FXML
    private TextField vornameID;
    @FXML
    private Button abbrechenAktion;
    @FXML
    private TextField nachnameID;
    @FXML
    private Button goID;
    private Parent root;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private Map<String, String[]> timeMap;
    private List<Player> list = new ArrayList<>();

    ;
    @FXML
    private void backAktion(ActionEvent event) {
        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
            Parent root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    private void goAktion(ActionEvent event) {

        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream("Mitglieder.xml")));
            list = (List<Player>) d.readObject();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewMitgliderHinzufügenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (list.contains(vornameID.getText()) && list.contains(nachnameID.getText()) && list.contains(IDid.getText())) {
            list.remove(vornameID.getText());
            list.remove(nachnameID.getText());
            list.remove(IDid.getText());
        }
        XMLEncoder e;
        try {
            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Mitglieder.xml")));
            e.writeObject(list);
            e.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewMitgliderHinzufügenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            scene = Projekt_Mattle_Augustin.stage2.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
            Parent root = (Parent) loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
