/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.gui;

import event.entities.Evennement;
import event.services.EventCrud;
import event.utils.MyConnection;
import static event.utils.MyConnection.getInstance;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.internal.runtime.Debug.id;
import static org.omg.CORBA.AnySeqHelper.id;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventController implements Initializable {

    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfnom;
    @FXML
    private ComboBox<String> tfcombo;
    private String[] typee ={"aaaa","bbbb","cccc"};
    @FXML
    private Button ajoutbtn;
    @FXML
    private Button modifbtn;
    @FXML
    private Button supprimbtn;
    @FXML
    private TableView<Evennement> view_event;
    @FXML
    private TableColumn<Evennement, String> nom_column;
    @FXML
    private TableColumn<Evennement, String> lieu_column;
    @FXML
    private TableColumn<Evennement, String> date_column;
     String query = null;
    MyConnection cnx = getInstance();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Evennement evn = null;
    EventCrud EC=new EventCrud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfcombo.getItems().addAll("Lieu 1", "Lieu 2", "Lieu 3");
         try {
            ShowEvennement();
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }    

    @FXML
    private void ajouterEvent(ActionEvent event) {
      
        String nom = tfnom.getText();
        String lieu = (String) tfcombo.getValue();
        LocalDate date = tfdate.getValue();
        Evennement E = new Evennement(1,nom,lieu,date);
        EventCrud ev = new EventCrud();
        ev.ajouterEvent(E);
        try {
            ShowEvennement();
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
        Evennement e =view_event.getSelectionModel().getSelectedItem();
          String nom = tfnom.getText();
        String lieu = (String) tfcombo.getValue();
        LocalDate date = tfdate.getValue();
        Evennement E = new Evennement(e.getid(),nom,lieu,date);
       
      
        EC.modifierEvent(E);
       
        try {
            ShowEvennement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {
        Evennement e;
      e=view_event.getSelectionModel().getSelectedItem();
        EC.supprimerEvent(e.getid());
        try {
            ShowEvennement();
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void ShowEvennement () throws SQLException {
         
        
         ObservableList< Evennement> Eventlist =getEventlist() ;
       
       
          
       
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieu_column.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        view_event.setItems(Eventlist);
    
    
}
    private ObservableList<Evennement> getEventlist() throws SQLException {
         
          
      ObservableList<Evennement> eventlist =FXCollections.observableArrayList();
     
   
     String query = "SELECT * FROM test";
     
      try {
          PreparedStatement pst = MyConnection.getInstance().getCnx()
                                    .prepareStatement(query);
           
              ResultSet rs =pst.executeQuery(query);
            
            while (rs.next()){
                eventlist.add(new Evennement(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("lieu"),
                        rs.getDate("date").toLocalDate()));
                
                      
            }
      }catch (SQLException e) {
            e.printStackTrace();
        }
     
        return eventlist;
         
     }
     

  
    
}
