package es.upm.dit.adsw.ej3;

/**
 * Escenarios que fuerzan el comportamiento del juego.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class StressTest1 {
    public static void main(String[] args) {
        Game.init(Game.TESTING);
        Game.setAppleListMonitor(new AppleListMonitor());

        AppleGenerator appleGenerator = new AppleGenerator(2000);
        appleGenerator.start();

        BallGenerator ballGenerator = new BallGenerator(1000, 50);
        ballGenerator.start();
    }

}
