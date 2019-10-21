package ConfiguracionBase;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class ConfigBase {
	public static WebDriver driver;
	public static Properties prop;
	public ConfigBase()
	{
		try {
			
			prop= new Properties();
			InputStream ip = ConfigBase.class.getClassLoader().getResourceAsStream("ConfiguracionBase/config.properties");
			prop.load(ip);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void inicializacion() 
	{	
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver76\\chromedriver.exe");	
			driver = new ChromeDriver(); //abre el navegador			
			driver.manage().window().maximize(); 
			driver.manage().deleteAllCookies();  
			driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);// PageLoadTimeOut, espera X segundos  para que se muestra la página web (url).		
			//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);  //Espera implicita para cada objeto

		}
		catch (Exception e)
		{
			System.out.println("Se Produjo el Error "+e.hashCode()+ "-"+e.getMessage());
		}
	
	}

}
