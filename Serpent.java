package es.upm.dit.adsw.ej3;

import java.awt.*;
import java.util.ArrayList;

/**
 * La serpiente.
 * La protagonista del juego.
 * Tiene vida propia: thread controlada por el dedo del usuario.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Serpent
        implements Screen.Thing, Runnable {
    private int r = 6;
    // lo que avanza, en pixels, en cada salto dt.
    private final int ds = 10;
    private final int dt;

    private final java.util.List<XY> body;
    private double direction;
    private int toGrow;

    private final RW_Monitor_0 monitor = new RW_Monitor();
    private volatile boolean running;

    /**
     * Constructor.
     * La 'velocidad' de la serpiente viene dada por ds/dt.
     *
     * @param x  posicion inicial de la cabeza.
     * @param y  posicion inicial de la cabeza.
     * @param dt intervalos de representación en pantalla.
     */
    public Serpent(int x, int y, int dt) {
        body = new ArrayList<>();
        body.add(new XY(x, y));
        direction = 0;
        toGrow = 5;
        this.dt = dt;
        Game.getScreen().add(this);
    }

    /**
     * Setter.
     *
     * @param ang direccion en la que avanza la serpiente. En radianes.
     */
    public void move(double ang) {
        direction = ang;
    }

    /**
     * La serpiente muere.
     * Para ser precisos, la serpiente queda marcada para morir.
     */
    public void quit() {
        running = false;
    }

    @Override
    public void run() {
        Screen screen = Game.getScreen();
        AppleListMonitor appleListMonitor = Game.getAppleListMonitor();
        running = true;

        while (running) {
            int width = screen.getWidth();
            int height = screen.getHeight();

            monitor.openWriting();
            XY head = body.get(0);
            int cx = head.getX();
            int cy = head.getY();
            double dx = ds * Math.cos(direction);
            double dy = ds * Math.sin(direction);
            int nx = (int) (cx + dx);
            int ny = (int) (cy + dy);

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
            if (ny < r) {
                ny = r;
                if (dy < 0)
                    direction = Math.atan2(-dy, dx);
            } else if (ny > height - r) {
                ny = height - r;
                if (dy > 0)
                    direction = Math.atan2(-dy, dx);
            }

            // me muevo al nuevo lugar
            XY prev = head;
            head = new XY(nx, ny);
            body.add(0, head);
            if (toGrow <= 0)
                body.remove(body.size() - 1);
            else
                toGrow--;
            monitor.closeWriting();

            // colisiones
            if (appleListMonitor.getCloseApple(prev, head) != null) {
                if (appleListMonitor.hitCloseApple(prev, head) != null) {
                    toGrow += 5;
                    Game.getPuntuacion().increment(10);
                }
            }

            screen.paint();

            Nap.sleep(dt);
        }

        Game.setState(Game.FINISHED);
        screen.paint();
    }

    /**
     * Getter.
     */
    public XY getHead() {
        try {
            monitor.openReading();
            return body.get(0);
        } finally {
            monitor.closeReading();
        }
    }

    /**
     * Informa de si una bola va a impactar sobre el cuerpo de la serpiente.
     *
     * @param xy   posicon de la bola.
     * @return 0 si impacta en la cabeza; &gt; 0 si impacta en el cuerpo; &lt; 0 si no imapacta.
     */
    public int getCut(XY xy) {
        try {
            monitor.openReading();
            return getCutAux(xy);
        } finally {
            monitor.closeReading();
        }
    }

    /**
     * Impacta (o no) sobre la serpiente.
     *
     * @param xy   posicion de la bola.
     */
    public void cut(XY xy) {
        try {
            monitor.openWriting();
            int cut = getCutAux(xy);
            if (cut < 0)
                return;
            if (cut == 0) {
                running = false;
                return;
            }
            while (body.size() > cut)
                body.remove(body.size() - 1);
            Game.getPuntuacion().decrement(15);
        } finally {
            monitor.closeWriting();
        }
    }

    /**
     * auxiliar.
     * Investiga donde impactaria la bola en la serpiente.
     * Es un metodo no sincronizado: hay que envolverlo bajo el paraguas del monitor.
     *
     * @param ballXY posicion de la bola.
     * @return 0 si impacta en la cabeza; &gt; 0 si impacta en el cuerpo; &lt; 0 si no imapacta.
     */
    private int getCutAux(XY ballXY) {
        XY head = body.get(0);
        if (head.isCloseTo(ballXY))
            return 0;
        for (int i = 1; i < body.size(); i++) {
            XY xy1 = body.get(i - 1);
            XY xy2 = body.get(i);
            if (ballXY.isCloseTo(xy1, xy2))
                return i;
        }
        return -1;
    }

    /**
     * Pinta la serpiente en pantalla.
     */
    @Override
    public void paint(Graphics2D g) {
        monitor.openReading();
        g.setColor(Color.RED);
        for (XY xy : body) {
            int cx = xy.getX();
            int cy = xy.getY();
            g.fillOval(cx - r, cy - r, 2 * r, 2 * r);
            g.setColor(Color.GREEN);
        }
        monitor.closeReading();
    }
}
