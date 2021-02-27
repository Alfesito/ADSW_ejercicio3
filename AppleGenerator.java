package es.upm.dit.adsw.ej3;
/**
 * 
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
import java.util.Random;

/**
 * Generador de manzanitas.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class AppleGenerator extends Thread {
    private final Random random = new Random();
    private final int delta;

    /**
     * Constructor.
     *
     * @param delta intervalo de creacion de manzanitas.
     */
    public AppleGenerator(int delta) {
        this.delta = delta;
    }

    @Override
    public void run() {
        Screen screen = Game.getScreen();
        AppleListMonitor appleListMonitor = Game.getAppleListMonitor();
        while (Game.getState() != Game.FINISHED) {
            Nap.sleep(delta);
            int x = random.nextInt(screen.getWidth());
            int y = random.nextInt(screen.getHeight());
            Apple apple = new Apple(x, y);
            appleListMonitor.add(apple);
        }
    }

    public void test(int n) {
        Screen screen = Game.getScreen();
        AppleListMonitor appleListMonitor = Game.getAppleListMonitor();
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(screen.getWidth());
            int y = random.nextInt(screen.getHeight());
            Apple apple = new Apple(x, y);
            appleListMonitor.add(apple);
        }
    }
}
