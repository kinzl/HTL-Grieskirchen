/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bmattle19
 */
public class ViewLoginController implements Initializable {

    private TextArea nachnameID;
    private TextArea vornameID;
    @FXML
    private Button LoginButton;
    @FXML
    private Button LoginButton1;
    @FXML
    private PasswordField passwortID;
    String pass = "localhost";
    private Stage stage;

    private PrintWriter out;
    @FXML
    private TextArea clubID;

    /**
     * Initializes the controller class.
     */
    Model m = new Model();
    public Map<String, String> clubs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginButton.setDisable(true);
        m.connectToServer(pass);
        clubs = m.getMap();
        
            if ((passwortID.getText() != null) && (clubID.getText() != null)) {
                LoginButton.setDisable(false);
                
            }
        
    }

    public Map<String, String> getClubs() {
        return clubs;
    }

    public void setClubs(Map<String, String> clubs) {
        this.clubs = clubs;
    }

    @FXML
    private void handleLogin(ActionEvent event) {

        clubs.keySet().stream().map(string -> string).forEachOrdered(key -> {
            String val = clubs.get(key);
            String timePass = passwortID.getText();
            String timeClub = clubID.getText();
            if (key.equals(timeClub) && val.equals(timePass)) {
                try {
                    Scene scene = stage.getScene();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
                    Parent root = (Parent) loader.load();
                    scene.setRoot(root);
                    System.out.println("Login Geschaft");
                } catch (IOException ex) {
                    Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);

                }
            } else {
                System.out.println(clubs.size());
            }
        });

    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;

    }

    @FXML
    private void handleBreak(ActionEvent event) {
        Platform.exit();
    }
}
