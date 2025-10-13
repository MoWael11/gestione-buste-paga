package it.eforhum.buste;

import java.time.LocalDate;

public class BustaPaga {
    private int numero;
    private double stipendioLordo;
    private double stipendioNetto;
    private LocalDate dataEmissione;

    public BustaPaga(int numero, double stipendioLordo, double stipendioNetto, LocalDate dataEmissione) {
        this.numero = numero;
        this.stipendioLordo = stipendioLordo;
        this.stipendioNetto = stipendioNetto;
        this.dataEmissione = dataEmissione;
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
        return "BustaPaga [numero=" + numero + ", stipendioLordo=" + stipendioLordo + ", stipendioNetto=" + stipendioNetto
                + ", dataEmissione=" + dataEmissione + "]";
    }
}
