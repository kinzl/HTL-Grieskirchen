package net.htlgkr.jbaumgartner19;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class MainViewController implements Initializable {

    private BufferedWriter bw;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String fileName;
    
    @FXML
    private MenuItem schließenButton;
    @FXML
    private MenuItem öffnenButton;
    @FXML
    private TextField txtFirstName;
    @FXML
    private Spinner<Integer> txtNetto;
    @FXML
    private Spinner<Integer> txtBrutto;
    @FXML
    private Spinner<Integer> txtHandicap;
    @FXML
    private TextField txtGolfclub;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtId;
    private Label SelectedList;
    private ListView Listview;
    private ObservableList olList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Listview.setItems(olList);

        Listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                SelectedList.setText(newValue.toString());
            }
        });
        
        TextInputDialog dialog = new TextInputDialog("File1");
        dialog.setContentText("Geben Sie den Namen Ihrer Datei ein:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            
        }

        try {
            Socket socket = new Socket("localhost", 4722);
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            bw.write("fileName");
            bw.write(fileName);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SpinnerValueFactory bruSpin = new IntegerSpinnerValueFactory(0, 0);
        txtBrutto.setValueFactory(bruSpin);

        SpinnerValueFactory netSpin = new IntegerSpinnerValueFactory(0, 0);
        txtNetto.setValueFactory(netSpin);

        SpinnerValueFactory handSpin = new IntegerSpinnerValueFactory(-54, 54);
        txtHandicap.setValueFactory(handSpin);
    }

    @FXML
    public void handleSchließenAction(ActionEvent actionEvent) {
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

    @FXML
    private void handleÖffnenAction(ActionEvent event) {

        try {
            bw.write("open");

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
