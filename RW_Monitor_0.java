package es.upm.dit.adsw.ej3;

/**
 * Monitor de lecturas-escrituras.
 *
 * @author jose a. manas
 * @version 8-4-2018
 */
public class RW_Monitor_0 {

    /**
     * Getter.
     *
     * @return numero de lectores autorizados en este momento.
     */
    public int getNReadersIn() {
        return 0;
    }

    /**
     * Getter.
     *
     * @return numero de escritores autorizados en este momento.
     */
    public int getNWritersIn() {
        return 0;
    }

    /**
     * Solicitud de permiso para hacer una lectura.
     * La thread que llama se queda esperando hasta que pueda entrar.
     */
    public void openReading() {
//        System.out.println("openReading()");
    }

    /**
     * Devolucion del permiso de lectura.
     *
     * @throws IllegalMonitorStateException si no hay algun lector dentro.
     */
    public void closeReading()
            throws IllegalMonitorStateException {
//        System.out.println("closeReading()");
    }

    /**
     * Solicitud de permiso para hacer una escritura.
     * La thread que llama se queda esperando hasta que pueda entrar.
     */
    public void openWriting() {
//        System.out.println("openWriting()");
    }

    /**
     * Devolucion del permiso de escritura.
     *
     * @throws IllegalMonitorStateException si no hay un escritor.
     */
    public void closeWriting()
            throws IllegalMonitorStateException {
//        System.out.println("closeWriting()");
    }

/*
    void invariant() {
        if (getNReadersIn() > 0 && getNWritersIn() > 0)
            System.err.println("ERROR");
        if (getNWritersIn() > 1)
            System.err.println("ERROR");
    }
*/

/*
    void waiting() {
        try {
            wait();
        } catch(Exception e) {
        }
    }
*/
}
