package ua.kiev.prog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;



public class MyWordPdfConverter {
	
	public static byte [] convertWordToPdf(byte [] data) throws XWPFConverterException, IOException, DocumentException{
		

		BaseFont bf = BaseFont.createFont("fonts/free/oldstandard/OldStandard-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf);

		POIFSFileSystem fs = null;  
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
		ByteArrayOutputStream file = null;

         try {    
             fs = new POIFSFileSystem(new ByteArrayInputStream(data));  

             HWPFDocument doc = new HWPFDocument(fs);  
             WordExtractor we = new WordExtractor(doc);  

             file = new ByteArrayOutputStream(); 

             PdfWriter writer = PdfWriter.getInstance(document, file);  

             Range range = doc.getRange();
             document.open();
             writer.setPageEmpty(true);  
             document.newPage();  
             writer.setPageEmpty(true);  

             String[] paragraphs = we.getParagraphText();  
             for (int i = 0; i < paragraphs.length; i++) {  

                 org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
                // CharacterRun run = pr.getCharacterRun(i);
                // run.setBold(true);
                // run.setCapitalized(true);
                // run.setItalic(true);
                 paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");  
             document.add(new Paragraph(paragraphs[i], font));  
             }    
         } catch (Exception e) {  
             e.printStackTrace();  
         } finally {  
                         // close the document  
            document.close();  
                     }  
		return file.toByteArray();
		
	}
	

}
