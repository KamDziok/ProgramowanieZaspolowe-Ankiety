package com.Ankiety_PZ.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Sample Skeleton for 'PanelEdycjiNagrod.fxml' Controller Class
 */

public class PanelEdycjiNagrodController extends BulidStage implements Initializable {

    File file = new File("C:\\Users\\Banan\\Pictures\\a.jpg");
    @FXML
    private ImageView imageview;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button panelEdycjiNagrodButtonUsun;

    @FXML
    private Button panelEdycjiNagrodButtonAnuluj;

    @FXML
    private Button PanelEdycjiNagrod;

    @FXML
    void panelEdycjiNagrodButtonDodajZdjecie(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Obrazy", "*.jpg","*.png","*.jpeg")
                ,new FileChooser.ExtensionFilter("Inne", "*")
        );
        file = fileChooser.showOpenDialog(stage);
        try {
            Image image = new Image(file.toURI().toString());

            imageview.setImage(image);
        }catch(IllegalArgumentException argumentException){
            System.out.println("Nie wybrałeś zdjęcia lub rozszerzenie nie jest obsługiwane. " + argumentException.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void panelEdycjiNagrodButtonAnuluj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_NAGROD);
        activeScene(event, false, false);
    }

    @FXML
    void panelEdycjiNagrodButtonUsun(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_NAGROD);
        activeScene(event, false, false);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert PanelEdycjiNagrod != null : "fx:id=\"panelEdycjiNagrodButtonDodajZdjecie\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert panelEdycjiNagrodButtonUsun != null : "fx:id=\"panelEdycjiNagrodButtonUsun\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert panelEdycjiNagrodButtonAnuluj != null : "fx:id=\"panelEdycjiNagrodButtonAnuluj\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);


    }
}