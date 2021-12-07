/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golfclubprojekt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kinzl
 */
public class MainMenueViewController implements Initializable {

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
    private ListView<String> listView;
    @FXML
    private Text errorMessage;
    @FXML
    private MenuItem öffnenButton1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Socket socket = new Socket("localhost", 4722);
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(MainMenueViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SpinnerValueFactory bruSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0);

        txtBrutto.setValueFactory(bruSpin);

        SpinnerValueFactory netSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0);
        txtNetto.setValueFactory(netSpin);

        SpinnerValueFactory handSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(-54, 54);
        txtHandicap.setValueFactory(handSpin);
    }

    @FXML
    private void handleSchließenAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void handleÖffnenAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("File1");
        dialog.setContentText("Geben Sie den Namen Ihrer Datei ein:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

        }

        try {
            bw.write("open");
            bw.flush();
            bw.write(String.valueOf(result));
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(MainMenueViewController.class.getName()).log(Level.SEVERE, null, ex);
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

        String toString = firstName + " " + lastName + "; Netto: " + netto + "; Brutto: " + brutto + "; Handicap: " + handicap + " | ID: " + id;

        if (firstName.isEmpty() || lastName.isEmpty() || golfclub.isEmpty() || id.isEmpty()) {
            errorMessage.setFill(Color.RED);
            errorMessage.setText("Bitte alle Felder ausfüllen");
        } else {

            
            HashSet hashSet = new HashSet();
            hashSet.add(firstName);
            hashSet.add(lastName);
            hashSet.add(netto);
            hashSet.add(brutto);
            hashSet.add(handicap);
            hashSet.add(golfclub);

            olList.setAll(hashSet.toString());
            listView.setItems(olList);
            System.out.println("Lebakassemmal");    
            try {
                bw.write("newTournament");
                bw.flush();
                bw.write(id);
                bw.flush();
                out.writeObject(hashSet);
                out.flush();
                Map<String, HashSet> map = (Map<String, HashSet>) in.readObject();
                
                olList.setAll(map.toString());
                
            } catch (IOException ex) {
                Logger.getLogger(MainMenueViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainMenueViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void removePlayer(ActionEvent event) {
        if(!txtId.getText().isEmpty()){
            try {
                bw.write("removePlayer");
                bw.write(txtId.getText());
            } catch (IOException ex) {
                Logger.getLogger(MainMenueViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            errorMessage.setFill(Color.RED);
            errorMessage.setText("Bitte die Id eingeben");
        }
    }

    @FXML
    private void newTournament(ActionEvent event) {
    }
}
