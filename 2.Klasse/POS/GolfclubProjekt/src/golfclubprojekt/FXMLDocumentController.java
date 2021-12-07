/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golfclubprojekt;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author kinzl
 */
public class FXMLDocumentController implements Initializable {

    private int versuche = 3;
    private Stage stage;
    @FXML
    private PasswordField apiField;
    @FXML
    private Button loginButton;
    @FXML
    private Button beendenButton;
    @FXML
    private Text errorMessage;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void LoginAction(ActionEvent event) {
        if (versuche != 0) {
            if (apiField.getText().isEmpty()) {
                errorMessage.setFill(Color.RED);
                errorMessage.setText("Bitte alle Felder ausfüllen!");
            } else {
                //request API key from Server
                MyServer server = new MyServer();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        server.connect();
                    }
                });
                thread.start();  

                try (
                        Socket socket = new Socket("localhost", 4722);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
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
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        errorMessage.setFill(Color.RED);
                        errorMessage.setText("Der API-Key ist falsch");
                        errorMessage.setText("Sie haben noch " + versuche + " Versuche");
                        --versuche;
                        System.out.println(array[i]);
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            errorMessage.setFill(Color.RED);
            errorMessage.setText("Sie haben keine Versuche mehr");
            Platform.exit();
        }
    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;

    }
    
    @FXML
    private void handleExitAction(ActionEvent event) {
        Platform.exit();
    }
    
}
