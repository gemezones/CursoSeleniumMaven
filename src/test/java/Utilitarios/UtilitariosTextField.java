package Utilitarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class UtilitariosTextField {

	// TextField - Campo de texto
	//Limpiar campo de texto
	public static void txtField_clean (WebElement textField, WebDriver driver, ITestResult testResult ){
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(textField));
		try {
			textField.clear();	
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}				
	}
	//Llenar campo de texto sin JS
	public static void txtField_putString (String valor,WebElement textField, WebDriver driver, ITestResult testResult ){
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(textField));
		try {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;		
	        js.executeScript("arguments[0].scrollIntoView(true);", textField);
			textField.sendKeys(valor);	 
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}	
	}
	//Llenar campo de texto con JS
	public static void txtField_putStringJS (String valor,WebElement textField, WebDriver driver, ITestResult testResult ){
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.elementToBeClickable(textField));
		try {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;		
	        js.executeScript("arguments[0].scrollIntoView(true);", textField);
	        js.executeScript("arguments[0].value='"+valor+"';", textField);
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}	
	}
	//Obtener campo de texto sin JS
		public static String txtField_getString(WebElement textField, WebDriver driver, ITestResult testResult ){
			new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.elementToBeClickable(textField));
			String mensajeRetorno=new String();
			try 
			{				
				JavascriptExecutor js = (JavascriptExecutor) driver;		
		        js.executeScript("arguments[0].scrollIntoView(true);", textField);
		        mensajeRetorno=textField.getText();	 
		        
			}catch (Exception e)
			{
				UtilitariosReporte.stackTracePrintException(e,testResult);
			}	
			return mensajeRetorno;			
		}	
}
