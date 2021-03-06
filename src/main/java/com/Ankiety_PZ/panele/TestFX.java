package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.query.ConnectToDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa startowa FX.
 */

public class TestFX extends Application {

    private static Scene scene;
    private static String oknoDoOtwarcia = SceneFXML.PANEL_LOGIN;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(oknoDoOtwarcia));
        stage.setScene(scene);
        stage.setTitle("Ankiety");
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TestFX.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
//        LoadDump dump = new LoadDump();
        if(ConnectToDataBase.connectToDataBase()){
            try {
//                dump.loadDump("baza_danych/bazadanychtest/ankiety.sql");
            } catch(Exception e) {
                System.out.println(e.getMessage());
            } finally {
                launch();
            }
        }else {
            oknoDoOtwarcia = SceneFXML.OKNO_INICJALIZACJI_BAZY;
            launch();
        }
    }
}
