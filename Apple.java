package es.upm.dit.adsw.ej3;
/**
 *
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

/**
 * Manzanita.
 * Objeto inerte.
 * Simplemente se controla su posicion y su salida a pantalla.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class Apple implements Screen.Thing {
    private Image image;
    private XY position;
    private boolean comida = false;

    /**
     * Manzanita.
     *
     * @param x posicion X.
     * @param y posicion Y.
     */
    public Apple(int x, int y) {
        position = new XY(x, y);
        setImage("apple-32.png");
        Game.getScreen().add(this);
    }

    /**
     * Getter.
     */
    public XY getXY() {
        return position;
    }

    /**
     * Getter.
     */
    public boolean isEaten() {
        return false;
    }

    /**
     * Carga la imagen con la que quiere ser presentado en pantalla.
     *
     * @param filename fichero del que se carga la imagen.
     */
    private void setImage(String filename) {
        if (image != null)
            return;
        Class<Apple> root = Apple.class;
        String path = "imgs/" + filename;
        try {
            URL url = root.getResource(path);
            ImageIcon icon = new ImageIcon(url);
            image = icon.getImage();
        } catch (Exception e) {
            System.err.println("no se puede cargar "
                    + root.getPackage().getName()
                    + System.getProperty("file.separator")
                    + path);
            image = null;
        }
    }

    /**
     * Terminador.
     */
    public void quit() {
        if (comida)
            Game.getScreen().announce("come manzana comida");
        comida = true;
        Game.getScreen().remove(this);
    }

    /**
     * Pinta la imagen propia del movil.
     *
     * @param g sistema grafico 2D para dibujar.
     */
    public void paint(Graphics2D g) {
        int cx = position.getX();
        int cy = position.getY();

        if (image == null) {
            // por si fallara la carga de la imagen
            int r = 6;
            g.setColor(Color.CYAN);
            g.fillRect(cx - r, cy - r, 2 * r, 2 * r);
            g.setColor(Color.RED);
            g.drawRect(cx - r, cy - r, 2 * r, 2 * r);
            return;
        }

        int iWidth = image.getWidth(null);
        int iHeight = image.getHeight(null);
        double escalaX = 20.0 / iWidth;
        double escalaY = 20.0 / iHeight;
        double escala = Math.min(escalaX, escalaY);
        double nwX = cx - escala * iWidth / 2;
        double nwY = cy - escala * iHeight / 2;
        AffineTransform transform = new AffineTransform(escala, 0, 0, escala, nwX, nwY);
        g.drawImage(image, transform, null);
    }
}
