package satrack.com.utilidades;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static java.lang.String.format;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import com.google.common.base.Joiner;
import com.google.common.collect.Table.Cell;
import com.ibm.icu.impl.Row;
import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import io.appium.java_client.windows.WindowsDriver;
import io.restassured.RestAssured;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException; 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
@SuppressWarnings("unused")
public class SatrackFunctions extends PageObject {	
	static final Logger logger = LoggerFactory.getLogger(SatrackFunctions.class);	
	
	/**
	 * Metodo general para escribir en un campo resaltando el campo para ser visible en el informe
	 * @param ElementoWeb recibe un string con el selector xpath del campo
	 * @param textoEscribir recibe el texto a escribir en el campo
	 * @author JONY
	 */
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
						logger.info("Escribi el texto en el campo de forma exitosa");
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
		/**
		 * Metodo general para escribir en un campo resaltando el campo para ser visible en el informe
		 * @param ElementoWeb recibe un webelementfacade
		 * @param textoEscribir recibe el texto a escribir en el campo
		 * @author JONY
		 */
		public void escribirCampo(WebElementFacade ElementoWeb,String textoEscribir) {
			logger.info("Comienzo la escritura del texto: "+textoEscribir);
			if(ElementoWeb.isPresent()) {
				int limite = 0;
				while (limite <= 10) {
					try {
						((JavascriptExecutor)getDriver()).executeScript("arguments[0].style.border='3px solid red'", ElementoWeb);
						ElementoWeb.clear();
						ElementoWeb.sendKeys(textoEscribir,Keys.TAB);
						break;
					}catch(Exception ex) {
						assertTrue(String.format("No es posible escribir en el campo porque se presenta el siguiente error: %s",ex), false);
					}
				}
				if(limite == 11) {
					assertTrue(String.format("No fue posible escribir en el campo luego de pasar 10 intentos"), false);
				}
			}else {
				assertTrue("No se encuentra presente el campo", false);
			}
			logger.info("Termino la escritura del texto: "+textoEscribir);
		}
		/**
		 * Metodo general para hacer click a un boton
		 * @param ElementoWeb recibe un webelementFacade
		 * @author JONY
		 */
		public void pulsarBoton(WebElementFacade ElementoWeb) {
			logger.info("Comienzo la pulsacion del boton");
			try {
				((JavascriptExecutor)getDriver()).executeScript("arguments[0].style.border='3px solid red'", ElementoWeb);
				ElementoWeb.click();				
			}catch(Exception ex) {
				assertTrue(String.format("No es posible pulsar el campo porque se presenta el siguiente error: %s",ex), false);
			}
		}
		/**
		 * Metodo general para hacer click a un boton
		 * @param ElementoWeb recibe el selector del boton
		 * @author JONY
		 */
		public void pulsarBoton(String ElementoWeb) {
			logger.info("Comienzo la pulsacion del boton");
			try {				
				WebElement elem = find(By.xpath(ElementoWeb));
				elem.click();				
			}catch(Exception ex) {
				assertTrue(String.format("No es posible pulsar el campo porque se presenta el siguiente error: %s",ex), false);
			}
		}
}	
	
	




		
		
		
		
	
