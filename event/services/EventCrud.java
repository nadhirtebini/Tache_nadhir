/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.services;

import event.utils.MyConnection;
import event.entities.Evennement;
import java.awt.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class EventCrud {
    public void ajouterEvent(Evennement E){
        LocalDate Date = E.getDate();
        
         try {
             
            String requete = "INSERT INTO test(id,nom, Lieu,date)"
                    + "VALUES (0,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                                    .prepareStatement(requete);
           
            pst.setString(1, E.getNom());
            pst.setString(2, E.getLieu());
            
            java.sql.Date sqlDate =  java.sql.Date.valueOf(Date);
            pst.setDate(3,sqlDate);
            
            
           
             
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    public void modifierEvent(Evennement E) {
  int rowsUpdated =0;
        try {
        
        String requete = "UPDATE test SET nom = ?, Lieu = ?, date = ? WHERE id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, E.getNom());
        pst.setString(2, E.getLieu());
        LocalDate newDate = E.getDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf(newDate);
        pst.setDate(3, sqlDate);
        // Assurez-vous de définir l'ID de l'événement que vous souhaitez mettre à jour
        pst.setInt(4, E.getid()); // Supposons que getId() renvoie l'ID de l'événement

        rowsUpdated = pst.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("L'événement a été modifié avec succès.");
        } else {
            System.out.println("Aucun événement n'a été modifié.");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification de l'événement : " + ex.getMessage());
        ex.printStackTrace();
    }
    System.out.println(rowsUpdated);
}
public void supprimerEvent(int eventId) {
    try {
        String requete = "DELETE FROM test WHERE id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        
        // Assurez-vous de définir l'ID de l'événement que vous souhaitez supprimer
        pst.setInt(1, eventId);

        int rowsDeleted = pst.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("L'événement a été supprimé avec succès.");
        } else {
            System.out.println("Aucun événement n'a été supprimé (ID non trouvé).");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    
  //   public List<Evennement> listeDesEntites() {
    //     List<Evennement> myList = new ArrayList<>();
      //  try {
        //    String requete = "SELECT * FROM personne";
          //  Statement st = MyConnection.getInstance().getCnx()
           //         .createStatement();
           // ResultSet rs = st.executeQuery(requete);
           // while(rs.next()){
             //   Evennement E = new Evennement();
                
               // E.setNom(rs.getString("nom"));
                //E.setDate(3,sqlDate);
               // myList.add(E);
           // }
       // } catch (SQLException ex) {
         //   System.out.println(ex.getMessage());
        //}
        //return myList;
    //}
}
    
    
    
