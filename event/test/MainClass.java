/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.test;

import event.entities.Evennement;
import event.services.EventCrud;
import event.utils.MyConnection;
import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class MainClass {
    public static void main(String[] args){
    MyConnection mc = new MyConnection();
    EventCrud ec = new EventCrud();
    Evennement E = new Evennement(1,"aa", "aa", LocalDate.parse("2000-06-10"));
    ec.ajouterEvent(E);
    
    }
    
}
