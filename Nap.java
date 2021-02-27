package es.upm.dit.adsw.ej3;

import java.util.Random;

/**
 * Rutinas auxiliares para dormir threads.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Nap {
    private static final Random RANDOM = new Random();

    /**
     * Duerme el tiempo indicado.
     *
     * @param ms milisegundos.
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Duerme un periodo aleatorio entre los limites indicados.
     *
     * @param min milisegundos minimos.
     * @param max milisegundos maximos.
     */
    public static void sleep(int min, int max) {
        sleep(min + RANDOM.nextInt(max - min));
    }
}
