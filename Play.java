package es.upm.dit.adsw.ej3;

/**
 * Juego de la serpiente.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Play {
    public static void main(String[] args) {
        Game.init(Game.RUNNING);
        Nap.sleep(5000);

        AppleListMonitor appleListMonitor = new AppleListMonitor();
        Game.setAppleListMonitor(appleListMonitor);

        AppleGenerator appleGenerator = new AppleGenerator(4000);
        appleGenerator.start();

        Screen screen = Game.getScreen();
        int x0 = screen.getWidth() / 2;
        int y0 = screen.getHeight() / 2;
        Serpent serpent = new Serpent(x0, y0, 100);
        Game.setSerpent(serpent);
        new Thread(serpent).start();

        Thread ballGenerator = new BallGenerator(4000, 100);
        ballGenerator.start();
    }
}
