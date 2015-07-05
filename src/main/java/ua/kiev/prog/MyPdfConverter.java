package ua.kiev.prog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class MyPdfConverter {
	
	public static byte [] convertExcelToPdf(byte [] file) throws IOException, DocumentException {
		
		
		//final String TMP_DIR = System.getProperty("java.io.tmpdir");
		//BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //���������� ���� ������, ������� ������������ ���������
		//Font font = new Font(bf);
		
		Font font = FontFactory.getFont(FontFactory.TIMES,"Cp1251", com.lowagie.text.Font.BOLD);
		
		
		ByteArrayInputStream is = new ByteArrayInputStream(file);		
		// FileInputStream is = new FileInputStream(TMP_DIR+"/"+fileName);
         // Read workbook into HSSFWorkbook
         HSSFWorkbook my_xls_workbook = new HSSFWorkbook(is); 
         // Read worksheet into HSSFSheet
         HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
         // To iterate over the rows
         
         PdfPTable my_table;
         if(my_worksheet.getPhysicalNumberOfRows() == 0){
        	 my_table = new PdfPTable(1); 
        	 my_table.addCell(new Phrase("There is no data on the first sheet of Excel file", font));
        	
         }else{
        	 int colQuant = my_worksheet.getRow(0).getLastCellNum();
        	 my_table = new PdfPTable(colQuant);
         }
         
         Iterator<Row> rowIterator = my_worksheet.iterator();
         
         //We will create output PDF document objects at this point
         com.itextpdf.text.Document iText_xls_2_pdf = new com.itextpdf.text.Document();
         
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         
        // FileOutputStream bos = new FileOutputStream(TMP_DIR+"/content.pdf");
         
         PdfWriter.getInstance(iText_xls_2_pdf , bos);
         iText_xls_2_pdf.open();
         PdfPCell table_cell;
         while(rowIterator.hasNext()) {
                 Row row = rowIterator.next(); 
                 Iterator<Cell> cellIterator = row.cellIterator();
                         while(cellIterator.hasNext()) {
                                 Cell cell = cellIterator.next(); //Fetch CELL
                                 switch(cell.getCellType()) { //Identify CELL type
                                         //you need to add more code here based on
                                         //your requirement / transformations
                                 case Cell.CELL_TYPE_NUMERIC:
                                	 
                                	 if(HSSFDateUtil.isCellDateFormatted(cell)){
                                 		Date date =  cell.getDateCellValue();
                                 		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                 		table_cell=new PdfPCell(new Phrase(sdf.format(date), font));
                                 	 }
                                 	 else{
                                 		table_cell=new PdfPCell(new Phrase(String.valueOf(cell.getNumericCellValue()), font));
                                   	
                                 	 }
                                	 my_table.addCell(table_cell);
                                	 break;
                                	 
                                 case Cell.CELL_TYPE_BLANK:
                                 	table_cell=new PdfPCell(new Phrase("", font));
                                 	my_table.addCell(table_cell);
                                 	break;
                                	 
                                 case Cell.CELL_TYPE_STRING:
                                         //Push the data from Excel to PDF Cell
                                          table_cell=new PdfPCell(new Phrase(cell.getStringCellValue(), font));
                                          //feel free to move the code below to suit to your needs
                                          my_table.addCell(table_cell);
                                         break;
                                 case Cell.CELL_TYPE_FORMULA:
                                	 if(Cell.CELL_TYPE_NUMERIC == cell.getCachedFormulaResultType()){
                                		 table_cell=new PdfPCell(new Phrase(String.valueOf(cell.getNumericCellValue()), font));
                                		 my_table.addCell(table_cell);
                                		 break;
                                		 
                                	 }
                                	 else{
                                		 table_cell=new PdfPCell(new Phrase(cell.getStringCellValue(), font));
                                		 my_table.addCell(table_cell);
                                		 break;
                                	 }       
                                 }
                                 //next line
                         }

         }
         //Finally add the table to PDF document
         iText_xls_2_pdf.add(my_table);                       
         iText_xls_2_pdf.close();                
         //we created our pdf file..
         is.close(); //close xls
         bos.close();
         
         return bos.toByteArray();
	}
	
	

		
}

	
