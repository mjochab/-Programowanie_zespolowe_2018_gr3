/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wu;

import Connection.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Klasa obsługująca menu logowania
 * @author Zbyszek
 */

public class FXMLDocumentController implements Initializable {
    
    /**
     * Przyciski, pola oraz labele występujące w klasie
     */
    
    @FXML
    private Label login,haslo,label ;
    @FXML
    private Button button;
    @FXML
    private TextField loginTekst;
    @FXML
    private PasswordField hasloTekst;
    
    
    /**
     * Metoda obsługująca przycisk "loguj"
     * @param event
     * @throws Exception 
     */
    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception {
        
        
        
        Parent student_window = FXMLLoader.load(getClass().getResource("Student_window.fxml"));
        Scene student_window_scene = new Scene(student_window);
        Stage app_stage_student_window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent admin_window = FXMLLoader.load(getClass().getResource("Admin_window.fxml"));
        Scene admin_window_scene = new Scene(admin_window);
        Stage app_stage_admin_window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        
        if (isValidCredentialsStudent())
            {
                app_stage_student_window.hide(); //optional
                app_stage_student_window.setScene(student_window_scene);
                app_stage_student_window.show();  
            }
        
         if (isValidCredentialsAdmin())
            {
                app_stage_admin_window.hide(); //optional
                app_stage_admin_window.setScene(admin_window_scene);
                app_stage_admin_window.show();  
            }
        
        else
            {
                loginTekst.clear();
                hasloTekst.clear();
            }
        
       
        
        
//        Parent student_window = FXMLLoader.load(getClass().getResource("Student_window.fxml"));
//        Scene student_window_scene = new Scene(student_window);
//        Stage app_stage_student_window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        if((loginTekst.getText().equals("student"))&& (hasloTekst.getText().equals("student"))) 
//        {
//                
//                app_stage_student_window.hide(); //optional
//                app_stage_student_window.setScene(student_window_scene);
//                app_stage_student_window.show();
//       
//        }
//      
//        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Admin_window.fxml"));
//        Scene home_page_scene = new Scene(home_page_parent);
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        if((loginTekst.getText().equals("admin"))&& (hasloTekst.getText().equals("admin"))) 
//        {
//                
//                app_stage.hide(); //optional
//                app_stage.setScene(home_page_scene);
//                app_stage.show();
//       
//        }
//        else {
//   
//       }
    
    }
    /**
     * Metoda sprawdzająca czy dane logowania studenta są poprawne
     * @return zwraca true jeśli dane są poprawne, a jeśli nie to false
     */
     public boolean isValidCredentialsStudent()
    {
        boolean let_in = false;
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        
        try {
            Statement statement=connection.createStatement();           
            String sql="SELECT * FROM studenci WHERE login_s = '"+loginTekst.getText()+"' AND haslo_s = '"+hasloTekst.getText()+"';";
            ResultSet rs=statement.executeQuery(sql);
            
            
                
            while ( rs.next() ) {
                 if (rs.getString("login_s") != null && rs.getString("haslo_s") != null) { 
                     String  username = rs.getString("login_s");
                     System.out.println( "login = " + username );
                     String password = rs.getString("haslo_s");
                     System.out.println("haslo = " + password);
                     let_in = true;
                 }  
            }
            rs.close();
            statement.close();
            connection.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("isValidCredentials operation done successfully");
            return let_in;
    
    }
     
     /**
      * Metoda sprawdzająca czy dane logowania admina są poprawne
      * @return zwraca true jeśli dane są poprawne, a jeśli nie to false
      */
     public boolean isValidCredentialsAdmin()
    {
        boolean let_in = false;
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        
        try {
            Statement statement=connection.createStatement();           
            String sql="SELECT * FROM pracownicy WHERE login_p = '"+loginTekst.getText()+"' AND haslo_p = '"+hasloTekst.getText()+"';";
            ResultSet rs=statement.executeQuery(sql);
            
            
                
            while ( rs.next() ) {
                 if (rs.getString("login_p") != null && rs.getString("haslo_p") != null) { 
                     String  username = rs.getString("login_p");
                     System.out.println( "login = " + username );
                     String password = rs.getString("haslo_p");
                     System.out.println("haslo = " + password);
                     let_in = true;
                 }  
            }
            rs.close();
            statement.close();
            connection.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("isValidCredentials operation done successfully");
            return let_in;
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     

}
