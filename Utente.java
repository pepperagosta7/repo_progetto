// Classe astratta Utente
abstract class Utente {
    private String nomeUtente;
    private String password;
    private boolean loggedIn = false;

    public Utente(String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
    }

    // Metodo per il login
    public boolean login(String nomeUtente, String password) {
        if (this.nomeUtente.equals(nomeUtente) && this.password.equals(password)) {
            loggedIn = true;
            System.out.println("Login effettuato con successo!");
            return true;
        } else {
            System.out.println("Credenziali errate!");
            return false;
        }
    }

    // Metodo per il logout
    public void logout() {
        if (loggedIn) {
            loggedIn = false;
            System.out.println("Logout effettuato.");
        } else {
            System.out.println("Nessun utente connesso.");
        }
    }

    // Metodo per verificare se l'utente è loggato
    public boolean isLoggedIn() {
        return loggedIn;
    }
}

// Classe concreta Cliente che estende Utente
class Cliente extends Utente {
    private int postiDisponibili;

    public Cliente(String nomeUtente, String password, int postiDisponibili) {
        super(nomeUtente, password);
        this.postiDisponibili = postiDisponibili;
    }

    // Metodo per prenotare un biglietto
    public void prenotaBiglietto(int numeroPosti) {
        if (!isLoggedIn()) {
            System.out.println("Devi effettuare il login per prenotare un biglietto.");
            return;
        }
        
        if (numeroPosti > 0 && numeroPosti <= postiDisponibili) {
            postiDisponibili -= numeroPosti;
            System.out.println("Prenotazione effettuata! Posti rimanenti: " + postiDisponibili);
        } else {
            System.out.println("Prenotazione non valida. Posti disponibili: " + postiDisponibili);
        }
    }
}

// Classe di test
