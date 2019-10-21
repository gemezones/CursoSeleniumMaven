package Test;

import org.testng.annotations.Test;


import Bean.BeanFormulario;
import ConfiguracionBase.ConfigBase;

import org.testng.annotations.BeforeTest;
import Page.PageFormulario;
import Utilitarios.UtilitariosEvidencias;
import Utilitarios.UtilitariosIO;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

public class TestFormulario extends ConfigBase {
	ITestResult testResult ;
  @BeforeTest
  public void setUp() 
	{
		inicializacion();
	}

  @Test(dataProvider="getTestData")
  public void RegistrarFormulario(String nombreApellido, String email, String fechaNacimiento, String sexo,String hobbies, String distrito,String casoPrueba)  throws IOException, InterruptedException,Exception
  {
	  final ITestResult testResult = Reporter.getCurrentTestResult();
	  PageFormulario pageformulario = new PageFormulario();
	  pageformulario.registrarFormulario(testResult,cargarBean(nombreApellido,email,fechaNacimiento,sexo,hobbies,distrito,casoPrueba));
	  UtilitariosEvidencias.TakeScreenShoot(casoPrueba);
  }
  
  public BeanFormulario cargarBean(String nombreApellido, String email, String fechaNacimiento, String sexo,String hobbies, String distrito, String casoPrueba) throws InterruptedException ,Exception
  {
	  BeanFormulario beanFormulario = new BeanFormulario();	  
	  beanFormulario.setNombreApellido(nombreApellido);
	  beanFormulario.setCorreo(email);
	  beanFormulario.setFechaNacimiento(fechaNacimiento);
	  beanFormulario.setSexo(sexo);
	  beanFormulario.setHobbies(hobbies);
	  beanFormulario.setDistrito(distrito);
	  beanFormulario.setCasoPrueba(casoPrueba);
	  return beanFormulario;
  }
  
  @AfterTest
  public void tearDown() throws InterruptedException ,Exception
	{				
		driver.close();
	}

  @DataProvider(name="getTestData")
	public static Object[][] datosPoblados() throws InterruptedException ,Exception{
		
		Object[][] datos = null;
		datos=UtilitariosIO.obtenerDataPrueba(prop.getProperty("rutaDataExcel"), "Data");	
		return datos;
		
	}
  
}
