package es.upm.dit.adsw.ej3;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
public class AppleListMonitor extends java.lang.Object{
	
	private RW_Monitor m = new RW_Monitor();
	private List<Apple> appleListMonitor = new ArrayList <Apple>();
	
/**Constructor	
 */
	public AppleListMonitor() {}
	
/**Tenemos una manzana mas	
 * @param apple
 */
	public void add(Apple apple) {	//modifica la lista
		try {
			m.openWriting();
			appleListMonitor.add(apple);
		}finally {
			m.closeWriting();
		}
	}
/**Tenemos una manzana de menos
 * @param apple
 */
	public void remove (Apple apple) {	//modifica la lista
		try {
			m.openWriting();
			appleListMonitor.remove(apple);
			apple.quit();
		}finally {
			m.closeWriting();
		}
	}
/**Informa si hay una manzana cerca del segmento P1-P2
 * @param P1
 * @param P2
 * @return la manzana cercana, si no: NULL
 */
	public Apple getCloseApple(XY P1, XY P2) {	//consultivo
		try {
			m.openReading();
			for(Apple a: appleListMonitor) {
				if(a != null) {
					if(a.getXY().isCloseTo(P1, P2)) {
						return a;
					}
				}
			}
			return null;
		}finally {
			m.closeReading();
		}
		
	}
/**Actua sobre una manzana cerca del segmento P1-P2	
 * @param P1
 * @param P2
 * @return la manzana cercana, si no: NULL
 */
	public Apple hitCloseApple(XY P1, XY P2) {	//modifica la lista
		try {
			m.openWriting();
			//busca la manzana
			int cont = 0;
			for(Apple a: appleListMonitor) {
				//encuentra la manzana
				if(a.getXY().isCloseTo(P1, P2) && (a != null)) {
					//borra la manzana
					Apple apple = appleListMonitor.remove(cont); 
					apple.quit();
					if(apple!=null) {
						return apple;
					}
				}
				cont++;
			}
			return null;
		}finally {
			m.closeWriting();
		}
	
	}
}
