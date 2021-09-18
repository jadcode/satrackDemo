package satrack.com.login.definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import satrack.com.login.steps.LoginSteps;

public class LoginDefinition {

	@Steps
	LoginSteps loginSteps;
	
	@Given("^Ingresa al sitio$") 
	public void ingresoSitioLogin(){
		loginSteps.ingresoSitioLoginStep(); 
	}	
	
	@When("^Escribe el \"([^\"]*)\" y la \"([^\"]*)\"$")
	public void escribirCredenciales(String usuario,String clave){
		loginSteps.escribirCredencialesStep(usuario,clave);
	}	
		
	@And("^Pulsa el botón Login$") 
	public void iniciarSesion(){
		loginSteps.iniciarSesionStep(); 
	}
		
	@Then("^Valida inicio de sesion exitoso$") 
	public void validarInicioSesionExitoso(){
		loginSteps.validarInicioSesionExitosoStep(); 
	}
	
}
