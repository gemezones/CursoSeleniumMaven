package Utilitarios;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class UtilitariosButton {
	//submit que tenga este evento.
	public static void btn_submit (WebElement button, WebDriver driver, ITestResult testResult) {
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));
		try {
			button.submit();
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}
	}
	//hacer clic al botón
	public static void btn_clic(WebElement button, WebDriver driver, ITestResult testResult) {
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));
		try {
			button.click();	
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
			System.out.println("Error no se encontro objeto para ahcer Click.");
		}
	}
	//hacer clic al botón con JS
	public static void btn_clicConJS(WebElement button, WebDriver driver, ITestResult testResult){
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));

		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver; 
			executor.executeScript("arguments[0].click();", button); 
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}
	} 
	
	public static void btn_clicMN(WebElement button, WebDriver driver, ITestResult testResult) {
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.elementToBeClickable(button));
		try {
			button.click();	
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}
	}
	
	public static void btn_clicConJSMN(WebElement button, WebDriver driver, ITestResult testResult){
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.elementToBeClickable(button));

		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver; 
			executor.executeScript("arguments[0].click();", button); 
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}
	} 
	
	public static void btn_mouserOver(WebElement button, WebDriver driver, ITestResult testResult){
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));

		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver; 
			String strJavaScript = "var element = arguments[0];"
		            + "var mouseEventObj = document.createEvent('MouseEvents');"
		            + "mouseEventObj.initEvent( 'mouseover', true, true );"
		            + "element.dispatchEvent(mouseEventObj);";
			
			executor.executeScript(strJavaScript, button); 
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}
	} 
	
	public static void btn_escrolerclic(WebElement button, WebDriver driver, ITestResult testResult) {	
		new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));
		try {
		
    	Actions escrolear = new Actions (driver);
    	escrolear.moveToElement(button).click();
    	
		}catch (Exception e){
			UtilitariosReporte.stackTracePrintException(e,testResult);
		}
    	    	
	}
	
	//hacer clic al botón
		public static void btn_doubleclic(WebElement button, WebDriver driver, ITestResult testResult) {
			new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));
			try {
				Actions action = new Actions(driver);
				action.doubleClick(button).perform();
			}catch (Exception e){
				UtilitariosReporte.stackTracePrintException(e,testResult);
			}
		}
		
		public static void btn_OnMouseOverJS(WebElement button, WebDriver driver, ITestResult testResult){
	          new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));

	          try {
	                
	                 String javaScript = "var evObj = document.createEvent('MouseEvents');" +
	                   "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
	                   "arguments[0].dispatchEvent(evObj);";
	                 JavascriptExecutor executor = (JavascriptExecutor)driver;
	                 executor.executeScript(javaScript, button);
	          }catch (Exception e){
	                 UtilitariosReporte.stackTracePrintException(e,testResult);
	          }
	   }
	
		public static void escroleaHastabtn(WebElement button, WebDriver driver, ITestResult testResult) {	
			new WebDriverWait(driver, Constants.tiempo_segundosEspera).until(ExpectedConditions.visibilityOf(button));
	    	Actions escrolear = new Actions (driver);
	    	escrolear.moveToElement(button);
		}
		
		
	      
	
}
