package satrack.com.avianca.steps;

import net.thucydides.core.annotations.Step;
import satrack.com.avianca.pageobject.AviancaPage;
	
public class AviancaSteps {

	AviancaPage aviancaPage;
			
	@Step
	public void cargarAPPAviancaStep(String dispositivo) {
		aviancaPage.ingresoSitioLibrosDisponibles(dispositivo);
	}
	
	@Step
	public void buscarVueloSoloIdaStep() {
		aviancaPage.buscarVueloSoloIda();
	}
	
	@Step
	public void seleccionarPrimerOrigenStep() {
		aviancaPage.seleccionarPrimerOrigen();
	}
	
	@Step
	public void seleccionarPrimerDestinoStep() {
		aviancaPage.seleccionarPrimerDestino();
	}
	
	@Step
	public void seleccionarFechaActualVueloStep() {
		aviancaPage.seleccionarFechaActualVuelo();
	}
	
	@Step
	public void buscarVueloStep() {
		aviancaPage.buscarVuelo();
	}
	
	@Step
	public void validarPaginaBusquedaVuelosStep() {
		aviancaPage.validarPaginaBusquedaVuelos();
	}
}

