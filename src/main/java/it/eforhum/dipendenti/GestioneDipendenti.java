package it.eforhum.dipendenti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.eforhum.utils.DBConnection;

public class GestioneDipendenti {
    Connection conn;
    List<Dipendente> dipendenti;
    
    public GestioneDipendenti() {
        conn = DBConnection.getConnection();
        dipendenti = new ArrayList<>();

        caricaDipendenti();
        caricaStipendiPerDipendenti();
    }
    
    private void caricaDipendenti() {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM dipendenti";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
               String cf = rs.getString("codice_fiscale");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                LocalDate dataAssunzione = rs.getDate("data_di_assunzione").toLocalDate();

                dipendenti.add(new Dipendente(cf, nome, cognome, dataAssunzione));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void caricaStipendiPerDipendenti() {
        for (int i = 0; i < dipendenti.size(); i++) {
            Dipendente d = dipendenti.get(i);

            String ralSql = "SELECT importo FROM RAL WHERE codice_fiscale = '" + d.getCodiceFiscale() + "' ORDER BY anno DESC LIMIT 1";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(ralSql)) {

                if (rs.next()) {
                    double latestRAL = rs.getDouble("importo");
                    d.setRAL(latestRAL);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String ranSql = "SELECT importo FROM RAN WHERE codice_fiscale = '" + d.getCodiceFiscale() + "' ORDER BY anno DESC LIMIT 1";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(ranSql)) {

                if (rs.next()) {
                    double latestRAN = rs.getDouble("importo");
                    d.setRAN(latestRAN);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void creaBustePagaPerAnno(LocalDate dataAssunzione, double RAL, double RAN, String cf) {
        int anno = dataAssunzione.getYear();
        double mensileRAL = RAL / 13;
        double mensileRAN = RAN / 13;

        for (int mese = 1; mese <= 13; mese++) {
            // GestioneBustePaga.creaBustaPaga(cf, anno, mese, mensileRAL, mensileRAN);

        }
        
        
    }

    public static void creaBustaPaga(String cf, int anno, int mese, double mensileRAL, double mensileRAN) {
        
    }

    public boolean verificaSeEsisteIlDipendente(String cf) {
        for (Dipendente d : dipendenti) {
            if (d.getCodiceFiscale().equals(cf)) {
                return true;
            }
        }
        return false;
    }

    public void aggiungiDipendente(String cf, String nome, String cognome, LocalDate dataAssunzione, double RAL, double RAN) {
        int anno = dataAssunzione.getYear();
        dipendenti.add(new Dipendente(cf, nome, cognome, dataAssunzione));
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO dipendenti (codice_fiscale, nome, cognome, data_di_assunzione) VALUES ('" + cf + "', '" + nome + "', '" + cognome + "', '" + java.sql.Date.valueOf(dataAssunzione) + "')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO RAL (codice_fiscale, anno, importo) VALUES ('" + cf + "', " + anno + ", " + RAL + ")";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO RAN (codice_fiscale, anno, importo) VALUES ('" + cf + "', " + anno + ", " + RAN + ")";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rimuoviDipendente(String cf) {
        dipendenti.removeIf(d -> d.getCodiceFiscale().equals(cf));
        Statement stmt;
        try {
            stmt = conn.createStatement();
            String sql = "DELETE FROM dipendenti WHERE codice_fiscale = '" + cf + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void aggiornaDipendente(String cf, String nome, String cognome, LocalDate dataAssunzione, double RAL, double RAN, int annoRAL_RAN) {
        try {
            // verifica priam sel ral e ran per l'anno esistono se no crea altrimenti aggiorna
            Statement stmt = conn.createStatement();
            String sql = "UPDATE dipendenti SET nome = '" + nome + "', cognome = '" + cognome + "', data_di_assunzione = '" + java.sql.Date.valueOf(dataAssunzione) + "' WHERE codice_fiscale = '" + cf + "'";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO RAL (codice_fiscale, anno, importo) VALUES ('" + cf + "', " + annoRAL_RAN + ", " + RAL + ") ON DUPLICATE KEY UPDATE importo = " + RAL;
            stmt.executeUpdate(sql);
            sql = "INSERT INTO RAN (codice_fiscale, anno, importo) VALUES ('" + cf + "', " + annoRAL_RAN + ", " + RAN + ") ON DUPLICATE KEY UPDATE importo = " + RAN;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
