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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class MyPDFCreator {
	
public static byte [] createPdfWarning() throws XWPFConverterException, IOException, DocumentException{
		
		BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
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

             String paragraph = "Недопустимое расширение файла. Доступны к просмотру только файлы с расширением '.doc', 'xls', 'pdf'";  
             
                  
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
