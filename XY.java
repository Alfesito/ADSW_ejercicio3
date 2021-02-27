package es.upm.dit.adsw.ej3;

/**
 * Un punto en un espacio 2D.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class XY {
    private static final int EPSILON = 200;
    private final int x;
    private final int y;

    /**
     * Constructor.
     */
    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    /**
     * Getter.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter.
     */
    public int getY() {
        return y;
    }

    /**
     * Detecta si this punto es cercano a Q.
     */
    public boolean isCloseTo(XY Q) {
        if (Game.getState() == Game.TESTING)
            Nap.sleep(100);
        return d2(Q, this) < EPSILON;
    }

    /**
     * Detecta si this punto es cercano al segmento P1-P2.
     */
    public boolean isCloseTo(XY P1, XY P2) {
        double l2 = d2(P1, P2);
        if (l2 < EPSILON)
            return d2(P1, this) < EPSILON;
        double t = ((x - P1.x) * (P2.x - P1.x) + (y - P1.y) * (P2.y - P1.y)) / l2;
        t = Math.max(0, Math.min(t, 1));
        double xm = P1.x + t * (P2.x - P1.x);
        double ym = P1.y + t * (P2.y - P1.y);
        double pxm = x - xm;
        double pym = y - ym;
        return (pxm * pxm + pym * pym) < EPSILON;
    }

    private double d2(XY p, XY q) {
        double d12x = p.x - q.x;
        double d12y = p.y - q.y;
        return d12x * d12x + d12y * d12y;
    }
}
