package satrack.com.compras.steps;

import net.thucydides.core.annotations.Step;
import satrack.com.compras.pageobject.ComprasPage;

	
public class ComprasSteps {

	ComprasPage compraPage;
			
	@Step
	public void ingresoSitioLibrosDisponiblesStep() {
		compraPage.ingresoSitioLibrosDisponibles();
	}
	
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
	
}

