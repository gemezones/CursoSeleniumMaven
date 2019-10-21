package Utilitarios;

import org.testng.Assert;
import org.testng.ITestResult;

public class UtilitariosReporte {
	  public static void cargarMensajeResultadoTestOk(ITestResult testResult , String mensaje , boolean ok){
			testResult.setAttribute("message", mensaje);
			Assert.assertTrue(ok, mensaje);
	  }
	  public static void cargarMensajeResultadoTestNok(ITestResult testResult , String mensaje ){
			testResult.setAttribute("message", mensaje);
			//Assert.assertTrue(false, mensaje);
			Assert.fail( mensaje);
	  }
	  
		public static void  stackTracePrintException(Exception e, ITestResult testResult) {
    	    StackTraceElement[] stack = e.getStackTrace();
    	    String exception = "<pre>";
    	    if(e.getMessage() !=null) {
    	    	exception = exception+e.getMessage()+"<br>";
    	    }
    	    for (StackTraceElement s : stack) {
    	        exception = exception + s.toString() + "<br>";
    	    }
    	    exception =  exception + "</pre>";
			Assert.fail(exception); 
			testResult.setAttribute("message",exception);
			e.printStackTrace();
			
		}
		public static void throwException(String mensaje) throws Exception {
			throw new Exception (mensaje); 
		}
	  
}
