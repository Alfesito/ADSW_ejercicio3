package es.upm.dit.adsw.ej3;

/**
 * Datos compartidos
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Game {
    private static final String TITLE = "ADSW: serpent game (9.4.2017)";

    public static final int RUNNING = 1;
    public static final int FINISHED = 2;
    public static final int TESTING = 3;
    private static int state;

    private static Screen screen;
    private static Puntuacion puntuacion;

    private static Serpent serpent;
    private static AppleListMonitor appleListMonitor;

    /**
     * Inicializacion de los componentes del juego.
     * @param state estado inicial.
     */
    public static void init(int state) {
        Game.state = state;
        int width = 500;
        int height = 500;
        screen = new Screen(TITLE, width, height);
        puntuacion = new Puntuacion();
        new Thread(puntuacion).start();
    }

    /**
     * Getter.
     */
    public static Screen getScreen() {
        return screen;
    }

    /**
     * Getter.
     */
    public static Puntuacion getPuntuacion() {
        return puntuacion;
    }

    /**
     * Setter.
     */
    public static void setSerpent(Serpent it) {
        serpent = it;
    }

    /**
     * Getter.
     */
    public static Serpent getSerpent() {
        return serpent;
    }

    /**
     * Setter.
     */
    public static void setAppleListMonitor(AppleListMonitor it) {
        appleListMonitor = it;
    }

    /**
     * Getter.
     */
    public static AppleListMonitor getAppleListMonitor() {
        return appleListMonitor;
    }

    /**
     * Setter.
     */
    public static void setState(int state) {
        Game.state = state;
    }

    /**
     * Getter.
     */
    public static int getState() {
        return state;
    }
}
