package satrack.com.login.pageobject;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import satrack.com.utilidades.SatrackFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertTrue;
import net.serenitybdd.core.pages.PageObject;

@DefaultUrl("http://sahitest.com/demo/training/login.htm")
public class LoginPage extends PageObject{
	
	protected static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	SatrackFunctions funcionesGenerales = new SatrackFunctions();
	 				
	@FindBy(name="user")
	public WebElementFacade txtUser;
	
	@FindBy(name="password")
	public WebElementFacade txtPassword;
	
	@FindBy(css=".button")
	public WebElementFacade btnLogin;
			
	@FindBy(xpath="//b[text()='Sahi Training Site']")
	public WebElementFacade lblLogin;
	
	
	public void ingresoSitioLogin() {
		logger.info("Comienza el paso: Ingreso al sitio");
		open();
		logger.info("Termina el paso: Ingreso al sitio");
	}
		
	public void escribirCredenciales(String usuario,String clave) {
		logger.info(String.format("Comienza el paso: Escribe el %s y la %s",usuario,clave));		
		funcionesGenerales.escribirCampo(txtUser,"test");
		logger.info("Escribi el campo usuario");
		Serenity.takeScreenshot();
		funcionesGenerales.escribirCampo(txtPassword,"secret");
		logger.info("Escribi el campo clave");
		logger.info(String.format("Termina el paso: Escribe el %s y la %s",usuario,clave));
	}
	
	public void iniciarSesion() {
		logger.info("Comienza el paso: Pulsa el botón Login");
		funcionesGenerales.pulsarBoton(btnLogin);
		logger.info("Termina el paso: Pulsa el botón Login");
	}
	
	public void validarInicioSesionExitoso() {
		logger.info("Comienza el paso: Valida inicio de sesion exitoso");
		if(lblLogin.isPresent()) {
			assertTrue("Aun se encuentra en la página del login, revise las credenciales de acceso", false);
		}
		logger.info("Termina el paso: Valida inicio de sesion exitoso");
	}	
}