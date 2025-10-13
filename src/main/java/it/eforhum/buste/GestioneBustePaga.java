package it.eforhum.buste;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.eforhum.utils.DBConnection;

public class GestioneBustePaga {
    private Connection conn;
    private List<BustaPaga> buste;
    
    public GestioneBustePaga() {
        conn = DBConnection.getConnection();
        buste = new ArrayList<>();
    }
    
    public void caricaBuste() {
        String sql = "SELECT * FROM busta_paga";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                double stipendioLordo = rs.getDouble("stipendio_lordo");
                double stipendioNetto = rs.getDouble("stipendio_netto");
                LocalDate dataEmissione = rs.getDate("data_emissione").toLocalDate();
                String codiceFiscale = rs.getString("codice_fiscale");

                buste.add(new BustaPaga(id, numero, stipendioLordo, stipendioNetto, dataEmissione, codiceFiscale));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double calcoloTotaleStipendiPerDipendente(String cf) {
        double totaleNetto = buste.stream()
            .filter(busta -> busta.getCodiceFiscale().equals(cf))
            .mapToDouble(BustaPaga::getStipendioNetto)
            .sum();
        return totaleNetto;
    }

        
}
