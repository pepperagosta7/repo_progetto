abstract class Sala {
    private int numeroPosti;
    private int numeroSala;

    public Sala(int numeroPosti, int numeroSala) {
        this.numeroPosti = numeroPosti;
        this.numeroSala = numeroSala;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public int getNumeroSala() {
        return numeroSala;
    }
}

class SalaFilm extends Sala {
    String nomeFilm;
    String orarioProiezione;

    public SalaFilm(int numeroPosti, int numeroSala, String nomeFilm, String orarioProiezione) {
        super(numeroPosti, numeroSala);
        this.nomeFilm = nomeFilm;
        this.orarioProiezione = orarioProiezione;
    }

    public void mostraDettagliFilm() {
        System.out.println("Film: " + nomeFilm + ", Orario: " + orarioProiezione + ", Sala: " + getNumeroSala());
    }
}

class GestoreCinema extends Utente {
    private ArrayList<SalaFilm> sale;

    public GestoreCinema(String nomeUtente, String password) {
        super(nomeUtente, password);
        this.sale = new ArrayList<>();
    }

    public void aggiungiSalaFilm(SalaFilm sala) {
        sale.add(sala);
        System.out.println("Film aggiunto: \"" + sala.nomeFilm + "\" in Sala " + sala.getNumeroSala() + ", ore " + sala.orarioProiezione);
    }
}
