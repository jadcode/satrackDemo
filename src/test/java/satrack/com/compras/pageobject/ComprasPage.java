package satrack.com.compras.pageobject;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import satrack.com.utilidades.SatrackFunctions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.serenitybdd.core.pages.PageObject;

public class ComprasPage extends PageObject{
	
	protected static final Logger logger = LoggerFactory.getLogger(ComprasPage.class);
	SatrackFunctions funcionesGenerales = new SatrackFunctions();
	Map<String, Integer> librosCompras = new HashMap<String, Integer>();

	@FindBy(xpath="//h2[text()='All available books']")
	public WebElementFacade lblPage;
		
	@FindBy(xpath="//input[@value='Add']")
	public WebElementFacade btnAdicionar;
		
	@FindBy(id="total")
	public WebElementFacade txtTotalSitio;
	
	@FindBy(xpath="//table[@id='added']//td[2]")
	public List<WebElementFacade> totalLibrosCompraSistema;
	
	
	public void ingresoSitioLibrosDisponibles() {
		logger.info("Inicia el paso: Ingresa a la página de libros disponibles");			
		if(lblPage.isPresent()) {
			logger.info("Se observa la lista de libros disponibles");
		}else {
			assertTrue("Error, no se observa el titulo de libros", false);
		}	
		logger.info("Termina el paso: Ingresa a la página de libros disponibles");
	}
			
	public void diligenciarCantidadLibrosYTitulo(String cantidadLibros, String tituloLibro) {
		logger.info(String.format("Inicia el paso: Ingresa la cantidad de libros %s del titulo %s",cantidadLibros,tituloLibro));		
		String stock = find(By.xpath(String.format("//td[text()='%s']/following-sibling::td[1]",tituloLibro))).getText();
		if(Integer.parseInt(cantidadLibros) < Integer.parseInt(stock)) {			
			logger.info("Si hay disponibilidad en la tienda para comprar los libros solicitados del titulo: "+tituloLibro);
			funcionesGenerales.escribirCampo(String.format("//td[text()='%s']/following-sibling::td/input",tituloLibro),cantidadLibros);
			librosCompras.put(tituloLibro, Integer.parseInt(cantidadLibros));
		}else {
			assertTrue(String.format("Error, no se puede comprar la cantidad de libros solicitada %s porque solo hay en stock %s libros del titulo solicitado %s",cantidadLibros,stock,tituloLibro), false);
		}	
		logger.info(String.format("Termina el paso: Ingresa la cantidad de libros %s del titulo %s",cantidadLibros,tituloLibro));
	}
		
	public void pulsarAdicionarLibro() {
		logger.info("Inicia el paso: Pulsa el botón adicionar");
		funcionesGenerales.pulsarBoton(btnAdicionar);
		logger.info("Termina el paso: Pulsa el botón adicionar");
	}
	
	public void verificarSumaCompra() {
		logger.info("Inicia el paso: Verifica que la suma total a pagar sea correcta");
		logger.info("Libros a comprar: "+librosCompras);
		List<Integer> totalCompra = new ArrayList<>();		
		Integer sumatoriaLibrosIngresados = librosCompras.values().stream()               
		  .collect(Collectors.summingInt(Integer::intValue));
		logger.info("Suma a comprar: "+sumatoriaLibrosIngresados);		
		Integer sumatoriaLibrosSistema = totalLibrosCompraSistema.stream().map(x -> x.getText())
	        	    .filter((s) -> s.matches("\\d+"))
	        	    .mapToInt(Integer::valueOf)
	        	    .sum();
		logger.info("Suma calculada por el sistema: "+sumatoriaLibrosSistema);		
		if(sumatoriaLibrosIngresados.equals(sumatoriaLibrosSistema)) {
			logger.info("Cantidad de libros calculada por el sistema exitosamente");
		}else {
			assertTrue(String.format("Error, la cantidad de libros solicitada es de %s y la entregada por el sistema es %s",sumatoriaLibrosIngresados,sumatoriaLibrosSistema), false);
		}		
		totalCompra = validarCalculosLibros();
		Integer totalAPagar = traerTotalCompra(totalCompra);				
		Integer granTotal = Integer.parseInt(txtTotalSitio.getAttribute("value"));
		logger.info("Total de dinero que el sistema notifica que debo pagar: "+granTotal);			
		if(totalAPagar.equals(granTotal)) {
			logger.info("Total calculado de forma exitosa");
		}		
		logger.info("Termina el paso: Verifica que la suma total a pagar sea correcta");		
	}
	
	public List<Integer> validarCalculosLibros() {
		logger.info("Comienza subproceso de calcular el total libros y precios por titulo");
		List<Integer> totalCompra = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : librosCompras.entrySet()) {
		    logger.info("Titulo del libro=" + entry.getKey() + ", Cantidad a comprar=" + entry.getValue());
		    String cantidadAprovisionada = find(By.xpath("//table[@id='added']//td[text()='"+entry.getKey()+"']/following-sibling::td[1]")).getText();
		    String valorUnitario = find(By.xpath("//table[@id='added']//td[text()='"+entry.getKey()+"']/following-sibling::td[2]")).getText().replaceAll("[^\\d]", "");
		    String TotalCostoLibrosTitulo = find(By.xpath("//table[@id='added']//td[text()='"+entry.getKey()+"']/following-sibling::td[3]")).getText().replaceAll("[^\\d]", "");
		    if(entry.getValue().toString().equals(cantidadAprovisionada)) {
		    	logger.info(String.format("Cantidad aprovisionada de libros del titulo %s exitosa",entry.getKey()));
		    	Integer valorCalculadoTitulo = Integer.parseInt(valorUnitario) * entry.getValue();
		    	if(valorCalculadoTitulo.equals(Integer.parseInt(TotalCostoLibrosTitulo))) {
		    		logger.info(String.format("Valor de los libros por el titulo %s calculado exitosamente",entry.getKey()));
		    		totalCompra.add(valorCalculadoTitulo);
		    	}
		    }
		}
		logger.info("Termina subproceso de calcular el total libros y precios por titulo");
		return totalCompra;
	}
	public Integer traerTotalCompra(List<Integer> totalCompraValores) {
		logger.info("Comienza subproceso de calcular el total a pagar esperado");
		Integer totalAPagar = 0;
		if(totalCompraValores.size() < 1) {
			assertTrue("Error, la lista de compras viene vacia", false);
		}else {
			totalAPagar = totalCompraValores.stream()
					  .mapToInt(Integer::valueOf)
					  .sum();		
		}
		logger.info("Total de dinero que debo pagar como usuario: "+totalAPagar);
		logger.info("Termina subproceso de calcular el total a pagar esperado");
		return totalAPagar;		
	}
}