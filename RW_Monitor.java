package es.upm.dit.adsw.ej3;
import java.util.List;
import java.util.ArrayList;
/**
 * MONITOR DE LECTURAS-ESCRITURAS
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
public class RW_Monitor extends es.upm.dit.adsw.ej3.RW_Monitor_0 {
	
	private List<Thread> wCola = new ArrayList<>();
	private int nReaders;
	private int nWriters;
	
	
	/**Constructor
	 */
	public RW_Monitor() {}
	
	/**METODOS PRIVADOS*/	
	//Saca en pantalla el error
	private	void invariant() {
        if ((getNReadersIn()>0) && (getNWritersIn()>0)) {
            System.err.println("ERROR");
        }
        if (getNWritersIn()>1) {
            System.err.println("ERROR");
        }
    }
	//Captura la exception para wait()
 	private void waiting() {
        try {
            wait();
        } catch(Exception e) {}
    }
    
    /**Getter
     * @overrides getNReadersIn in class RW_Monitor_0
     * @return nº de lectores autorizados en este momento
     */
	@Override
	public synchronized int getNReadersIn() {
		return nReaders;
	}
	
	/**Getter
	 * @overrides getNWritersIn in class RW_Monitor_0	
	 * @return nº de escritores autorizados en este momento
	 */
	@Override
	public synchronized int getNWritersIn() {
		return nWriters;
	}
	
	/**Solicitud de permiso para hacer una lectura. La thread que llama
	 * 	se queda esperando hasta que pueda entrar
	 * @overrides in class RW_Monitor_0
	 */
	@Override
	public synchronized void openReading() {
//		System.out.println("openReading()");
		 while((nWriters > 0) || (wCola.size() > 0)) {			
			waiting();		
		}
		 nReaders++;
    	invariant();
	}
	
	/**Solicitud de permiso para hacer una escritura.
	 * La thread que llama se queda esperando hasta que pueda entrar.
	 * @overrides openWriting in class RW_Monitor_0
	 */
	@Override
	public synchronized void openWriting() {
//  	System.out.println("openWriting()");
		int i = 0;
		Thread th = Thread.currentThread();
		wCola.add(th);
		
		while((wCola.get(0) != th) || (nWriters > 0) || (nReaders > 0)){
			waiting();
		} 
		nWriters++;
		wCola.remove(i);
		invariant();
	}
	
	/**Devolucion de permisos de lectura
	 * @overrides closeReading in class RW_Monitor_0
	 * @throws java.lang.IllegalMonitorStateException-Si no hay algun lector dentro
	 */
	@Override
	public synchronized void closeReading() throws java.lang.IllegalMonitorStateException {
//  	System.out.println("closeReading()");
		if (nReaders > 0) {
			nReaders--;
			notifyAll();
			invariant();
		}else {
			throw new java.lang.IllegalMonitorStateException();
		}
	}
	
	/**Devolucion de permisos de escritura
	 * @overrides closeWriting in class RW_Monitor_0
	 * @throws java.lang.IllegalMonitorStateException - si no hay escritor
	 */
	@Override
	public synchronized void closeWriting() throws java.lang.IllegalMonitorStateException{
//      System.out.println("closeWriting()");
		if (nWriters > 0) {	
			nWriters--;
			notifyAll();
			invariant();
		}else {
			throw new java.lang.IllegalMonitorStateException();
		}
	}
	
}
	
