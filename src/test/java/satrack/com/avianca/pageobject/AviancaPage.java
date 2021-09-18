package satrack.com.avianca.pageobject;

import net.serenitybdd.core.annotations.findby.By;
import satrack.com.utilidades.SatrackFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.android.AndroidDriver;
import static org.junit.Assert.assertTrue;
import java.net.URL;
import net.serenitybdd.core.pages.PageObject;

public class AviancaPage extends PageObject{
	
	protected static final Logger logger = LoggerFactory.getLogger(AviancaPage.class);
	SatrackFunctions funcionesGenerales = new SatrackFunctions();	
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	String botonBuscar = "areamovil.aviancataca:id/searchButton";
	String botonSoloIda = "areamovil.aviancataca:id/oneWayButton";
	String botonOrigen = "areamovil.aviancataca:id/originButton";
	String primerOrigen = "//*[@resource-id='areamovil.aviancataca:id/itemIconBg' and @index='0']";
	String botonDestino = "areamovil.aviancataca:id/destinationInput";
	String primerDestino = "//*[@resource-id='areamovil.aviancataca:id/itemIcon' and @index='1']";
	String campoFechaVuelo = "areamovil.aviancataca:id/calendarField";
	String tituloPaginaEsperada = "Busca tu vuelo";
		
	@SuppressWarnings("rawtypes")
	public void ingresoSitioLibrosDisponibles(String dispositivo) {
		logger.info("Comienza el paso: Ingresa al sitio de Sahi Tests");		
        try {        	
        	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), funcionesGenerales.traerDispositivo(dispositivo));        	
        }catch(Exception ex) {
        	logger.info("Existe un error al conectar con appium server: "+ex);
        }        
		logger.info("Termina el paso: Ingresa al sitio de Sahi Tests");
	}
	
	public void buscarVueloSoloIda() {
		pulsarBotonID(botonBuscar);
		pulsarBotonID(botonSoloIda);
	}
	
	public void seleccionarPrimerOrigen() {
		pulsarBotonID(botonOrigen);
		pulsarBotonXPATH(primerOrigen);
	}
	
	public void seleccionarPrimerDestino() {
		pulsarBotonID(botonDestino);
		pulsarBotonXPATH(primerDestino);
	}
	
	public void seleccionarFechaActualVuelo() {
		pulsarBotonID(campoFechaVuelo);			
		pulsarBotonXPATH("//*[@resource-id='areamovil.aviancataca:id/day_number_text' and @text='"+funcionesGenerales.traerDiaActual()+"']");
		pulsarBotonID("areamovil.aviancataca:id/save_calendar_button");	
	}
	
	public void buscarVuelo() {
		pulsarBotonID("areamovil.aviancataca:id/search_flights_button");
	}
	
	public void validarPaginaBusquedaVuelos() {
		esperarControlVisual("areamovil.aviancataca:id/webview_title");
		String resultado = driver.findElement(By.id("areamovil.aviancataca:id/webview_title")).getText();
		logger.info("Pantalla en la que estoy: "+resultado);
		while(resultado.contains("Aguarde un instante")) {
			logger.info("Aun se encuentra cargando");
			waitFor(1).seconds();
			resultado = driver.findElement(By.id("areamovil.aviancataca:id/webview_title")).getText();				
		}
		logger.info("Me encuentro en la pagina: "+resultado);
		if(!resultado.contains(tituloPaginaEsperada)) {
			driver.quit();
			assertTrue(String.format("No se observa el titulo esperado: %s , el aplicativo retorna el mensaje: %s",tituloPaginaEsperada,resultado), false);
		}		
		driver.quit();
		logger.info("Logrado");
	}
		
	public void pulsarBotonID(String selector) {
		boolean resultado = false;
		while(!resultado) {
			try {
				driver.findElement(By.id(selector)).click();	
				logger.info("Pulse correctamente el selector: "+selector);
				resultado = true;
				break;
			}catch(Exception ex) {
				logger.info("Aun no se encuentra disponible el selector: "+selector);
			}
			
		}
	}
	
	public void pulsarBotonXPATH(String selector) {
		boolean resultado = false;
		while(!resultado) {
			try {
				driver.findElement(By.xpath(selector)).click();	
				logger.info("Pulse correctamente el selector: "+selector);
				resultado = true;
				break;
			}catch(Exception ex) {
				logger.info("Aun no se encuentra disponible el selector: "+selector);
			}
			
		}
	}
	
	public void esperarControlVisual(String selector) {
		boolean resultado = false;
		while(!resultado) {
			try {
				driver.findElement(By.id(selector)).isDisplayed();	
				logger.info("Se visualiza correctamente el selector: "+selector);
				resultado = true;
				break;
			}catch(Exception ex) {
				logger.info("Aun no se encuentra disponible el selector: "+selector);
			}
			
		}
	}
}