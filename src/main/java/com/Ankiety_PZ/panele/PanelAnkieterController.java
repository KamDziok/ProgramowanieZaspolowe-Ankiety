package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

/**
 * Klasa odpowiedzialna za obsługe listy ankiet przynależnych do osoby je tworzącej
 */

public class PanelAnkieterController extends BulidStage implements SetStartValues {

    private Uzytkownicy curentUser;

    private Ankiety ankiety;

    private String imie_nazwisko_rola_tmp;
    @FXML
    private Label panelAnkieterLabelError;
    @FXML
    private Label imie_nazwisko_rola;
    @FXML
    private Label imie_nazwisko_rola2;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button wyloguj1;
    @FXML
    private Button panelAnkietButtonDodaj;
    @FXML
    private TableView tableAnkiety;
    @FXML
    private TableColumn tytul;
    @FXML
    private TableColumn wygasa;
    @FXML
    private TableColumn pkt;
    @FXML
    private TableColumn przyciskEdycja;
    @FXML
    private TableColumn przyciskUsun;
    @FXML
    private TableColumn przyciskAnaliza;
    @FXML
    private TextField email;
    @FXML
    private TextField haslo;
    @FXML
    private TextField nowehaslo;
    @FXML
    private TextField hasloznowu;
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField miejscowosc;
    @FXML
    private TextField ulica;
    @FXML
    private TextField budynek;
    @FXML
    private TextField lokal;
    @FXML
    private TextField kod1;
    @FXML
    private TextField kod2;

    /**
     * Metoda obsługująca przyciśk Dodaj
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelAnkietButtonDodajAction(ActionEvent event) {
        Date data = new Date();
        Ankiety ankieta = new Ankiety();
        ankieta.setUzytkownicy(curentUser);
        ankieta.setLiczbaWypelnien(0);
        Calendar ca = Calendar.getInstance();
        ca.setTime(data);
        ca.add(Calendar.DATE, 1);
        data = ca.getTime();
        ankieta.setDataRozpoczecia(data);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 31);
        dt = c.getTime();
        ankieta.setDataZakonczenia(dt);
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaAnkietyController panelTworzeniaankietyController = load.getController();
        panelTworzeniaankietyController.SetEdycja(false);
        panelTworzeniaankietyController.SetStart();
        panelTworzeniaankietyController.StartListaPytan();
        panelTworzeniaankietyController.setPytdoUsuniecia();
        panelTworzeniaankietyController.setStartValuesAnkiety(ankieta);
        panelTworzeniaankietyController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    @FXML
    void panelAnkieteraButtonZmienUstawienia(ActionEvent event) {
        String mailAnkieter = email.getText();
        String passwordAnkieter = haslo.getText();
        String passwordNewAnkieter = nowehaslo.getText();
        String passwordRepeatAnkieter = hasloznowu.getText();
        String nameAnkieter = imie.getText();
        String surnameAnkieter = nazwisko.getText();
        String cityAnkieter = miejscowosc.getText();
        String streetAnkieter = ulica.getText();
        String numberHouseStringAnkieter = budynek.getText();
        String numberFlatStringAnkieter = lokal.getText();
        String postCodeFirstStringAnkieter = kod1.getText();
        String postCodeSecondStringAnkieter = kod2.getText();
        Walidacja walidacja = new Walidacja();
        if (walidacja.czyUzupelnionePola(mailAnkieter, surnameAnkieter, nameAnkieter, cityAnkieter, streetAnkieter, numberHouseStringAnkieter, postCodeFirstStringAnkieter, postCodeSecondStringAnkieter)) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstStringAnkieter, postCodeSecondStringAnkieter)) {
                if (walidacja.sprawdzHaslo(passwordAnkieter, passwordRepeatAnkieter, passwordNewAnkieter, curentUser)) {
                    if (walidacja.czyPoprawnyMail(mailAnkieter)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        int postCodeFirstIntN = Integer.parseInt(postCodeFirstStringAnkieter);
                        int postCodeSecondIntN = Integer.parseInt(postCodeSecondStringAnkieter);
                        String postCode = postCodeFirstIntN + "-" + postCodeSecondIntN;
                        curentUser.setMail(mailAnkieter);
                        curentUser.setImie(nameAnkieter);
                        if (!passwordAnkieter.isEmpty()) {
                            curentUser.setHaslo(passwordRepeatAnkieter);
                        }
                        curentUser.setNazwisko(surnameAnkieter);
                        curentUser.setMiejscowosc(cityAnkieter);
                        curentUser.setUlica(streetAnkieter);
                        curentUser.setNumerBudynku(numberHouseStringAnkieter);
                        curentUser.setNumerLokalu(numberFlatStringAnkieter);
                        curentUser.setKodPocztowy(postCode);
                        update.updateUzytkownicy(curentUser);
                        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto ankietera";
                        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
                        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
                        panelAnkieterLabelError.setText("Profil został pomyślnie zaktualizowany.");
                    } else {

                        panelAnkieterLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                } else {
                    panelAnkieterLabelError.setText(walidacja.getBlad_haslo());
                }
            } else {
                panelAnkieterLabelError.setText(walidacja.getBlad_kod_pocztowy());
            }
        } else {
            panelAnkieterLabelError.setText("Wymagane pola są puste!");
        }
    }

    private void setUstawienia() {
        String imie = curentUser.getImie();
        String nazwisko = curentUser.getNazwisko();
        String[] kod = curentUser.getKodPocztowy().split("-");
        email.setText(curentUser.getMail());
        this.imie.setText(imie);
        this.nazwisko.setText(nazwisko);
        miejscowosc.setText(curentUser.getMiejscowosc());
        ulica.setText(curentUser.getUlica());
        budynek.setText(curentUser.getNumerBudynku());
        lokal.setText(curentUser.getNumerLokalu());
        kod1.setText(kod[0]);
        kod2.setText(kod[1]);
    }

    /**
     * Metoda obsługująca przyciśk wyloguj1.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void wyloguj1Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    void ankietawtoku() {
        panelAnkieterLabelError.setText("Nie można edytować ankiety, która się rozpoczęła.");
    }

    private void setAnkiety() {
        AnkietyQuery query = new AnkietyQuery();
        List<Ankiety> ankiety = query.selectAllUzytkownik(curentUser);
        ObservableList<AnkieterTabelka> dane = FXCollections.observableArrayList();
        for (Ankiety ankieta2 : ankiety
        ) {
            dane.add(new AnkieterTabelka(ankieta2, curentUser));
        }
        tableAnkiety.itemsProperty().setValue(dane);
        tytul.setCellValueFactory(new PropertyValueFactory("tytul"));
        wygasa.setCellValueFactory(new PropertyValueFactory("dataZakonczenia"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        przyciskAnaliza.setCellValueFactory(new PropertyValueFactory("buttonAnaliza"));

    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto ankietera";
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
        setUstawienia();
        setAnkiety();
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        this.ankiety = ankieta;
        System.out.println(ankiety);
    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {
    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {
    }

    @Override
    public void setStartValuesIerator(Iterator iterator) {
    }

    @FXML
    void initialize() {
        assert panelAnkietButtonDodaj != null : "fx:id=\"panelAnkietButtonDodaj\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
    }

}
