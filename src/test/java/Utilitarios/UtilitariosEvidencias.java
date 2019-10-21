package Utilitarios;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
 
import javax.imageio.ImageIO;
 
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import ConfiguracionBase.ConfigBase;


public class UtilitariosEvidencias extends ConfigBase{
      
	 public static void LimpiarArchivoPNG ( String dirPath) throws Exception
     {
          
  	   //Busca x tipo de archivo
         // Aquí la carpeta que queremos explorar
     	   
         String path = dirPath;
         String files;
         File folder = new File(path);
         File[] listOfFiles = folder.listFiles();
  
         for (int i = 0; i < listOfFiles.length; i++)
         {
             if (listOfFiles[i].isFile())
             {
                 files = listOfFiles[i].getName();
                 if (files.endsWith(".png") || files.endsWith(".PNG"))
                 {
                 	  listOfFiles[i].delete();
                 }
             }
         }
    
             
        }  
	public static void MixcaptureScreenShotListFilespng ( XWPFRun run, FileOutputStream out, String dirPath) throws Exception
       {
            
    	   //Busca x tipo de archivo
           // Aquí la carpeta que queremos explorar
       	   
           String path = dirPath;
           String files;
           File folder = new File(path);
           File[] listOfFiles = folder.listFiles();
    
           for (int i = 0; i < listOfFiles.length; i++)
           {
               if (listOfFiles[i].isFile())
               {
                   files = listOfFiles[i].getName();
                   if (files.endsWith(".png") || files.endsWith(".PNG"))
                   {
                   	                	   
                       InputStream pic = new FileInputStream(dirPath + files);
                        run.addBreak();
                        run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, files, Units.toEMU(370), Units.toEMU(250));
                        run.addTab();
                        run.setText("                       ");
                        run.setText(files);
                        run.setText(" ");
                        run.addBreak();                        
                        pic.close();
                       //System.out.println(files);
                   }
               }
           }
           //System.out.println("Fin");
               
          }
         
               
            public static void TakeScreenShoot(String descripcionCasoPrueba) {
                  try {

     
               	   //String dirPath = System.getProperty("user.dir")+"\\src\\resources\\pantallazos\\";
                	  String dirPath = prop.getProperty("pantallazos");
               	   
               	String flag="0";
                   //Create folder/directory if not exist.
                   File file = new File(dirPath);
                      if (!file.exists()) {
                          if (file.mkdir()) {
                              System.out.println("Directory is created!");
                          } else {
                              System.out.println("Failed to create directory!");
                          }
                      }
                      //HORA EN NOMBRE DE WORD
                      Date date = new Date();
                      DateFormat hourdateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
      		        	String datetime = hourdateFormat.format(date);
      		          
      		          //String pathPlantillaWord = System.getProperty("user.dir")+"\\src\\resources\\PlantillaInforme\\IPS_REQ-2019 Reportes_v1.docx";
      		        	String pathPlantillaWord = prop.getProperty("plantilla");
                      //XWPFDocument docx = new XWPFDocument();
      		          XWPFDocument docx = new XWPFDocument(new FileInputStream(new File(pathPlantillaWord)));
                      
                      FileOutputStream out = new FileOutputStream(dirPath+"Evidencia_"+datetime+".docx");
                      //CrearCabeceraDocumento(out,docx);
                      XWPFParagraph  para =docx.createParagraph();
    		          //String rt = System.getProperty("user.dir")+"\\src\\resources\\pantallazos\\Evidencia_"+datetime+".docx";
    		          String rt = prop.getProperty("pantallazos")+"Evidencia_"+datetime+".docx";
    		          String sFichero = rt;    		          
    		          XWPFRun run = para.createRun();
    		          run.setText(descripcionCasoPrueba);
    	              run.setFontFamily("Arial");
    	              run.setFontSize(14);
    	              run.setTextPosition(10);
    	              //run.setCharacterSpacing(1);    		          
                      para.setAlignment(ParagraphAlignment.CENTER);      		        
      		   
                      File fichero = new File(sFichero);
                      if (fichero.exists())
                      {
                      flag="1";
                      //System.out.println("Archivo Generado!");
                      }                 
                      MixcaptureScreenShotListFilespng( run,out , dirPath);
                      //System.out.println("Write to doc file sucessfully...");
                      LimpiarArchivoPNG(dirPath);                      
                      docx.write(out);
                      out.flush();
                      out.close();
                      docx.close();
    
                      
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }

        
}