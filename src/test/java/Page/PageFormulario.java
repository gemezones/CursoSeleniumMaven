package Page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import ConfiguracionBase.ConfigBase;
import Bean.BeanFormulario;
import Utilitarios.GetScreenShot;
import Utilitarios.UtilitariosButton;
import Utilitarios.UtilitariosTextField;

public class PageFormulario extends ConfigBase
{

	@FindBy(name  = "entry.2005620554")  
	WebElement inputnombreApellido;
	
	@FindBy(name  = "entry.1140037502")  
	WebElement inputcorreo;

	@FindBy(xpath  = "//div[@class='quantumWizTextinputPaperinputEl freebirdThemedInput freebirdFormviewerViewItemsDateDateInput modeLight']/div[@class='quantumWizTextinputPaperinputMainContent exportContent']/div[@class='quantumWizTextinputPaperinputContentArea exportContentArea']/div[@class='quantumWizTextinputPaperinputInputArea']/input[@class='quantumWizTextinputPaperinputInput exportInput']")
	WebElement inputfechaNacimientoSinFecha;

	@FindBy(xpath  = "//div[@class='quantumWizTextinputPaperinputEl freebirdThemedInput freebirdFormviewerViewItemsDateDateInput modeLight hasValue']/div[@class='quantumWizTextinputPaperinputMainContent exportContent']/div[@class='quantumWizTextinputPaperinputContentArea exportContentArea']/div[@class='quantumWizTextinputPaperinputInputArea']/input[@class='quantumWizTextinputPaperinputInput exportInput']")
	WebElement inputfechaNacimientoConFecha;

	//@FindBy(xpath  = "")
	//WebElement inputSexoHombre;
	
	@FindBy(xpath  = "//span[contains(text(),'Siguiente')]")
	WebElement botonSiguiente;
	
	//@FindBy(xpath  = "")
	//WebElement checkHobbies;
	
	
	@FindBy(xpath  = "//div[@class='quantumWizMenuPaperselectOption freebirdThemedSelectOptionDarkerDisabled exportOption isSelected isPlaceholder']")
	WebElement comboDistrito;
	
	//@FindBy(xpath  = "")
	//WebElement comboValor;
	//actualizado desde Git Hub
	@FindBy(xpath  = "//span[contains(text(),'Enviar')]")
	WebElement enviar;
	//Actulizado desde GitHub
	public 	PageFormulario()
	{
		  String baseUrl = "https://docs.google.com/forms/d/e/1FAIpQLSfzLAD7DNf6fz1tu_gtbuyDZVbpSbeR_PNS3zOtlj416VZ1jA/viewform";
		  PageFactory.initElements(driver, this);  // si no se inicializan los WebElement da error de null pointer exception
		  driver.get(baseUrl);
	}
	
	public void registrarFormulario( ITestResult testResult,BeanFormulario beanFormulario) throws InterruptedException, IOException 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//************************Primera Pestaña*************************************************
			UtilitariosTextField.txtField_putString(beanFormulario.getNombreApellido(), inputnombreApellido, driver, testResult);
			UtilitariosTextField.txtField_putString(beanFormulario.getCorreo(), inputcorreo, driver, testResult);
			
			
			js.executeScript("arguments[0].scrollIntoView();", inputnombreApellido);
			
			GetScreenShot.capturarPantallaFile(driver, "-IngresoNombreYCorreo.png");
			Thread.sleep(3000);
			UtilitariosTextField.txtField_putString(beanFormulario.getFechaNacimiento(), inputfechaNacimientoSinFecha, driver, testResult);			
			WebDriverWait myWaitVar = new WebDriverWait(driver, 40);			
			myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+beanFormulario.getSexo()+"')]") ));
			WebElement sexo = driver.findElement(By.xpath("//span[contains(text(),'"+beanFormulario.getSexo()+"')]"));
			sexo.click();
			myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='quantumWizTextinputPaperinputEl freebirdThemedInput freebirdFormviewerViewItemsDateDateInput modeLight hasValue']/div[@class='quantumWizTextinputPaperinputMainContent exportContent']/div[@class='quantumWizTextinputPaperinputContentArea exportContentArea']/div[@class='quantumWizTextinputPaperinputInputArea']/input[@class='quantumWizTextinputPaperinputInput exportInput']") ));
			js.executeScript("arguments[0].scrollIntoView();", inputfechaNacimientoConFecha);
			
			GetScreenShot.capturarPantallaFile(driver, "-IngresoFechaNacimientoYSexo.png");
			Thread.sleep(3000);
			UtilitariosButton.btn_clic(botonSiguiente, driver, testResult);		
		//************************Segunda Pestaña*************************************************		
			myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+beanFormulario.getHobbies()+"')]") ));
			WebElement Check = driver.findElement(By.xpath("//span[contains(text(),'"+beanFormulario.getHobbies()+"')]"));
			Check.click();
			myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Seleccionar Hobbies')]") ));
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[contains(text(),'Seleccionar Hobbies')]")));
			
			GetScreenShot.capturarPantallaFile(driver, "-SeleccionHobbies.png");
			Thread.sleep(3000);
			UtilitariosButton.btn_clic(comboDistrito, driver, testResult);			
			myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='exportSelectPopup quantumWizMenuPaperselectPopup']/div[@data-value='"+beanFormulario.getDistrito()+"']") ));		
			WebElement valorDistrito = driver.findElement(By.xpath("//div[@class='exportSelectPopup quantumWizMenuPaperselectPopup']/div[@data-value='"+beanFormulario.getDistrito()+"']"));
			valorDistrito.click();
			
			myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Seleccionar Distrito')]") ));
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[contains(text(),'Seleccionar Distrito')]")));
			
			GetScreenShot.capturarPantallaFile(driver, "-SeleccionDistrito.png");
			Thread.sleep(3000);	
			UtilitariosButton.btn_clic(enviar,driver, testResult);
	}
}
