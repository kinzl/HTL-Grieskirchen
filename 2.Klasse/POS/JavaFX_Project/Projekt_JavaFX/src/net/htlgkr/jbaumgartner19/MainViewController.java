package net.htlgkr.jbaumgartner19;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class MainViewController implements Initializable {

    private BufferedWriter bw;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String fileName;
    private Label SelectedList;
    private ListView Listview;
    private ObservableList olList = FXCollections.observableArrayList();
    
    @FXML
    private MenuItem schließenButton;
    @FXML
    private MenuItem öffnenButton;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtGolfclub;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtId;
    @FXML
    private Spinner<Integer> txtHandicap;
    @FXML
    private Spinner<Integer> txtBrutto;
    @FXML
    private Spinner<Integer> txtNetto;
    @FXML
    private ListView<Integer> listView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    public void handleSchließenAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void handleÖffnenAction(ActionEvent event) {
        try {
            bw.write("open");

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        int netto = txtNetto.getValue();
        int brutto = txtBrutto.getValue();
        int handicap = txtHandicap.getValue();
        String golfclub = txtGolfclub.getText();
        String id = txtId.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || golfclub.isEmpty() || id.isEmpty()) {
                
        } else {
            
            HashSet hashSet = new HashSet();
            hashSet.add(firstName);
            hashSet.add(lastName);
            hashSet.add(netto);
            hashSet.add(brutto);
            hashSet.add(handicap);
            hashSet.add(golfclub);
            olList.add(hashSet);
            Listview.setItems(olList);
            try {

                bw.write("newTournament");
                out.writeObject(hashSet);
                out.flush();
                bw.write(id);
                bw.flush();

            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
