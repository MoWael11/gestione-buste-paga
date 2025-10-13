package it.eforhum.buste;

import java.time.LocalDate;

public class BustaPaga {
    private int id;
    private int numero;
    private double stipendioLordo;
    private double stipendioNetto;
    private LocalDate dataEmissione;
    private final String codiceFiscale;

    public BustaPaga(int id, int numero, double stipendioLordo, double stipendioNetto, LocalDate dataEmissione, String codiceFiscale) {
        this.id = id;
        this.numero = numero;
        this.stipendioLordo = stipendioLordo;
        this.stipendioNetto = stipendioNetto;
        this.dataEmissione = dataEmissione;
        this.codiceFiscale = codiceFiscale;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getStipendioLordo() {
        return stipendioLordo;
    }

    public void setStipendioLordo(double stipendioLordo) {
        this.stipendioLordo = stipendioLordo;
    }

    public double getStipendioNetto() {
        return stipendioNetto;
    }

    public void setStipendioNetto(double stipendioNetto) {
        this.stipendioNetto = stipendioNetto;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append('\'');
        sb.append("Numero: ").append(numero).append('\'');
        sb.append("Stipendio Lordo: ").append(stipendioLordo).append('\'');
        sb.append("Stipendio Netto: ").append(stipendioNetto).append('\'');
        sb.append("Data di Emissione: ").append(dataEmissione).append('\'');
    
        return sb.toString();
        
    }
}
