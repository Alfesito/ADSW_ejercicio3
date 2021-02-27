package es.upm.dit.adsw.ej3;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Dibujos en pantalla.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Screen {
    private final JFrame frame;
    private final JPanel panel;
    private final ArrayList<Thing> thingList = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param title  texto en el marco.
     * @param width  ancho en pixels.
     * @param height alto en pixels.
     */
    public Screen(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new MyJPanel();
        panel.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        Graphics2D g2 = (Graphics2D) frame.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        panel.addMouseListener(new TouchPadListener1());
//        frame.addMouseMotionListener(new TouchPadListener2());
    }

    /**
     * Anade una cosa a la pantalla.
     *
     * @param thing cosa a pintar como parte del dibujo.
     */
    public void add(Thing thing) {
        synchronized (thingList) {
            thingList.add(thing);
        }
    }

    /**
     * Elimina una cosa de la pantalle.
     *
     * @param thing cosa a pintar como parte del dibujo.
     */
    public void remove(Thing thing) {
        synchronized (thingList) {
            thingList.remove(thing);
        }
    }

    /**
     * Indica al sistema que el dibujo ha cambiado y hay que repintarlo.
     */
    public void paint() {
        frame.repaint();
    }

    /**
     * Getter.
     *
     * @return ancho de la pantalla del pixels.
     */
    public int getWidth() {
        return panel.getWidth();
    }

    /**
     * Getter.
     *
     * @return alto de la pantalla del pixels.
     */
    public int getHeight() {
        return panel.getHeight();
    }

    public void announce(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }

    /**
     * Cosas pintables que se pueden anadir.
     */
    public interface Thing {
        void paint(Graphics2D g);
    }

    private class MyJPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            int width = panel.getWidth();
            int height = panel.getHeight();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);

            synchronized (thingList) {
                for (Thing thing : thingList)
                    thing.paint((Graphics2D) g);
            }
        }
    }
}
