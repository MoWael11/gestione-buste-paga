package it.eforhum.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private DBConnection() {}
    private final static String URI = "jdbc:mysql://localhost:3306/gestione_buste_paga?useSSL=false&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "superSecretPassword";
    private static Connection connection;

    public static Connection connectToDB() {
        if (connection == null) {
            try( Connection mysql = DriverManager.getConnection(URI, USER, PASSWORD);) {
                connection = mysql;
                System.out.println("Connessione al database avvenuta con successo.");
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connessione al database fallita.");
                return null;
            }
        }
        return connection;
    }

    public static java.sql.Connection getConnection() {
        return connection;
    }
}
