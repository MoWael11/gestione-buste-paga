package it.eforhum;

import java.time.LocalDate;

import it.eforhum.buste.GestioneBustePaga;
import it.eforhum.dipendenti.GestioneDipendenti;
import it.eforhum.utils.Input;

public class TUI {
    Byte choice;
    boolean isValid;
    GestioneDipendenti gestioneDipendenti = new GestioneDipendenti();
    GestioneBustePaga gestioneBustePaga = new GestioneBustePaga();
    
    TUI() {
        System.out.println("Benevenuto nella gestione delle buste paga");

    }   
    
    public void menu() {
        while (true) {
            choice = Input.byteInput("Inserisci\n1 per gestire i dipendenti\n2 per gestire le buste paga\n0 per uscire\n");
            switch (choice) {
                case 1:
                    System.out.println("Gestione dipendenti");
                    break;
                case 2:
                    System.out.println("Gestione buste paga");
                    break;
                case 0:
                    System.out.println("Arrivederci");
                    return;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }
        
    }

    public void gestioneDipendenti() {
        String cf, nome, cognome;
        Double RAL, RAN;
        LocalDate dataAssunzione;

        choice = Input.byteInput("Inserisci\n1 per aggiungere un dipendente\n2 per rimuovere un dipendente\n3 per modificare un dipendente\n4 per calcolare il totale degli stipendi (netto) per un dipendente\n5 calcolo del totale delle trattenute di un singolo dipendente (F24)\n6 Stampare il dipendente con lo stipendio più alto\n0 per tornare al menu principale\n");
        switch (choice) {
            case 1 -> {
                do { 
                    cf = Input.stringInput("Inserisci il codice fiscale del dipendente: ");
                    isValid = !gestioneDipendenti.verificaSeEsisteIlDipendente(cf);
                } while (!isValid);
                nome = Input.stringInput("Inserisci il nome del dipendente: ");
                cognome = Input.stringInput("Inserisci il cognome del dipendente: ");
                
                do { 
                    RAL = Input.doubleInput("Inserisci la RAL del dipendente: ");
                    RAN = Input.doubleInput("Inserisci la RAN del dipendente: ");
                    
                    if (RAL < RAN) {
                        System.out.println("La RAL non può essere minore della RAN!");
                        isValid = false;
                    } else {
                        isValid = true;
                    }
                } while (!isValid);
                
                dataAssunzione = Input.dateInput("Inserisci la data di assunzione");

                gestioneDipendenti.aggiungiDipendente(cf, nome, cognome, dataAssunzione, RAL, RAN);
                System.out.println("Nuovo dipendente aggiunto");
            }
            case 2 -> {
                cf = Input.stringInput("Inserisci il codice fiscale del dipendente da rimuovere: ");

                gestioneDipendenti.rimuoviDipendente(cf);
                System.out.println("Dipendente rimosso");
            }
            case 3 -> {
                do { 
                    cf = Input.stringInput("Inserisci il codice fiscale del dipendente da modificare: ");
                    isValid = !gestioneDipendenti.verificaSeEsisteIlDipendente(cf);
                } while (!isValid);
                nome = Input.stringInput("Inserisci il nome del dipendente: ");
                cognome = Input.stringInput("Inserisci il cognome del dipendente: ");
                
                Integer annoRAL_RAN;
                do { 
                    RAL = Input.doubleInput("Inserisci la RAL del dipendente: ");
                    RAN = Input.doubleInput("Inserisci la RAN del dipendente: ");
                    
                    if (RAL < RAN) {
                        System.out.println("La RAL non può essere minore della RAN!");
                        isValid = false;
                    } else {
                        isValid = true;
                    }

                    annoRAL_RAN = Input.yearInput("Inserisci la l'anno RAL-RAN");
                } while (!isValid);
                
                dataAssunzione = Input.dateInput("Inserisci la data di assunzione");

                gestioneDipendenti.aggiornaDipendente(cf, nome, cognome, dataAssunzione, RAL, RAN, annoRAL_RAN);
                System.out.println("Dipendente modificato");
            }
            case 4 -> {
                do { 
                    cf = Input.stringInput("Inserisci il codice fiscale del dipendente: ");
                    
                    isValid = gestioneDipendenti.verificaSeEsisteIlDipendente(cf);
                } while (!isValid);

                double totaleNetto = gestioneBustePaga.calcoloTotaleStipendiPerDipendente(cf);
                System.out.println("Totale stipendio netto per il dipendente " + cf + ": " + totaleNetto);
            }
            case 5 -> {
                do { 
                    cf = Input.stringInput("Inserisci il codice fiscale del dipendente: ");

                    // TODO: CHECK CF if exists
                } while (!isValid);
                // TODO: calculate total withholdings for employee
            }
            case 6 -> {
            }
            case 0 -> {
                return;
            }
            default -> System.out.println("Scelta non valida, riprova.");
        }
        // TODO: print employee with highest salary
            }

    public void gestioneBuste() {
        choice = Input.byteInput("Inserisci\n1- Registrazione busta paga\n2- Elenco bonifici e f24");
        String cf;
        Integer numeroBusta;
        Double stipendioNetto, stipendioLordo;
        LocalDate dataEmissione;
        // write 
        switch (choice) {
            case 1:
                do {
                    cf = Input.stringInput("Inserisci il codice fiscale del dipendente: ");

                    // TODO: CHECK CF if exists
                } while (!isValid);

                do {
                    numeroBusta = Input.integerInput("Inserisci il numero della busta (1 - 13): ");
                    
                    if (numeroBusta < 1 || numeroBusta > 13) {
                        System.out.println("Intervallo errato");
                        isValid = false;
                    } else {
                        isValid = true;
                    }
                } while (!isValid);

                stipendioNetto = Input.doubleInput("Inserisci lo stipendio netto: ");
                stipendioLordo = Input.doubleInput("Inserisci lo stipendio lordo: ");

                dataEmissione = Input.dateInput("Inserisci la data di emissione: ");

                // TODO create busta
                break;

            case 2: 
                int pagina = 1;

                // TODO get list of buste by employee 
                // for in list
                
                break;
            default:
                throw new AssertionError();
        }
    }
}
