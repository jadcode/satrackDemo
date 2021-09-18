package com.satrack.runners;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import satrack.com.utilidades.BeforeSuite;
import satrack.com.utilidades.DataToFeature;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@CucumberOptions(features ="src/test/resources/feature/",glue = "satrack.com",
tags = {"@Compra"}, 
monochrome= true)
@RunWith(CucumberWithSerenity.class)
public class RunnerCompras {

	@BeforeSuite
	public static void test() throws InvalidFormatException, IOException {		
		DataToFeature.overrideFeatureFiles("./src/test/resources/feature/");
		}
	public static void main(String[] args) {
		
	}
}
 



