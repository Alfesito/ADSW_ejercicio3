package es.upm.dit.adsw.ej3;

import java.util.Random;

/**
 * Escenarios que fuerzan el comportamiento del juego.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class StressTest3 {
    private static final int N_SERPENTS = 2;

    public static void main(String[] args) {
        Game.init(Game.TESTING);
        Game.setAppleListMonitor(new AppleListMonitor());

        AppleGenerator appleGenerator = new AppleGenerator(1000);
        appleGenerator.start();

        Random random = new Random();
        for (int s = 0; s < N_SERPENTS; s++) {
            int x = random.nextInt(Game.getScreen().getWidth());
            int y = random.nextInt(Game.getScreen().getHeight());
            double angle = random.nextGaussian();
            Serpent serpent = new Serpent(x, y, 20);
            serpent.move(angle);
            Game.setSerpent(serpent);
            new Thread(serpent).start();
        }

        BallGenerator ballGenerator = new BallGenerator(1000, 50);
        ballGenerator.start();
    }
}
