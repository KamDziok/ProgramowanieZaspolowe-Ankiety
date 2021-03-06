package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za logowanie użytkownika.
 * Klasa kontroler do ramki 'PanelLogin.fxml', jest potomkiem klasy {@link BulidStage}.
 */

public class PanelLoginController extends BulidStage {

    private String email;
    private String password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField panelLoginTFEmail;

    @FXML
    private PasswordField panelLoginPFPassword;

    @FXML
    private Button panelLoginButtonLogin;

    @FXML
    private Button panelLoginButtonRegi;

    @FXML
    private Label panelLoginLabelError;

    /**
     * Metoda obsługi przycisku zaloguj.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     * @author KamDziok
     */

    @FXML
    void panelLoginButtonLoginAcept(ActionEvent event) {
        panelLoginLabelError.setText("");
        email = panelLoginTFEmail.getText();
        password = panelLoginPFPassword.getText();

        if (!email.isEmpty() && !password.isEmpty()) {
            System.out.println(email);
            System.out.println(password);
            UzytkownicyQuery query = new UzytkownicyQuery();
            Uzytkownicy user = query.selectByMailAndPassword(email, password);
            System.out.println(user);
            if (user != null) {
                switch (user.getUprawnienia()) {
                    case Permissions.ADMIN:
                        loadingFXML(event, SceneFXML.PANEL_ADMINA);
                        PanelAdminaController panelAdminaController = load.getController();
                        panelAdminaController.setStartValues(user);
                        activeScene(event, false, false);
                        break;
                    case Permissions.ANKIETER:
                        loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
                        PanelAnkieterController panelAnkieterController = load.getController();
                        panelAnkieterController.setStartValues(user);
                        activeScene(event, false, false);
                        break;
                    case Permissions.OSOBA_OD_NAGROD:
                        loadingFXML(event, SceneFXML.PANEL_NAGROD);
                        PanelOsobyOdNagrodController panelOsobyOdNagrodController = load.getController();
                        panelOsobyOdNagrodController.setStartValues(user);
                        activeScene(event, false, false);
                        break;
                    case Permissions.KLIENT:
                        loadingFXML(event, SceneFXML.PANEL_UZYTKOWNIKA);
                        PanelUzytkownikaController panelUzytkownikaController = load.getController();
                        panelUzytkownikaController.setStartValues(user);
                        activeScene(event, false, false);
                        break;
                    default:
                        panelLoginLabelError.setText("Twoje konto zostało zablokowane!");

                }
            } else {
                panelLoginLabelError.setText("Błędny mail lub hasło!");
            }

        } else {
            panelLoginLabelError.setText("Nie podałeś wszystkich danych!");
        }
    }

    /**
     * Metoda obsługi przycisku zarejestruj.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelLoginButtonRegiAcept(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_REGI);
        PanelRegiController panelRegiController = load.getController();
        activeScene(event, false, false);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert panelLoginTFEmail != null : "fx:id=\"panelLoginTFEmail\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginPFPassword != null : "fx:id=\"panelLoginPFPassword\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginButtonLogin != null : "fx:id=\"panelLoginButtonLogin\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginButtonRegi != null : "fx:id=\"panelLoginButtonRegi\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginLabelError != null : "fx:id=\"panelLoginLabelError\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        panelLoginLabelError.setText("");
    }
}
