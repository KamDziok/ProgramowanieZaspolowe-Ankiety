package com.Ankiety_PZ.query;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Klasa służy do wykonywania skryptów sql
 */

public class RunningScripts {

    /**
     * Metoda łączy się z serwerem MySql poprzez JDBC w celu wykonania skryptu sql
     *
     * @param link adres serwera
     * @param user nazwa użytkownika bazy danych
     * @param haslo hasło dla tego użytkownika (może być puste)
     * @param source scieżka do pliku *.sql w, którym jest skrypt do wykonania
     */

    public static void exeSqlFile(String link, String user, String haslo, String source) throws Exception {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://" + link;
        Connection con = DriverManager.getConnection(mysqlUrl, user, haslo);
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader(source));
        sr.runScript(reader);
        sr.closeConnection();
    }
}
