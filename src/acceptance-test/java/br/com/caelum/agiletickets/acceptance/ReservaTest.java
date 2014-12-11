package br.com.caelum.agiletickets.acceptance;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.acceptance.page.ReservaPage;

public class ReservaTest {

	private static WebDriver browser;
	private ReservaPage reserva;

	@BeforeClass
	public static void abreBrowser() {
		browser = new FirefoxDriver();
	}
	
	@Before
	public void setUp() throws Exception {
		reserva = new ReservaPage(browser);
	}

	@AfterClass
	public static void teardown() {
		browser.close();
	}
	
	@Test
	public void reserva95Ingressos() throws Exception {
		reserva.abreListagem();
		
	}
	
	@Test
	public void aumentaPrecoUltimosIngresos() throws Exception {
		reserva.abreListagem();
	}

}
