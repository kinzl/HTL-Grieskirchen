/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_mattle_augustin;

import java.util.List;
import java.util.Observable;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author bmattle19
 */
public class Projekt_Mattle_Augustin extends Application {
    public static Stage stage2;
    
    public static ObservableList<Player> observablelist = FXCollections.observableArrayList();;
    public static TurnierViewController turnierControler;
    public static ViewLoginController golfname;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage2 = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewLogin.fxml"));
        Parent root = (Parent) loader.load();
        ViewLoginController controller_1 = (ViewLoginController) loader.getController();
        
        controller_1.setPrimaryStage(stage);
        
        Scene scene = new Scene(root);
        stage2.setTitle("Login Screen");
        stage2.setScene(scene);
        stage2.show();
        
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
        
    }

}
