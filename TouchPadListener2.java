package es.upm.dit.adsw.ej3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Atiende al movimiento del dedo por la pantalla, orientando a la serpiente.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class TouchPadListener2
        implements MouseMotionListener {
    private int mouseDown_X;
    private int mouseDown_Y;
    private long mouseDown_T;

    /**
     * Tocar y deslizar.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Deslizar.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        int now_X = e.getX();
        int now_Y = e.getY();
        long now_T = System.currentTimeMillis();
        if (mouseDown_T < 0) {
            mouseDown_X = now_X;
            mouseDown_Y = now_Y;
            mouseDown_T = now_T;
            return;
        }
        if (now_T - mouseDown_T > 300) {
            int dx = now_X - mouseDown_X;
            int dy = now_Y - mouseDown_Y;
            Serpent serpent = Game.getSerpent();
            if (serpent != null)
                serpent.move(Math.atan2(dy, dx));
            mouseDown_X = now_X;
            mouseDown_Y = now_Y;
            mouseDown_T = now_T;
        }
    }
}
