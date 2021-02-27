package es.upm.dit.adsw.ej3;

import java.awt.*;
////CONTROLAR LA CONCURRENCIA!!!
/**
 * Contados de puntuacion
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
public class Puntuacion
        implements Runnable, Screen.Thing {
    private final Font font;
    private int puntos;

    /**
     * Constructor.
     */
    public Puntuacion() {
        font = new Font("SansSerif", Font.BOLD, 18);
        puntos = 100;
        Game.getScreen().add(this);
    }

    /**
     * Suma puntos.
     *
     * @param n a sumar.
     */
    public synchronized void increment(int n) {	//zona critica
        puntos += n;
    }

    /**
     * Resta puntos.
     *
     * @param n a restar.
     */
    public synchronized void decrement(int n) {	//zona critica
        puntos -= n;
        if (puntos <= 0) {
            Game.getSerpent().quit();
            Game.setState(Game.FINISHED);
        }
    }

    /**
     * Cada segundo resta 1 punto.
     */
    @Override
    public void run() {	// COMPLETAR
    	int ms=1000;
    	while((puntos > 0 || Game.getState() != 2)) {
    		Nap.sleep(ms);
    		decrement(1);
    	}
    }

    /**
     * Se imprime en pantalla.
     *
     * @param g pantalla.
     */
    @Override
    public void paint(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("puntos: " + puntos, 10, 20);
    }
}
