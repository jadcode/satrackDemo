package satrack.com.compras.definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import satrack.com.compras.steps.ComprasSteps;

public class ComprasDefinition {

	@Steps
	ComprasSteps compraSteps;
	
	@Given("^Ingresa a la página de libros disponibles$") 
	public void ingresoSitioLibrosDisponibles(){
		compraSteps.ingresoSitioLibrosDisponiblesStep(); 
	}	
			
	@When("^Ingresa la \"([^\"]*)\" del \"([^\"]*)\"$")
	public void diligenciarCantidadLibrosYTitulo(String cantidadLibros, String tituloLibro){
		compraSteps.diligenciarCantidadLibrosYTituloStep(cantidadLibros,tituloLibro);
	}	
		
	@And("^Pulsa el botón adicionar$") 
	public void pulsarAdicionarLibro(){
		compraSteps.pulsarAdicionarLibroStep(); 
	}			
	
	@Then("^Verifica que la suma total a pagar sea correcta$") 
	public void verificarSumaCompra(){
		compraSteps.verificarSumaCompraStep(); 
	}

	
}
