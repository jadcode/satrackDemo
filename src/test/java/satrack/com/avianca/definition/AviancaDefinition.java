package satrack.com.avianca.definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import satrack.com.avianca.steps.AviancaSteps;

public class AviancaDefinition {

	@Steps
	AviancaSteps aviancaSteps;
	
	@Given("^Ingresa a la app de avianca en el \"([^\"]*)\"$") 
	public void cargarAPPAvianca(String dispositivo){
		aviancaSteps.cargarAPPAviancaStep(dispositivo); 
	}	
		
	@When("^Busca un vuelo de solo ida$") 
	public void buscarVueloSoloIda(){
		aviancaSteps.buscarVueloSoloIdaStep(); 
	}
	
	@And("^Selecciona el primer aeropuerto de origen$") 
	public void seleccionarPrimerOrigen(){
		aviancaSteps.seleccionarPrimerOrigenStep(); 
	}	
	
	@And("^Selecciona el primer aeropuerto de destino$") 
	public void seleccionarPrimerDestino(){
		aviancaSteps.seleccionarPrimerDestinoStep(); 
	}	
	
	@And("^Selecciona la fecha actual para el vuelo$") 
	public void seleccionarFechaActualVuelo(){
		aviancaSteps.seleccionarFechaActualVueloStep(); 
	}	
	
	@And("^Busca un vuelo$") 
	public void buscarVuelo(){
		aviancaSteps.buscarVueloStep(); 
	}
	
	@Then("^Valida que se muestra la pagina de buscar vuelo$") 
	public void validarPaginaBusquedaVuelos(){
		aviancaSteps.validarPaginaBusquedaVuelosStep(); 
	}
}
