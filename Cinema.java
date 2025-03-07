package DECIMALEZIONE.EsercizioPomeriggio;

import java.util.*;

abstract class Utente {
    private String nomeUtente; // Nome utente
    private String password; // Password
    private boolean isLogged; // Stato di login

    // Costruttore della classe Utente
    public Utente(String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.isLogged = false;
    }

    // Getter per il nome utente
    public String getNomeUtente() {
        return nomeUtente;
    }

    // Setter per il nome utente
    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    // Getter per la password
    public String getPassword() {
        return password;
    }

    // Setter per la password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter per lo stato di login
    public boolean isLogged() {
        return isLogged;
    }

    // Setter per lo stato di login
    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    // Metodo per effettuare il login
    public boolean login(String nomeUtente, String password) {
        if (this.nomeUtente.equals(nomeUtente) && this.password.equals(password)) {
            isLogged = true;
            System.out.println("Login effettuato con successo.");
            return true;
        } else {
            System.out.println("Credenziali errate.");
            return false;
        }
    }

    // Metodo per effettuare il logout
    public void logout() {
        if (isLogged) {
            isLogged = false;
            System.out.println("Logout effettuato con successo.");
        } else {
            System.out.println("L'utente non è loggato.");
        }
    }
}

class Cliente extends Utente {
    private int postiDisponibili; // Posti disponibili per il cliente

    // Costruttore della classe Cliente
    public Cliente(String nomeUtente, String password, int postiDisponibili) {
        super(nomeUtente, password);
        this.postiDisponibili = postiDisponibili;
    }

    // Getter per i posti disponibili
    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    // Setter per i posti disponibili
    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    // Metodo per prenotare un biglietto
    public void prenotaBiglietto(int numeroPosti) {
        if (numeroPosti > 0 && numeroPosti <= postiDisponibili) {
            postiDisponibili -= numeroPosti;
            System.out.println("Prenotazione confermata. Posti rimanenti: " + postiDisponibili);
        } else {
            System.out.println("Prenotazione non riuscita. Posti insufficienti.");
        }
    }
}

abstract class Sala {
    private int numeroPosti; // Numero di posti nella sala
    private int numeroSala; // Numero della sala

    // Costruttore della classe Sala
    public Sala(int numeroPosti, int numeroSala) {
        this.numeroPosti = numeroPosti;
        this.numeroSala = numeroSala;
    }

    // Getter per il numero di posti
    public int getNumeroPosti() {
        return numeroPosti;
    }

    // Setter per il numero di posti
    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    // Getter per il numero della sala
    public int getNumeroSala() {
        return numeroSala;
    }

    // Setter per il numero della sala
    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }
}

class SalaFilm extends Sala {
    private String nomeFilm; // Nome del film
    private String orarioProiezione; // Orario della proiezione

    // Costruttore della classe SalaFilm
    public SalaFilm(int numeroPosti, int numeroSala, String nomeFilm, String orarioProiezione) {
        super(numeroPosti, numeroSala);
        this.nomeFilm = nomeFilm;
        this.orarioProiezione = orarioProiezione;
    }

    // Getter per il nome del film
    public String getNomeFilm() {
        return nomeFilm;
    }

    // Setter per il nome del film
    public void setNomeFilm(String nomeFilm) {
        this.nomeFilm = nomeFilm;
    }

    // Getter per l'orario della proiezione
    public String getOrarioProiezione() {
        return orarioProiezione;
    }

    // Setter per l'orario della proiezione
    public void setOrarioProiezione(String orarioProiezione) {
        this.orarioProiezione = orarioProiezione;
    }

    // Metodo per mostrare i dettagli del film
    public void mostraDettagliFilm() {
        System.out.println("Film: " + nomeFilm + ", Orario: " + orarioProiezione + ", Sala: " + getNumeroSala());
    }
}

class GestoreCinema extends Utente {
    private ArrayList<SalaFilm> sale; // Lista delle sale film

    // Costruttore della classe GestoreCinema
    public GestoreCinema(String nomeUtente, String password) {
        super(nomeUtente, password);
        this.sale = new ArrayList<>();
    }

    // Getter per la lista delle sale film
    public ArrayList<SalaFilm> getSale() {
        return sale;
    }

    // Setter per la lista delle sale film
    public void setSale(ArrayList<SalaFilm> sale) {
        this.sale = sale;
    }

    // Metodo per aggiungere una sala film
    public void aggiungiSalaFilm(SalaFilm sala) {
        sale.add(sala);
        System.out.println("Film aggiunto: \"" + sala.getNomeFilm() + "\" in Sala " + sala.getNumeroSala() + ", ore " + sala.getOrarioProiezione());
    }
}

public class Cinema {
    private static Scanner scanner = new Scanner(System.in); // Scanner per l'input
    private static ArrayList<Cliente> clienti = new ArrayList<>(); // Lista dei clienti
    private static ArrayList<GestoreCinema> gestori = new ArrayList<>(); // Lista dei gestori
    private static Cliente clienteLoggato = null; // Cliente loggato
    private static GestoreCinema gestoreLoggato = null; // Gestore loggato

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            clear(); // Pulisce lo schermo
            System.out.println("\nMenu Principale: \n1. Menu Cliente\n2. Menu Gestore\n3. Esci");
            System.out.print("Scelta: ");
            try {
                int scelta = scanner.nextInt(); // Legge la scelta dell'utente
                scanner.nextLine();
                switch (scelta) {
                    case 1:
                        menuCliente(); // Mostra il menu cliente
                        break;
                    case 2:
                        menuGestore(); // Mostra il menu gestore
                        break;
                    case 3:
                        System.out.println("Uscita...");
                        scanner.close(); // Chiude lo scanner
                        System.exit(0); // Esce dal programma
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserire un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private static void menuCliente() throws InterruptedException {
        boolean exit = false;
        while (!exit) {
            clear(); // Pulisce lo schermo
            System.out.println("\nMenu Cliente: \n1. Registra Cliente\n2. Login Cliente\n3. Prenota Biglietto\n4. Logout Cliente\n5. Torna al Menu Principale");
            System.out.print("Scelta: ");
            try {
                int scelta = scanner.nextInt(); // Legge la scelta dell'utente
                scanner.nextLine();
                switch (scelta) {
                    case 1:
                        registraCliente(); // Registra un nuovo cliente
                        break;
                    case 2:
                        loginCliente(); // Effettua il login del cliente
                        break;
                    case 3:
                        prenotaBiglietto(); // Prenota un biglietto
                        break;
                    case 4:
                        logoutCliente(); // Effettua il logout del cliente
                        break;
                    case 5:
                        exit = true; // Torna al menu principale
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserire un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private static void menuGestore() throws InterruptedException {
        boolean exit = false;
        while (!exit) {
            clear(); // Pulisce lo schermo
            System.out.println("\nMenu Gestore: \n1. Registra Gestore\n2. Login Gestore\n3. Aggiungi Film\n4. Logout Gestore\n5. Torna al Menu Principale");
            System.out.print("Scelta: ");
            try {
                int scelta = scanner.nextInt(); // Legge la scelta dell'utente
                scanner.nextLine();
                switch (scelta) {
                    case 1:
                        registraGestore(); // Registra un nuovo gestore
                        break;
                    case 2:
                        loginGestore(); // Effettua il login del gestore
                        break;
                    case 3:
                        aggiungiFilm(); // Aggiunge un nuovo film
                        break;
                    case 4:
                        logoutGestore(); // Effettua il logout del gestore
                        break;
                    case 5:
                        exit = true; // Torna al menu principale
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserire un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private static void registraCliente() {
        System.out.print("Inserisci username: ");
        String user = scanner.nextLine(); // Legge l'username
        System.out.print("Inserisci password: ");
        String pass = scanner.nextLine(); // Legge la password
        clienti.add(new Cliente(user, pass, 50)); // Aggiunge il nuovo cliente alla lista
        System.out.println("Cliente registrato con successo.");
    }

    private static void loginCliente() {
        System.out.print("Inserisci username: ");
        String user = scanner.nextLine(); // Legge l'username
        System.out.print("Inserisci password: ");
        String pass = scanner.nextLine(); // Legge la password
        for (Cliente cliente : clienti) {
            if (cliente.login(user, pass)) {
                clienteLoggato = cliente; // Imposta il cliente loggato
                return;
            }
        }
        System.out.println("Cliente non trovato.");
    }

    private static void prenotaBiglietto() {
        if (gestori.isEmpty()) {
            System.out.println("Nessun film disponibile.");
            return;
        }

        if (gestoreLoggato == null) {
            System.out.println("Nessun gestore loggato.");
            return;
        }
        GestoreCinema gestore = gestoreLoggato; // Usa il gestore loggato
        ArrayList<SalaFilm> sale = gestore.getSale(); // Prende la lista delle sale

        if (sale.isEmpty()) {
            System.out.println("Nessun film disponibile.");
            return;
        }

        System.out.println("Film disponibili:");
        for (SalaFilm sala : sale) {
            sala.mostraDettagliFilm(); // Mostra i dettagli dei film
        }

        System.out.print("Inserisci il nome del film che vuoi prenotare: ");
        String nomeFilm = scanner.nextLine(); // Legge il nome del film

        SalaFilm salaScelta = null;
        for (SalaFilm sala : sale) {
            if (sala.getNomeFilm().equalsIgnoreCase(nomeFilm)) {
                salaScelta = sala; // Imposta la sala scelta
                break;
            }
        }

        if (salaScelta == null) {
            System.out.println("Film non trovato.");
            return;
        }

        System.out.print("Quanti biglietti vuoi prenotare? ");
        int posti = scanner.nextInt(); // Legge il numero di posti
        scanner.nextLine();

        if (clienteLoggato != null) {
            clienteLoggato.prenotaBiglietto(posti); // Prenota il biglietto
        } else {
            System.out.println("Nessun cliente loggato.");
        }
    }

    private static void registraGestore() {
        System.out.print("Inserisci username: ");
        String user = scanner.nextLine(); // Legge l'username
        System.out.print("Inserisci password: ");
        String pass = scanner.nextLine(); // Legge la password
        gestori.add(new GestoreCinema(user, pass)); // Aggiunge il nuovo gestore alla lista
        System.out.println("Gestore registrato con successo.");
    }

    private static void loginGestore() {
        System.out.print("Inserisci username: ");
        String user = scanner.nextLine(); // Legge l'username
        System.out.print("Inserisci password: ");
        String pass = scanner.nextLine(); // Legge la password
        for (GestoreCinema gestore : gestori) {
            if (gestore.login(user, pass)) {
                gestoreLoggato = gestore; // Imposta il gestore loggato
                return;
            }
        }
        System.out.println("Gestore non trovato.");
    }

    private static void aggiungiFilm() {
        if (gestoreLoggato == null) {
            System.out.println("Nessun gestore loggato.");
            return;
        }

        System.out.print("Nome del film: ");
        String film = scanner.nextLine(); // Legge il nome del film
        System.out.print("Numero sala: ");
        int numSala = scanner.nextInt(); // Legge il numero della sala
        scanner.nextLine();
        System.out.print("Orario proiezione: ");
        String orario = scanner.nextLine(); // Legge l'orario della proiezione
        gestoreLoggato.aggiungiSalaFilm(new SalaFilm(100, numSala, film, orario)); // Aggiunge la sala film
    }

    private static void logoutCliente() {
        if (clienteLoggato != null) {
            clienteLoggato.logout(); // Effettua il logout del cliente
            clienteLoggato = null;
        } else {
            System.out.println("Nessun cliente loggato.");
        }
    }

    private static void logoutGestore() {
        if (gestoreLoggato != null) {
            gestoreLoggato.logout(); // Effettua il logout del gestore
            gestoreLoggato = null;
        } else {
            System.out.println("Nessun gestore loggato.");
        }
    }

    private static void clear() throws InterruptedException {
        Thread.sleep(2500); // Attende 2.5 secondi
        System.out.print("\033[H\033[2J"); // Pulisce lo schermo
        System.out.flush();
    }
}