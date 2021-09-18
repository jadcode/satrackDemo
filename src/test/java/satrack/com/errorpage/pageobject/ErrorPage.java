package satrack.com.errorpage.pageobject;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import satrack.com.utilidades.SatrackFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.assertTrue;
import net.serenitybdd.core.pages.PageObject;

@DefaultUrl("http://sahitest.com/demo/")
public class ErrorPage extends PageObject{
	
	protected static final Logger logger = LoggerFactory.getLogger(ErrorPage.class);
	SatrackFunctions funcionesGenerales = new SatrackFunctions();
	Integer codigoErrorEsperado = 0;
	 				
	@FindBy(css=".error-code[jscontent='errorCode']")
	public WebElementFacade lblError;
		
	public void ingresoSitioSahiTest() {
		logger.info("Comienza el paso: Ingresa al sitio de Sahi Tests");
		open();
		logger.info("Termina el paso: Ingresa al sitio de Sahi Tests");
	}
		
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
}