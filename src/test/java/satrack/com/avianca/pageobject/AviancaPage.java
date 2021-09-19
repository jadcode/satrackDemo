package satrack.com.avianca.pageobject;

import net.serenitybdd.core.Serenity;
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
	String botonFechaVuelo ="areamovil.aviancataca:id/save_calendar_button";
	String botonBuscarVuelo = "areamovil.aviancataca:id/search_flights_button";
	String tituloPagina = "areamovil.aviancataca:id/webview_title";
		
	@SuppressWarnings("rawtypes")
	public void ingresoSitioLibrosDisponibles(String dispositivo) {
		logger.info("Comienza el paso: Ingresa a la app de avianca en el dispositivo");		
        try {        	
        	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), funcionesGenerales.traerDispositivo(dispositivo));        	
        }catch(Exception ex) {
        	logger.info("Existe un error al conectar con appium server: "+ex);
        }        
		logger.info("Termina el paso: Ingresa a la app de avianca en el dispositivo");
	}
	
	public void buscarVueloSoloIda() {
		logger.info("Comienza el paso: Busca un vuelo de solo ida");
		pulsarBotonID(botonBuscar);
		Serenity.takeScreenshot();
		pulsarBotonID(botonSoloIda);
		logger.info("Termina el paso: Busca un vuelo de solo ida");
	}
	
	public void seleccionarPrimerOrigen() {
		logger.info("Comienza el paso: Selecciona el primer aeropuerto de origen");
		pulsarBotonID(botonOrigen);
		pulsarBotonXPATH(primerOrigen);
		logger.info("Termina el paso: Selecciona el primer aeropuerto de origen");
	}
	
	public void seleccionarPrimerDestino() {
		logger.info("Comienza el paso: Selecciona el primer aeropuerto de destino");
		pulsarBotonID(botonDestino);
		pulsarBotonXPATH(primerDestino);
		logger.info("Termina el paso: Selecciona el primer aeropuerto de destino");
	}
	
	public void seleccionarFechaActualVuelo() {
		logger.info("Comienza el paso: Selecciona la fecha actual para el vuelo");
		pulsarBotonID(campoFechaVuelo);			
		pulsarBotonXPATH("//*[@resource-id='areamovil.aviancataca:id/day_number_text' and @text='"+funcionesGenerales.traerDiaActual()+"']");
		pulsarBotonID(botonFechaVuelo);	
		logger.info("Termina el paso: Selecciona la fecha actual para el vuelo");
	}
	
	public void buscarVuelo() {
		logger.info("Comienza el paso: Busca un vuelo");
		pulsarBotonID(botonBuscarVuelo);
		logger.info("Termina el paso: Busca un vuelo");
	}
	
	public void validarPaginaBusquedaVuelos() {
		logger.info("Comienza el paso: Valida que se muestra la pagina de buscar vuelo");
		esperarControlVisual(tituloPagina);
		String resultado = driver.findElement(By.id(tituloPagina)).getText();
		logger.info("Pantalla en la que estoy: "+resultado);
		int limite = 0;
		while(resultado.contains("Aguarde un instante")) {
			if(limite == 60) {
				assertTrue("Pasó 1 minuto y no cargó la pantalla, revisar los tiempos de respuesta", false);
			}
			logger.info("Aun sigue en proceso de cargar los vuelos en el intento: "+limite);
			waitFor(1).seconds();
			limite++;
			resultado = driver.findElement(By.id(tituloPagina)).getText();				
		}
		logger.info("Me encuentro en la pagina: "+resultado);
		if(!resultado.contains(tituloPaginaEsperada)) {
			driver.quit();
			assertTrue(String.format("No se observa el titulo esperado: %s , el aplicativo retorna el mensaje: %s",tituloPaginaEsperada,resultado), false);
		}
		waitFor(2).seconds();
		driver.quit();
		logger.info("Termina el paso: Valida que se muestra la pagina de buscar vuelo");
	}
		
	public void pulsarBotonID(String selector) {
		boolean resultado = false;
		int limite = 0;
		while(!resultado) {
			if(limite == 60) {
				assertTrue("Pasó 1 minuto y no se pudo realizar el click con el selector por id: "+selector, false);
			}
			try {
				driver.findElement(By.id(selector)).click();	
				logger.info("Pulse correctamente el selector: "+selector);
				resultado = true;
				break;
			}catch(Exception ex) {
				logger.info(String.format("Aun no se encuentra disponible el selector por id : %s en el intento %s",selector,limite));
				limite++;
			}
			
		}
	}
	
	public void pulsarBotonXPATH(String selector) {
		boolean resultado = false;
		int limite = 0;
		while(!resultado) {
			if(limite == 60) {
				assertTrue("Pasó 1 minuto y no se pudo realizar el click con el selector por xpath: "+selector, false);
			}
			try {
				driver.findElement(By.xpath(selector)).click();	
				logger.info("Pulse correctamente el selector: "+selector);
				resultado = true;
				break;
			}catch(Exception ex) {
				logger.info(String.format("Aun no se encuentra disponible el selector por xpath : %s en el intento %s",selector,limite));
				limite++;
			}
			
		}
	}
	
	public void esperarControlVisual(String selector) {
		boolean resultado = false;
		int limite = 0;
		while(!resultado) {
			if(limite == 60) {
				assertTrue("Pasó 1 minuto y no se visualiza el control: "+selector, false);
			}
			try {
				driver.findElement(By.id(selector)).isDisplayed();	
				logger.info("Se visualiza correctamente el selector: "+selector);
				resultado = true;
				break;
			}catch(Exception ex) {
				logger.info(String.format("Aun no se encuentra disponible el selector por xpath : %s en el intento %s",selector,limite));
				limite++;
			}
			
		}
	}
}