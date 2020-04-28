package com.Ankiety_PZ.test;

import com.Ankiety_PZ.query.ConnectToDataBase;
import com.Ankiety_PZ.query.LoadDump;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class TestFX extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setScene(new Scene(new Pane(), 800, 600));
//        stage.show();
//    }

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML(SceneFXML.PANEL_LOGIN));
        stage.setScene(scene);
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
        ConnectToDataBase.connectToDataBase();
        LoadDump test = new LoadDump();
        try {
            test.loadDump("baza_danych/bazadanychtest/ankiety2.sql");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Current dir using System:" + Paths.get("") .toAbsolutePath().toString());
        launch();
    }
}
