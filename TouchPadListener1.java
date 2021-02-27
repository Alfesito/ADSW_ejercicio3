package es.upm.dit.adsw.ej3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Atiende a un clic del dedo en la pantalla, orientando a la serpiente.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class TouchPadListener1
        implements MouseListener {
    /**
     * Hacer clic.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Tocar en la pantalla.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Serpent serpent = Game.getSerpent();
        if (serpent == null)
            return;
        XY head = serpent.getHead();
        int dx = e.getX() - head.getX();
        int dy = e.getY() - head.getY();
        serpent.move(Math.atan2(dy, dx));
    }

    /**
     * Levantar el dedo de la pantalla.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Cuando el raton entra en el componente.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Cuando el raton sale del componente.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

}
