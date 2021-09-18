package satrack.com.login.steps;

import net.thucydides.core.annotations.Step;
import satrack.com.login.pageobject.LoginPage;

	
public class LoginSteps {

	LoginPage loginPage;
			
	@Step
	public void ingresoSitioLoginStep() {
		loginPage.ingresoSitioLogin();
	}
	
	@Step
	public void escribirCredencialesStep(String usuario,String clave) {		
		loginPage.escribirCredenciales(usuario,clave);
	}
	
	@Step
	public void iniciarSesionStep() {		
		loginPage.iniciarSesion();
	}
	
	@Step
	public void validarInicioSesionExitosoStep() {
		loginPage.validarInicioSesionExitoso();
	}
	
}

