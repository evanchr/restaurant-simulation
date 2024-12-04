public class Employe extends Thread {
    private Compartiment[] compartiments;
    private boolean running;

    /**
     * Constructeur de Camion
     * 
     * @param sites
     */
    public Employe(Compartiment[] compartiments) {
        this.compartiments = compartiments;
        this.running = false;
    }

    /**
     * Permet d'arrêter l'employe
     */
    public void stopEmploye() {
        running = false;
    }

    /**
     * Simulation de l'employé qui remplit les compartiments
     */
    public void run() {
        running = true;
        while (running) {
            for (Compartiment c : compartiments) {
                if (!running) {
                    return;
                }
                c.remplir();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
