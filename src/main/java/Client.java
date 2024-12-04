import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Client extends Thread {
    private Semaphore semaphore;
    private Compartiment[] compartiments = {};
    private Stand standCuisson;

    /**
     * Constructeur de Client
     * 
     * @param semaphore     du restaurant
     * @param compartiments à acceder pour se servir
     * @param standCuisson  à acceder pour faire cuire
     */
    public Client(Semaphore semaphore, Compartiment[] compartiments, Stand standCuisson) {
        this.semaphore = semaphore;
        this.compartiments = compartiments;
        this.standCuisson = standCuisson;
    }

    /**
     * Lance les actions du client
     */
    public void run() {
        try {
            // rentre dans le restaurant
            semaphore.acquire();
            System.out.println("Le client " + Thread.currentThread().getId() + " vient de rentrer dans le restaurant");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> quantites = new ArrayList<>();
        // se sert à chaque compartiment
        for (Compartiment compartiment : compartiments) {
            Random random = new Random();
            int quantite = random.nextInt(101);
            quantites.add(quantite);
            compartiment.seServir(quantite);
            try {
                Thread.sleep(200 + quantite);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // passe au stand de cuisson
        standCuisson.cuire(quantites);
        // mange
        quantites.forEach((q) -> {
            try {
                Thread.sleep(q);
            } catch (InterruptedException e) {
            }
        });
        semaphore.release(); // sort du restaurant
        System.out.println(
                "Le client " + Thread.currentThread().getId() + "a fini de manger et vient de sortir du restaurant");
    }
}
