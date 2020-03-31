/**
 * Sample Skeleton for 'PanelLogin.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Klasa kontroler do ramki 'PanelLogin.fxml', jest potomkiem klasy {@link BulidStage}.
 *
 * @author KamDziok
 */

public class PanelLoginController extends BulidStage {

    private String email;
    private String password;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="panelLoginTFEmail"
    private TextField panelLoginTFEmail; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginPFPassword"
    private PasswordField panelLoginPFPassword; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginButtonLogin"
    private Button panelLoginButtonLogin; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginButtonRegi"
    private Button panelLoginButtonRegi; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginLabelError"
    private Label panelLoginLabelError; // Value injected by FXMLLoader

    /**
     * Metoda obsługi przycisku zaloguj.
     *
     * @author KamDziok
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelLoginButtonLoginAcept(ActionEvent event) {
        email = panelLoginTFEmail.getText();
        password = panelLoginPFPassword.getText();

        if(!email.isEmpty() && !password.isEmpty()){
            //selectUserByEmailAndPassword(email, password);

            //tymczasowe
           if(email.equals("uzy")){
               //otwórz panel uzytkownika
           }
           if(email.equals("admin")){
               loadingFXML(event, SceneFXML.PANEL_ADMINA);
               activeScene(event, false, false);
           }
            if(email.equals("ank")){
                loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
                activeScene(event, false, false);
            }
            if(email.equals("nag")){
                loadingFXML(event, SceneFXML.PANEL_NAGROD);
                activeScene(event, false, false);
            }
        }else{
            panelLoginLabelError.setText("Nie podałeś wszystkich danych.");
        }
    }

    /**
     * Metoda obsługi przycisku zarejestruj.
     *
     * @author KamDziok
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelLoginButtonRegiAcept(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_REGI);
        PanelRegiController panelRegiController = load.getController();
        //panelRegiController.setValues();
        activeScene(event, false, true);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert panelLoginTFEmail != null : "fx:id=\"panelLoginTFEmail\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginPFPassword != null : "fx:id=\"panelLoginPFPassword\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginButtonLogin != null : "fx:id=\"panelLoginButtonLogin\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginButtonRegi != null : "fx:id=\"panelLoginButtonRegi\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginLabelError != null : "fx:id=\"panelLoginLabelError\" was not injected: check your FXML file 'PanelLogin.fxml'.";

        panelLoginLabelError.setText("");
    }
}