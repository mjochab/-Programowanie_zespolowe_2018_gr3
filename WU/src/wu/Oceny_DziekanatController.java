package wu;

import Connection.ConnectionClass;
import com.itextpdf.text.DocumentException;
import generatorpdf.Raporty;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Klasa odpowiedzialna za wyswietlenie okna Oceny_Dziekanat
 *
 * @author Teo
 */
public class Oceny_DziekanatController implements Initializable {

    
      @FXML
    private TableView<Oceny> table_oceny;
    @FXML
    private TableColumn<Oceny, String> columnProwadzacy;
    @FXML
    private TableColumn<Oceny, String> columnPrzedmiot;
    @FXML
    private TableColumn<Oceny, Integer> columnOcena;
    @FXML
    private TableColumn<Oceny, String> columnStudent;
    
    ConnectionClass PolaczenieDB = new ConnectionClass();

    Connection sesja = PolaczenieDB.getConnection();
    private ObservableList<Oceny> data;
    @FXML
    private Button assesment_menu;
    @FXML
    private ComboBox<Pracownicy> comboProwadzacy;
    @FXML
    private ComboBox<Przedmioty> comboPrzedmiot;
    @FXML
    private ComboBox comboOcena;
    @FXML
    private ComboBox<Student> comboStudent;

    ObservableList<Pracownicy> dataPracownicy;
    ObservableList<Przedmioty> dataPrzedmioty;
    ObservableList<Student> dataStudent;
    private Button add_wykladowca;
    @FXML
    private Button delete_wykładowca;
    @FXML
    private Button generate_dziekanat;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    // combox oceny
        comboOcena.getItems().addAll(
                "2",
                "3",
                "4",
                "5"
        );
        dataPracownicy = FXCollections.observableArrayList();
        dataPrzedmioty = FXCollections.observableArrayList();
        dataStudent = FXCollections.observableArrayList();

        data = FXCollections.observableArrayList();

        Statement stmt = null;

        try {

            stmt = sesja.createStatement();

            ResultSet rs = stmt.executeQuery("Select id_oceny,CONCAT(imie_s,\" \",nazwisko_s) as student,CONCAT(imie_p,\" \",nazwisko_p) as prowadzacy ,ocena, nazwa_przedmiotu from oceny,pracownicy,przedmioty,studenci where oceny.id_przedmiotu=przedmioty.id_przedmiotu and oceny.id_studenta=studenci.id_studenta and oceny.id_pracownika=pracownicy.id_pracownika;");

            //System.out.println("Dane:"+ rs.getString(2));
            while (rs.next()) {
                data.add(new Oceny(rs.getInt(1),rs.getInt(4), rs.getString(5), rs.getString(2), rs.getString(3)));

            }

            columnStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
            columnOcena.setCellValueFactory(new PropertyValueFactory<>("ocena"));
            columnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("nazwa_przedmiotu"));
            columnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("prowadzacy"));
            table_oceny.setItems(null);
            table_oceny.setItems(data);

        } catch (Exception e) {

        }

        // wyswietlanie combox pracownicy
        Statement stmt2 = null;

        try {

            stmt2 = sesja.createStatement();

            ResultSet rs = stmt2.executeQuery("SELECT * from pracownicy;");

            //System.out.println("Dane:"+ rs.getString(2));
            while (rs.next()) {

                dataPracownicy.add(new Pracownicy(rs.getInt(1), rs.getString(2), rs.getString(3)));

            }

        } catch (Exception e) {

        }

        comboProwadzacy.setItems(null);
        comboProwadzacy.setItems(dataPracownicy);

        comboProwadzacy.setCellFactory(new Callback<ListView<Pracownicy>, ListCell<Pracownicy>>() {

            @Override
            public ListCell<Pracownicy> call(ListView<Pracownicy> p) {

                final ListCell<Pracownicy> cell = new ListCell<Pracownicy>() {

                    @Override
                    protected void updateItem(Pracownicy t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getimie_p() + " " + t.getnazwisko_p());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        comboProwadzacy.setConverter(new StringConverter<Pracownicy>() {
            @Override
            public String toString(Pracownicy object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getimie_p() + " " + object.getnazwisko_p();
                }
            }

            @Override
            public Pracownicy fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //KONIEC pracownicy
        // wyswietlanie combox przedmioty
        Statement stmt3 = null;

        try {

            stmt3 = sesja.createStatement();

            ResultSet rs = stmt3.executeQuery("SELECT * from przedmioty;");

            //System.out.println("Dane:"+ rs.getString(2));
            while (rs.next()) {

                dataPrzedmioty.add(new Przedmioty(rs.getInt(1), rs.getString(2)));

            }

        } catch (Exception e) {

        }

        comboPrzedmiot.setItems(null);
        comboPrzedmiot.setItems(dataPrzedmioty);

        comboPrzedmiot.setCellFactory(new Callback<ListView<Przedmioty>, ListCell<Przedmioty>>() {

            @Override
            public ListCell<Przedmioty> call(ListView<Przedmioty> p) {

                final ListCell<Przedmioty> cell = new ListCell<Przedmioty>() {

                    @Override
                    protected void updateItem(Przedmioty t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getnazwa_przedmiotu());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        comboPrzedmiot.setConverter(new StringConverter<Przedmioty>() {
            @Override
            public String toString(Przedmioty object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getnazwa_przedmiotu();
                }
            }

            @Override
            public Przedmioty fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //KONIEC przedmioty
        // wyswietlanie combox student
        Statement stmt4 = null;

        try {

            stmt4 = sesja.createStatement();

            ResultSet rs = stmt4.executeQuery("SELECT * from studenci;");

            //System.out.println("Dane:"+ rs.getString(2));
            while (rs.next()) {

                dataStudent.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));

            }

        } catch (Exception e) {

        }

        comboStudent.setItems(null);
        comboStudent.setItems(dataStudent);

        comboStudent.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {

            @Override
            public ListCell<Student> call(ListView<Student> p) {

                final ListCell<Student> cell = new ListCell<Student>() {

                    @Override
                    protected void updateItem(Student t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getimie_s() + " " + t.getnazwisko_s());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        comboStudent.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getimie_s() + " " + object.getnazwisko_s();
                }
            }

            @Override
            public Student fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //KONIEC student
        
        
    } 
    
    /**
     * Metoda odpowiedzialna za ładowanie sceny z oknem dziekanatu
     * @param event
     * @throws IOException 
     */
    @FXML
     private void assesmentDziekanat_menuActionButton(ActionEvent event) throws IOException {
    
        Parent assessment_page_parent = FXMLLoader.load(getClass().getResource("Dziekanat_window.fxml"));
        Scene assessment_page_scene = new Scene(assessment_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(assessment_page_scene);
        app_stage.show();        
     }
     
     /**
      * Metoda odpowiedzialna za ładowanie sceny Login_window 
      * @param event
      * @throws IOException 
      */
    private void assesment_logoutActionButton(ActionEvent event) throws IOException {

        Parent logout_page_parent = FXMLLoader.load(getClass().getResource("Login_window.fxml"));
        Scene logout_page_scene = new Scene(logout_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(logout_page_scene);
        app_stage.show();             
    }
    
    /**
     * Metoda odpowiedzialna za generowanie pdf dla dziekanatu 
     * @param event
     * @throws IOException 
     */
     @FXML
    private void generate_dziekanatButtonAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, DocumentException {
        
         Raporty rap = new Raporty();
        Raporty.rs = Raporty.executeDefaultQuery();
        Raporty.rs.first();
        Raporty.savePdf();
        Raporty.document = Raporty.setDocumentInfo(Raporty.document, "autor", "title ", "language", "creator");
        Raporty.document.open();
        Raporty.document.add(Raporty.setHeaderTab());
        Raporty.document.add(Raporty.setInfoTable(Raporty.setInfoCell("Nadawca", "Grupa ", "Numer 3"), Raporty.setInfoCell("Odbiorca", "UR", "Wydział Matematyczno - Przyrodniczy")));
        Raporty.document.add(Raporty.setItemTable());
        Raporty.document.close();
            
    }
    
    
    @FXML
    private void comboPDziekanatBtn(ActionEvent event) throws IOException {
            
    }
    
    @FXML
    private void comboPrzDziekanatBtn(ActionEvent event) throws IOException {
            
    }
    
    @FXML
    private void comboODziekanatBtn(ActionEvent event) throws IOException {
            
    }
    
    @FXML
    private void comboSDziekanatBtn(ActionEvent event) throws IOException {
            
    }

    @FXML
    private void add_wykladowcaButtonAction(ActionEvent event) {
        
        
         Statement stmt = null;

        try {

            stmt = sesja.createStatement();
 
            stmt.executeUpdate("INSERT INTO `oceny` (`id_oceny`, `id_przedmiotu`, `id_studenta`, `id_pracownika`,`ocena`) VALUES (null,'" + comboPrzedmiot.getSelectionModel().getSelectedItem().getid_przedmiotu() + "','" + comboStudent.getSelectionModel().getSelectedItem().getid_studenta() + "','" + comboProwadzacy.getSelectionModel().getSelectedItem().getid_pracownika() + "','" + comboOcena.getSelectionModel().getSelectedItem().toString() +  "');");

            Parent assessment_page_parent = FXMLLoader.load(getClass().getResource("Oceny_Admin.fxml"));
            Scene assessment_page_scene = new Scene(assessment_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(assessment_page_scene);
            app_stage.show();
            // cIPracownik.getSelectionModel().getSelectedItem().getid_pracownika()
        } catch (Exception e) {

        }
    }

    @FXML
    private void delete_wykładowcaButtonAction(ActionEvent event) {
        
         int id = (table_oceny.getSelectionModel().getSelectedItem().getid_oceny());

        try {

            PreparedStatement statement = sesja.prepareStatement("DELETE FROM oceny WHERE id_oceny = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            Parent assessment_page_parent = FXMLLoader.load(getClass().getResource("Oceny_Admin.fxml"));
            Scene assessment_page_scene = new Scene(assessment_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(assessment_page_scene);
            app_stage.show();
        } catch (Exception e) {

        }
        
        
    }
       
}
