package satrack.com.login.pageobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.serenitybdd.core.pages.PageObject;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class General_PO extends PageObject{
	
protected static final Logger logger = LoggerFactory.getLogger(General_PO.class);
	
  public WebElement obtenerElemento(String xpath){    	
    	return find(By.xpath(xpath));
    }
        
    public void hacerScroll(){    	
    	JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,200)");    	
    }
    
	public void hacerClic(String xpath){		
		find(By.xpath(xpath)).click();
	}
		
	public void pasoVentana(){		
		String  handle= getDriver().getWindowHandle();
		getDriver().switchTo().window(handle);		
	}
			
	public void verificarMensaje(String xpath,String Mensaje2){		
	assertEquals(find(By.xpath(xpath)).getText(),Mensaje2);			
	}
	
	public void seleccionarOpcion(String xpath,String opcion){	       
        Select dropdown = new Select(getDriver().findElement(By.xpath(xpath)));
        dropdown.selectByVisibleText(opcion);
    }
	
	
	public void limpiarTexto(String xpath){		
		find(By.xpath(xpath)).clear();
	}
		
	public void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		
	
	public void escribirCampo(String xpath,String textoEscribir) {
		logger.info("Comienzo la escritura del campo: "+xpath);
		if(find(By.xpath(xpath)).isPresent()) {
			int limite = 0;
			while (limite <= 10) {
				try {
					WebElement elem = find(By.xpath(xpath));
					((JavascriptExecutor)getDriver()).executeScript("arguments[0].style.border='3px solid red'", elem);
					elem.clear();
					elem.sendKeys(textoEscribir,Keys.TAB);
					break;
				}catch(Exception ex) {
					assertTrue(String.format("No es posible escribir en el campo: %s porque se presenta el siguiente error: %s",xpath,ex), false);
				}
			}
			if(limite == 11) {
				assertTrue(String.format("No fue posible escribir en el campo: %s luego de pasar 10 intentos: %s",xpath), false);
			}
		}else {
			assertTrue("No se encuentra presente el campo: "+xpath, false);
		}
		logger.info("Termino la escritura del campo: "+xpath);
	}
}
