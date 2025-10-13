package it.eforhum.dipendenti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dipendente implements Comparable<Dipendente> {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private double RAL;
    private double RAN;
    private LocalDate dataAssunzione;

    public Dipendente(String codiceFiscale, String nome, String cognome, LocalDate dataAssunzione) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataAssunzione = dataAssunzione;
        this.dataAssunzione = dataAssunzione;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public double getRAL() {
        return RAL;
    }

    public void setRAL(double RAL) {
        this.RAL = RAL;
    }

    public double getRAN() {
        return RAN;
    }

    public void setRAN(double RAN) {
        this.RAN = RAN;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(LocalDate dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dipendente{")
        .append("codiceFiscale='").append(codiceFiscale).append('\'')
        .append(", nome='").append(nome).append('\'')
        .append(", cognome='").append(cognome).append('\'')
        .append(", RAL=").append(RAL)
        .append(", RAN=").append(RAN)
        .append(", dataAssunzione=").append(dataAssunzione.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")))
        .append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Dipendente o) {
        return Double.compare(this.RAL, o.RAL);
    }

}
