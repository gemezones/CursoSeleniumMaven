package Utilitarios;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

import ConfiguracionBase.ConfigBase;

public class GetScreenShot extends ConfigBase{
   
    
	//static String bitmapPath = System.getProperty("user.dir")+"\\recursos\\pantallazos\\";	
	//static String bitmapPath = "D:\\George\\WorkSpace\\pcc\\src\\resources\\pantallazos\\";
	static String bitmapPath = prop.getProperty("pantallazos");
	
	
	
	
	public static String getBitmapPath() {
		return bitmapPath;
	}

	public static void setBitmapPath(String bitmapPath) {
		GetScreenShot.bitmapPath = bitmapPath;
	}

	//Capturar como imagen en formato 64 para embeberlo en el html del reporte
    public static String captureOKBase64(WebDriver driver) throws IOException
    {	
        TakesScreenshot ts = (TakesScreenshot)driver;                     
        String screenshotBase64 = "data:image/png;base64,"+ts.getScreenshotAs(OutputType.BASE64);
        return screenshotBase64;
    }
    
    public static void capturarPantallaBase64(WebDriver driver) {
		 //Agregando el código para guardar la imagen que grabó correctamente la solicitud.
			String screenShotPath64;
			//Toma la foto
			try {
				screenShotPath64 = GetScreenShot.captureOKBase64(driver);
				Images.listadoImagenesTemporales.add(screenShotPath64);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Agrega la foto al Test
			for (int i = 0; i<Images.listadoImagenesTemporales.size();i++ ){
				try {
					ExtentUtil.fetchTest().addScreenCaptureFromPath( Images.listadoImagenesTemporales.get(i).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			 Images.limpiarListadoImagenes();
    	
    }
    
    
    //Capturar como imagen en el proyecto (en local)
    public static File captureOKFile(WebDriver driver) throws IOException
    {	
        TakesScreenshot ts = (TakesScreenshot)driver;                        
        return ts.getScreenshotAs(OutputType.FILE);
    }
    
    public static void capturarPantallaFile(WebDriver driver, String fileName) throws InterruptedException {
		 //Agregando el código para guardar la imagen que grabó correctamente la solicitud.
			File pantallazo;
			//Toma la foto
			try {
				pantallazo = GetScreenShot.captureOKFile(driver);	
				
				Date date = new Date();
		        DateFormat hourdateFormat = new SimpleDateFormat("HHmmss");
		        String datetime = hourdateFormat.format(date);
				
				FileUtils.copyFile(pantallazo, new File (bitmapPath +datetime+ fileName));
				Images.listadoImagenesTemporales.add(bitmapPath +datetime+fileName);
				
				
				
				Thread.currentThread().sleep(5*1000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Agrega la foto al Test
			for (int i = 0; i<Images.listadoImagenesTemporales.size();i++ ){
				try {
					ExtentUtil.fetchTest().addScreenCaptureFromPath( Images.listadoImagenesTemporales.get(i).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			 Images.limpiarListadoImagenes();
    	
    }
    
  
    
}