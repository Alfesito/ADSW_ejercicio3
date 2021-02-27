package es.upm.dit.adsw.ej3;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * TEST monitor de lecturas-escrituras
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 30/03/2019
 */
public class RW_MonitorTest {
	
	private RW_Monitor m = new RW_Monitor();

	public RW_MonitorTest() {}
	
	@Test	//inicio
	public void test0() {
		assertEquals(0, m.getNWritersIn());
		assertEquals(0, m.getNReadersIn());
	}
	@Test	//openReading()
	public void test1() {
		m.openReading();
		assertEquals(0, m.getNWritersIn());
		assertEquals(1, m.getNReadersIn());
		
	}
	@Test	//openWriting()
	public void test2() {
		m.openWriting();
		assertEquals(1, m.getNWritersIn());
		assertEquals(0, m.getNReadersIn());
	}
	@Test	//closeReading()
	public void test3() {
		m.openReading();
		m.closeReading();//
		assertEquals(0, m.getNWritersIn());
		assertEquals(0, m.getNReadersIn());
	}
	
	//Exception: closeReading()
	@Test (expected = java.lang.IllegalMonitorStateException.class)
	public void test4() {
		m.closeReading();
		assertEquals(-1, m.getNWritersIn());
		assertEquals(-1, m.getNReadersIn());
		assertEquals(null, m.getNWritersIn());
		assertEquals(null, m.getNReadersIn());
	}
	
	@Test	//closeWriting()
	public void test5() {
		m.openWriting();
		m.closeWriting();
		assertEquals(0, m.getNWritersIn());
		assertEquals(0, m.getNReadersIn());
	}
	
	//Exception: closeWriting()
	@Test (expected = java.lang.IllegalMonitorStateException.class)
	public void test6() {
		m.closeWriting();
		assertEquals(-1, m.getNWritersIn());
		assertEquals(-1, m.getNReadersIn());
		assertEquals(null, m.getNWritersIn());
		assertEquals(null, m.getNReadersIn());
	}
	
	@Test (expected = Exception.class) 
	public void ow_cr() {     
		m.openWriting();     
		m.closeReading(); 
		}
	
/*	@Test (expected = Exception.class)
	public void or_ow() {
		m.openReading();
		m.openWriting();
	}
	
	@Test (expected = Exception.class)
	public void ow_or() {
		m.openWriting();
		m.openReading();
	}*/
	
	
}
