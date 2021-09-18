package satrack.com.avianca.steps;

import net.thucydides.core.annotations.Step;
import satrack.com.avianca.pageobject.AviancaPage;
import satrack.com.compras.pageobject.ComprasPage;

	
public class AviancaSteps {

	AviancaPage aviancaPage;
			
	@Step
	public void cargarAPPAviancaStep() {
		aviancaPage.ingresoSitioLibrosDisponibles();
	}
	
	/*
	@Step
	public void diligenciarCantidadLibrosYTituloStep(String cantidadLibros, String tituloLibro) {		
		compraPage.diligenciarCantidadLibrosYTitulo(cantidadLibros,tituloLibro);
	}
	
	@Step
	public void pulsarAdicionarLibroStep() {
		compraPage.pulsarAdicionarLibro();
	}
			
	@Step
	public void verificarSumaCompraStep() {
		compraPage.verificarSumaCompra();
	}
	*/
}

