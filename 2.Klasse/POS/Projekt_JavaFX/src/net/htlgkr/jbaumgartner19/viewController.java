/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgkr.jbaumgartner19;

import com.sun.jndi.dns.DnsContextFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jbaumgartner19
 */
public class viewController implements Initializable {

    private int versuche = 3;
    @FXML
    private PasswordField apiField;
    @FXML
    private Button loginButton;
    @FXML
    private Button beendenButton;


    private Stage stage;

    @FXML
    private Text errorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void LoginAction(ActionEvent event) {

        if (versuche != 0) {
            if ( apiField.getText().isEmpty()) {
                errorMessage.setFill(Color.RED);
                errorMessage.setText("Bitte alle Felder ausfüllen!");
            } else {
                //request API key from Server
                Server server = new Server();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        server.connect();
                    }
                });
                thread.start();

                try (
                        Socket socket = new Socket("localhost", 4641);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ) {

                    bw.write("requestApiKey");
                    bw.newLine();
                    bw.flush();
                    Object o = in.readObject();
                    while (o == null) {
                        o = in.readObject();
                    }
                    String[] array = null;
                    if (o instanceof String[]) {
                        array = (String[]) o;
                    }

                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals(apiField.getText())) {
                            try {
                                Scene scene = stage.getScene();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenueView.fxml"));
                                Parent root = (Parent) loader.load();
                                scene.setRoot(root);

                            } catch (IOException ex) {
                                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        errorMessage.setFill(Color.RED);
                        errorMessage.setText("Der API-Key ist falsch");
                        errorMessage.setText("Sie haben noch " + versuche + " Versuche");
                        --versuche;
                        System.out.println(array[i]);
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(viewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            errorMessage.setFill(Color.RED);
            errorMessage.setText("Sie haben keine Versuche mehr");
        }

    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;

    }

}
