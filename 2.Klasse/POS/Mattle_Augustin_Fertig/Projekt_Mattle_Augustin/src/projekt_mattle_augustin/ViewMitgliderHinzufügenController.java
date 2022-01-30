package projekt_mattle_augustin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mattl
 */
public class ViewMitgliderHinzufügenController implements Initializable {

    @FXML
    private TextField vornameid;
    @FXML
    private TextField nachnameid;
    @FXML

    private TextField IDid;
    private Scene scene;
    private String[] name = new String[2];
    private Parent root;
    private Map<String, String[]> hmap = new TreeMap<>();
    private Stage stage;
    @FXML
    private ListView<Player> mitgliederListe;
    private  List<Player> list = new ArrayList<>();;
    public ObservableList<Player> observableList = FXCollections.observableArrayList();
    private Socket socket;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player p = new Player();
        mitgliederListe.setItems(observableList);
        
       
    }
    
    @FXML
    private void hinzufügenAktion(ActionEvent event) {
        int i = 0;
        int i2 = 1;
        name[i] = vornameid.getText();
        name[i2] = nachnameid.getText();
        int id = Integer.parseInt(IDid.getText());
        list.add(new Player(vornameid.getText(), nachnameid.getText(), id));
        observableList.setAll(list);
        Projekt_Mattle_Augustin.observablelist = observableList;
        hmap.put(IDid.getText(), name);
        

        

        
    }
//    public void saveTips() {
//        if (hmap == null || hmap.isEmpty()) throw new RuntimeException("Keine Tips vorhanden.");
//        try (PrintWriter pw = new PrintWriter("Mitglieder")) {
//            for (String string : hmap.keySet()) {
//                
//            }
//            
//            for(String tip : hmap) {
//                pw.println(tip);
//            }
//        } catch (FileNotFoundException ex) {
//            throw new RuntimeException("Datei konnte nicht gespeichert werden.");
//        }
//    }

    @FXML
    private void backAktion(ActionEvent event) {
//        try {
//         FileOutputStream fileOut =
//         new FileOutputStream("Mitglieder.ser");
//         ObjectOutputStream out2 = new ObjectOutputStream(fileOut);
//         out2.writeObject(list);
//         out2.close();
//         fileOut.close();
//         System.out.printf("Serialized data is saved in Mitglider.ser");
//         
//      } catch (IOException i) {
//         i.printStackTrace();
//      }
   
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

    public void setPrimaryStage2(Stage stage) {
        this.stage = stage;

    }

    public ListView<Player> getMitgliederListe() {
        return mitgliederListe;
    }

    public void setMitgliederListe(ListView<Player> mitgliederListe) {
        this.mitgliederListe = mitgliederListe;
    }

    public List<Player> getList() {
        return list;
    }

    public void setList(List<Player> list) {
        this.list = list;
    }

    public ObservableList<Player> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<Player> observableList) {
        this.observableList = observableList;
    }

    public TextField getVornameid() {
        return vornameid;
    }

    public void setVornameid(TextField vornameid) {
        this.vornameid = vornameid;
    }

    public TextField getNachnameid() {
        return nachnameid;
    }

    public void setNachnameid(TextField nachnameid) {
        this.nachnameid = nachnameid;
    }

    public TextField getIDid() {
        return IDid;
    }

    public void setIDid(TextField IDid) {
        this.IDid = IDid;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Map<String, String[]> getHmap() {
        return hmap;
    }

    public void setHmap(Map<String, String[]> hmap) {
        this.hmap = hmap;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void ladenAktion(ActionEvent event) {
        list.clear();
        try {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream("Mitglieder.xml")));
            list = (List<Player>) d.readObject();
            observableList.addAll(list);
            
            
                   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewMitgliderHinzufügenController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//         FileInputStream fileIn = new FileInputStream();
//         ObjectInputStream in = new ObjectInputStream(fileIn);
//         list = (List<Player>) in.readObject();
//         observableList.setAll(list);
//         in.close();
//         fileIn.close();
//      } catch (IOException i) {
//         i.printStackTrace();
//         return;
//      } catch (ClassNotFoundException c) {
//         System.out.println("Mitglieder class not found");
//         c.printStackTrace();
//         return;
      }
    
}
