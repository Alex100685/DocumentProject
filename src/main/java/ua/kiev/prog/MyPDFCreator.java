package ua.kiev.prog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class MyPDFCreator {
	
public static byte [] createPdfWarning() throws XWPFConverterException, IOException, DocumentException{
		
	BaseFont bf = BaseFont.createFont("c:/windows/fonts/arialbd.ttf", "Cp1251", BaseFont.EMBEDDED);
	Font font = new Font(bf);
		
	
		  
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
		ByteArrayOutputStream file = null;

         try {    

             file = new ByteArrayOutputStream(); 

             PdfWriter writer = PdfWriter.getInstance(document, file);  

             document.open();
             writer.setPageEmpty(true);  
             document.newPage();  
             writer.setPageEmpty(true);  

             String paragraph = "Не подходящий формат файла. Подходящие: '.doc', 'xls', 'pdf'";  
             
                  
             document.add(new Paragraph(paragraph, font));  
                
         } catch (Exception e) {  
             e.printStackTrace();  
         } finally {  
                         // close the document  
            document.close();  
                     }  
		return file.toByteArray();
		
	}

}
