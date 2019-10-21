package Utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class UtilitariosIO {

	static Workbook book;
	static Sheet sheet;
	//Convertir el archivo css a String
	 public static String retornarString () throws IOException{
		 
			BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\configuracionReporte\\customize.css")));
			String line;
			StringBuilder sb = new StringBuilder();
			
			while((line=br.readLine())!= null){ 
			    sb.append(line.trim());
			}	
			br.close();
			return sb.toString();
		}
	  public static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	  public static String desencriptarBase64 (String cadena){
		  String decodificar = "";
		  byte[] bytes;
			try {
				  bytes = cadena.getBytes("UTF-8");							  
				  byte[] decoded = Base64.getDecoder().decode(bytes);
				  decodificar = new String (decoded,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			  
		  return decodificar;	  
	  }
	  public static String devolverNombreCSV(String nombre_archivo) { 
		  String path = System.getProperty("user.dir")+"\\src\\main\\resources\\csv\\"+nombre_archivo+".csv";
		  return path;
	  }
	  public static void escribirCSV(Iterator iterator,String nombreCSV) throws IOException {
		    CSVWriter writer = new CSVWriter(new FileWriter(nombreCSV));
			while (iterator.hasNext()) {
				Map<String, Object> fila = (Map<String, Object>) iterator.next();
				Collection<String> coleccionLlaves = fila.keySet();
				Iterator iterator2 = coleccionLlaves.iterator();
				while (iterator2.hasNext()) {	
					Object llave = iterator2.next();
					Object valor =  fila.get(llave.toString());
					String []filaCSV = {llave.toString(),valor.toString()} ;
					writer.writeNext(filaCSV);						
				}
			}
			writer.close();
	  }
	  
	  public static void leerCSV(List<Map<String, Object>> listadoMapa , String nombreCSV) throws IOException {
		     Reader reader = new FileReader(nombreCSV);
			 CSVReader csvReader = new CSVReader(reader);
			 Iterator iterator = csvReader.iterator();
			 HashMap<String, Object> campos = new HashMap<String, Object>() ;
			 while (iterator.hasNext()) {
				 String []lista = (String[]) iterator.next();
				 Map<String, Object> fila = new HashMap<String, Object>();
				 //fila.put(, );
				 campos.put(lista[0], lista[1]);
			 }
			 listadoMapa.add(campos);
	  
	  }
		public static Object[][] obtenerDataPrueba(String rutaArchivo, String SheetName) {
			FileInputStream file = null;
			try {  
				file = new FileInputStream(rutaArchivo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try { 
					book = WorkbookFactory.create(file); 
			} catch (IOException e) {  
				e.printStackTrace(); 
			} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
				e.printStackTrace();
			}   
			sheet = book.getSheet(SheetName); 
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
			for (int i = 0; i < sheet.getLastRowNum(); i++) {    //getLastRowNum = Obtiene el numero de filas
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) { // getLastCellNum = Obtiene numero de columnas de la fila
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}  
			} 
			return data;   
		}	
	
}
