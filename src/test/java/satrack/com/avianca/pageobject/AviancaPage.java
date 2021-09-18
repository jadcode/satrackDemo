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
		
	@SuppressWarnings("rawtypes")
	public void ingresoSitioLibrosDisponibles() {
		logger.info("Comienza el paso: Ingresa al sitio de Sahi Tests");		
        try {        	
        	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), funcionesGenerales.traerDispositivo());        	
        }catch(Exception ex) {
        	logger.info("Existe un error al conectar con appium server: "+ex);
        }        
		logger.info("Termina el paso: Ingresa al sitio de Sahi Tests");
		saltarOpciones();
		driver.quit();
	}
	
	
	public void saltarOpciones() {
		logger.info("Proceso a saltar opciones de inicio");
		try {		
			pulsarBotonID(botonBuscar);
			pulsarBotonID(botonSoloIda);
			pulsarBotonID(botonOrigen);
			pulsarBotonXPATH(primerOrigen);		
			pulsarBotonID(botonDestino);
			pulsarBotonXPATH(primerDestino);
			pulsarBotonID(campoFechaVuelo);			
			pulsarBotonXPATH("//*[@resource-id='areamovil.aviancataca:id/day_number_text' and @text='"+funcionesGenerales.traerDiaActual()+"']");
			pulsarBotonID("areamovil.aviancataca:id/save_calendar_button");			
			pulsarBotonID("areamovil.aviancataca:id/search_flights_button");			
			esperarControlVisual("areamovil.aviancataca:id/webview_title");
			String resultado = driver.findElement(By.id("areamovil.aviancataca:id/webview_title")).getText();
			System.out.println(resultado);
			while(resultado.contains("Aguarde un instante")) {
				logger.info("Aun se encuentra cargando");
				waitFor(1).seconds();
				resultado = driver.findElement(By.id("areamovil.aviancataca:id/webview_title")).getText();				
			}
			System.out.println(resultado);
			
			
			logger.info("Logrado");
									
			
		}catch(Exception ex) {
			logger.info("Error: "+ex);
		}
	
		logger.info("Pulse el boton");
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
				//waitFor(2).seconds();
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
				//waitFor(2).seconds();
				break;
			}catch(Exception ex) {
				logger.info("Aun no se encuentra disponible el selector: "+selector);
			}
			
		}
	}
		
	/*
	public void seleccionarOpcionPruebaSitio(String option) {
		logger.info(String.format("Comienza el paso: Selecciona la opción %s",option));		
		funcionesGenerales.pulsarBoton("//a[text()='"+option+"']");
		logger.info(String.format("Termina el paso: Selecciona la opción %s",option));
	}
	
	public void seleccionarErrorValidar(String error) {
		logger.info("Comienza el paso: Selecciona el error a validar "+error);
		funcionesGenerales.pulsarBoton("//a[text()='"+error+"']");
		codigoErrorEsperado = Integer.parseInt(error.replaceAll("[^\\d]", ""));
		logger.info("Termina el paso: Selecciona el error a validar "+error);
	}
	
	public void verificarErrorEsperado() {
		logger.info("Comienza el paso: Valida que muestra el error esperado");		
		RestAssured.baseURI = "http://sahitest.com/demo/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get();
		int statusCode = response.getStatusCode();
		logger.info("El codigo de respuesta del sitio es: "+statusCode);
		if(statusCode != codigoErrorEsperado) {
			logger.info(String.format("El sitio responde un codigo de error distinto al esperado, el codigo esperado es %s y el retornado es %s, se procede a validar visualmente",codigoErrorEsperado,statusCode));
		}				
		if(!lblError.isPresent()) {
			assertTrue("No se observa el error esperado", false);
		}else {
			logger.info("Se observa el error esperado a nivel visual");
		}		
		logger.info("Termina el paso: Valida que muestra el error esperado");
	}	
	*/
}