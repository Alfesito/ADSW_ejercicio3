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
 * Generador de bolas.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class BallGenerator extends Thread {
    private final Random random = new Random();
    private int delta;
    private int dt;

    /**
     * Constructor.
     *
     * @param delta cada cuanto tiempo aparece una nueva bola.
     */
    public BallGenerator(int delta, int dt) {
        this.delta = delta;
        this.dt = dt;
    }

    @Override
    public void run() {
        while (Game.getState() != Game.FINISHED) {
            try {
                Nap.sleep(delta);
                int x0 = random.nextInt(Game.getScreen().getWidth());
                int y0 = 0;
                double angle = Math.PI / 2 + rdm(0.5);
                double dt1 = dt * (1 + random.nextGaussian() / 10);
                Ball ball = new Ball(x0, y0, angle, 20, dt1);
                Thread thread = new Thread(ball);
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private double rdm(double a) {
        return a * 2 * (Math.random() - .5);
    }

    public void test(int x0, int y0, double angle) {
        int ds = 5 + random.nextInt(20);
        Ball ball = new Ball(x0, y0, angle, ds, 100);
        Thread thread = new Thread(ball);
        thread.start();
    }
}
