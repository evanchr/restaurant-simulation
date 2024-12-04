public class Compartiment {
    public static final int QUANTITE_MAX = 1000;

    String nourriture;
    int quantite = QUANTITE_MAX;

    /**
     * Constructeur de Compartiment
     * 
     * @param typeNourriture nourriture du compartiment
     */
    public Compartiment(String typeNourriture) {
        nourriture = typeNourriture;
    }

    /**
     * Permet au client de se servir en nourriture
     * 
     * @param quantite de nourriture souhaitée par le client
     */
    public synchronized void seServir(int quantite) {
        while (quantite > getQuantite()) {
            try {
                System.out.println("Il n'y a pas assez de quantité dans le compartiment de " + getNourriture()
                        + ". Le client " + Thread.currentThread().getId() + " s'endort.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Quantité du compartiment de " + getNourriture() + " avant que le client "
                + Thread.currentThread().getId() + " se serve : " + getQuantite());
        setQuantite(getQuantite() - quantite);
        System.out.println("Quantité du compartiment " + getNourriture() + " après que le client "
                + Thread.currentThread().getId() + " se soit servi : " + getQuantite());
        notifyAll();
    }

    /**
     * Permet à l'employé de refaire le plein de nourriture
     */
    public synchronized void remplir() {
        if (getQuantite() < 100) {
            System.out.println("Quantité du compartiment " + getNourriture() + " avant remplissage : " + getQuantite());
            setQuantite(QUANTITE_MAX);
            System.out.println("Remplissage terminé. Nouvelle quantité : " + getQuantite());
        }
        notifyAll();
    }

    /**
     * 
     * @return le type de nourriture
     */
    public String getNourriture() {
        return nourriture;
    }

    /**
     * Permet de changer le type de nourriture
     * 
     * @param nourriture le nouveau type de nourriture
     */
    public void setNourriture(String nourriture) {
        this.nourriture = nourriture;
    }

    /**
     * 
     * @return la quantité de nourriture restante dans le compartiment
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Met à jour la quantité dans le compartiment
     * 
     * @param quantite la nouvelle quantité
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Compartiment [nourriture=" + nourriture + ", quantite=" + quantite + "g]";
    }
}
