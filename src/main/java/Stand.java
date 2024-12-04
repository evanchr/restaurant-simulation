import java.util.ArrayList;

public class Stand {
    private boolean occupe;

    /**
     * Permet à un client de faire cuire ses aliments
     * 
     * @param assiete Contient les quantités d'aliment dans l'assiète du client à
     *                faire cuire
     */
    public synchronized void cuire(ArrayList<Integer> assiete) {
        while (occupe) {
            try {
                System.out.println(
                        "Le stand est occupé, le client " + Thread.currentThread().getId() + " attend son tour.");
                wait();
            } catch (Exception e) {
            }
        }
        occupe = true;
        assiete.forEach((q) -> {
            try {
                System.out.println(Thread.currentThread().getId() + " fait cuire pendant " + q + " ms.");
                Thread.sleep(q);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(Thread.currentThread().getId() + " a fini de cuire ses aliments.");
        occupe = false;
        notifyAll();
    }
}
