package wu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Teo
 */
public class Oceny_DziekanatController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }  
    
    @FXML
     private void assesmentDziekanat_menuActionButton(ActionEvent event) throws IOException {
    
        Parent assessment_page_parent = FXMLLoader.load(getClass().getResource("Dziekanat_window.fxml"));
        Scene assessment_page_scene = new Scene(assessment_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(assessment_page_scene);
        app_stage.show();        
     }
     
      @FXML
    private void assesment_logoutActionButton(ActionEvent event) throws IOException {

        Parent logout_page_parent = FXMLLoader.load(getClass().getResource("Login_window.fxml"));
        Scene logout_page_scene = new Scene(logout_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(logout_page_scene);
        app_stage.show();             
    }
    
     @FXML
    private void generate_dziekanatButtonAction(ActionEvent event) throws IOException {
            
    }
    
     @FXML
    private void add_dziekanatButtonAction(ActionEvent event) throws IOException {
            
    }
    
    @FXML
    private void edit_dziekanatButtonAction(ActionEvent event) throws IOException {
            
    }
    
    @FXML
    private void delete_dziekanatButtonAction(ActionEvent event) throws IOException {
            
    }
       
}