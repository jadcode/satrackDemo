package satrack.com.errorpage.definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import satrack.com.errorpage.steps.ErrorPageSteps;

public class ErrorPageDefinition {

	@Steps
	ErrorPageSteps errorSteps;
	
	@Given("^Ingresa al sitio de Sahi Tests$") 
	public void ingresoSitioSahiTest(){
		errorSteps.ingresoSitioSahiTestStep(); 
	}	
			
	@When("^Selecciona la opción \"([^\"]*)\"$")
	public void seleccionarOpcionPruebaSitio(String option){
		errorSteps.seleccionarOpcionPruebaSitioStep(option);
	}	
		
	@And("^Selecciona el error a validar \"([^\"]*)\"$") 
	public void seleccionarErrorValidar(String error){
		errorSteps.seleccionarErrorValidarStep(error); 
	}			
	
	@Then("^Valida que muestra el error esperado$") 
	public void verificarErrorEsperado(){
		errorSteps.verificarErrorEsperadoStep(); 
	}

	
}
