package sio.gestionmagazine;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sio.gestionmagazine.Model.Article;
import sio.gestionmagazine.Model.Magazine;
import sio.gestionmagazine.Services.ServiceArticle;
import sio.gestionmagazine.Services.ServiceMagazine;
import sio.gestionmagazine.Services.ServicePigiste;
import sio.gestionmagazine.Tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class GestionMagazineController implements Initializable {

    ServiceMagazine serviceMagazine = new ServiceMagazine();
    ServicePigiste servicePigiste = new ServicePigiste();
    ServiceArticle serviceArticle = new ServiceArticle();
    ConnexionBDD cnx;
    @FXML
    private TableColumn tcNomMagazine;
    @FXML
    private TableColumn tcNomSpecialite;
    @FXML
    private TableColumn tcNomPigiste;
    @FXML
    private TableColumn tcNomArticle;
    @FXML
    private TableColumn tcNbPages;
    @FXML
    private TableColumn tcNumeroMagazine;
    @FXML
    private TableView <Article>tvArticles;
    @FXML
    private TableView <Magazine>tvMagazines;
    @FXML
    private TextField txtMontantArticle;
    @FXML
    private TextField txtMontantMagazine;
    @FXML
    private TableColumn tcNumeroArticle;
    @FXML
    private Button btnAjouterArticle;
    @FXML
    private ComboBox cboChoixPigiste;
    @FXML
    private Slider sldNbPages;
    @FXML
    private TextField txtNomArticle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cnx = new  ConnexionBDD();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        };

       serviceMagazine = new ServiceMagazine();
        servicePigiste = new ServicePigiste();
        try {
        tcNumeroMagazine.setCellValueFactory(new PropertyValueFactory<>("idMag"));
        tcNomMagazine.setCellValueFactory(new PropertyValueFactory<>("nomMag"));
        tcNomSpecialite.setCellValueFactory(new PropertyValueFactory<>("nomSpe"));

            tvMagazines.setItems(FXCollections.observableArrayList(serviceMagazine.getAllMagazines()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @FXML
    public void tblMagazinesClicked(Event event) throws SQLException {
        servicePigiste=new ServicePigiste();
        serviceMagazine=new ServiceMagazine();
//        try {
//
//
//            tcNumeroArticle.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
//            tcNomArticle.setCellValueFactory(new PropertyValueFactory<>("titreArticle"));
//            tcNbPages.setCellValueFactory(new PropertyValueFactory<>("nbPages"));
//            tcNomPigiste.setCellValueFactory(new PropertyValueFactory<>("nomPigiste"));
//            tvArticles.setItems(FXCollections.observableArrayList(serviceArticle.getAllArticles()));
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        cboChoixPigiste.setItems(FXCollections.observableArrayList(servicePigiste.getAllPigistes()));

        txtMontantMagazine.setText(String.valueOf(serviceMagazine.getMontantMagazine(
                tvMagazines.getSelectionModel().getSelectedItem().getIdMag()
        )));




    }

    @FXML
    public void tvArticlesClicked(Event event) throws SQLException {


        (Double)txtMontantArticle.setText(serviceArticle.getMontantArticle(tvArticles.getSelectionModel().getSelectedItem().getIdArticle()));
    }

    @FXML
    public void btnAjouterArticleClicked(Event event) throws SQLException {


    }
}