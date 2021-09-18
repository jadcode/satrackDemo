package satrack.com.errorpage.steps;

import net.thucydides.core.annotations.Step;
import satrack.com.errorpage.pageobject.ErrorPage;

	
public class ErrorPageSteps {

	ErrorPage errorPage;
			
	@Step
	public void ingresoSitioSahiTestStep() {
		errorPage.ingresoSitioSahiTest();
	}
	
	@Step
	public void seleccionarOpcionPruebaSitioStep(String option) {		
		errorPage.seleccionarOpcionPruebaSitio(option);
	}
	
	@Step
	public void seleccionarErrorValidarStep(String error) {		
		errorPage.seleccionarErrorValidar(error);
	}
	
	@Step
	public void verificarErrorEsperadoStep() {
		errorPage.verificarErrorEsperado();
	}
		
}

