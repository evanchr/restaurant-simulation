import java.util.concurrent.Semaphore;

public class Restaurant {

    /* Constantes de la simulation */
    private static final int CAPACITE_CLIENTS_MAX = 25;
    private static final int NB_CLIENTS_TOTAL = 40;
    private static final int NB_COMPARTIMENTS = 4;
    private static final String[] NOMS_NOURRITURES = { "poisson", "viande", "légumes", "nouilles" };

    /*
     * Initialisation du sémaphore du restaurant afin de simuler une file d'attente
     * à l'entrée
     */
    private static final Semaphore semaphore = new Semaphore(CAPACITE_CLIENTS_MAX);

    /* Initialisation des variables */
    private Compartiment[] compartiments = new Compartiment[NB_COMPARTIMENTS];
    private Client[] clients = new Client[NB_CLIENTS_TOTAL];
    private Employe employe;
    private Stand standDeCuisson;

    /* Constructeur du restaurant */
    public Restaurant() {
        standDeCuisson = new Stand();
        /* Instanciation des compartiments de nourriture */
        for (int i = 0; i < NB_COMPARTIMENTS; i++) {
            compartiments[i] = new Compartiment(NOMS_NOURRITURES[i]);
        }

        /* Instanciation des clients */
        for (int i = 0; i < NB_CLIENTS_TOTAL; i++) {
            clients[i] = new Client(semaphore, compartiments, standDeCuisson);
        }

        /* Instanciation de l'employe */
        employe = new Employe(compartiments);
        System.out.println(employe);

        /* Démarrage de l'employe et des clients */
        employe.start();
        for (Client c : clients) {
            c.start();
        }
        for (Client c : clients) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        employe.stopEmploye();
        System.out.println("Tous les clients ont fini de manger, l'employé a fini sa journée");
        for (Compartiment c : compartiments) {
            System.out.println(c);
        }
    }
}
