package es.upm.dit.adsw.ej3;
/**
 * 
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
import java.awt.*;
import java.util.Random;

/**
 * Una bola.
 * Tiene vida propia: thread.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Ball implements Screen.Thing, Runnable {
    private static final Random random = new Random();
    private int r = 10;
    private final int ds;
    private final double dt;

    private XY xy;
    private double direction;
    private Color body = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

    private volatile boolean running;

    /**
     * Constructor.
     * La 'velocidad' es ds/dt.
     *
     * @param x         posicion X inicial.
     * @param y         posicion Y inicial.
     * @param direction orientacion inicial.
     * @param ds        desplazamiento en pixels en cada intervalo dt.
     * @param dt        cada cuanto tiempo se mueve.
     */
    public Ball(int x, int y, double direction, int ds, double dt) {
        this.xy = new XY(x, y);
        this.direction = direction;
        this.ds = ds;
        this.dt = dt;
        Game.getScreen().add(this);
    }

    /**
     * Setter.
     */
    public void setColor(Color color) {
        body = color;
    }

    /**
     * Setter.
     */
    public void setXY(int x0, int y0) {
        this.xy = new XY(x0, y0);
    }

    /**
     * Getter.
     */
    public XY getXY() {
        return xy;
    }

    /**
     * Getter.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Terminador.
     * Deja la bola marcada para terminar en el siguiente cico.
     */
    public void quit() {
        running = false;
    }

    @Override
    public void run() {
        Serpent serpent = Game.getSerpent();
        AppleListMonitor appleListMonitor = Game.getAppleListMonitor();
        running = true;

        while (running) {
            int width = Game.getScreen().getWidth();
            int height = Game.getScreen().getHeight();

            double dx = ds * Math.cos(direction);
            double dy = ds * Math.sin(direction);
            int nx = (int) (xy.getX() + dx);
            int ny = (int) (xy.getY() + dy);

            // si se sale del cuadro, rebota
            if (nx < r) {
                nx = r;
                if (dx < 0)
                    direction = Math.atan2(dy, -dx);
            } else if (nx > width - r) {
                nx = width - r;
                if (dx > 0)
                    direction = Math.atan2(dy, -dx);
            }
            if (ny > height)
                break;

            // me muevo al nuevo lugar
            XY prev = xy;
            setXY(nx, ny);

            // colisiones
            if (appleListMonitor.getCloseApple(prev, xy) != null) {
                if (appleListMonitor.hitCloseApple(prev, xy) != null) {
                    running = false;    // por consistencia
                    break;              // aunque no espera para salir
                }
            }
            if (serpent != null && serpent.getCut(xy) >= 0)
                serpent.cut(xy);

            Game.getScreen().paint();

            Nap.sleep((int) dt);
        }

        Game.getScreen().remove(this);
        Game.getScreen().paint();
    }

    /**
     * Pinta la bola en pantalla.
     */
    @Override
    public void paint(Graphics2D g) {
        int nwx = xy.getX() - r;
        int nwy = xy.getY() - r;
        if (body != null) {
            g.setColor(body);
            g.fillOval(nwx, nwy, 2 * r, 2 * r);
        }
        g.setColor(Color.BLACK);
        g.drawOval(nwx, nwy, 2 * r, 2 * r);
    }
}
