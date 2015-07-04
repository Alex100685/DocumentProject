package ua.kiev.prog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;

public class MyPdfEncyptor {
	
	private static final String USER_PASSWORD = "user";
    private static final String OWNER_PASSWORD = "admin";
	
	public static byte [] encryptNoCopy(byte [] file) throws DocumentException, IOException{
		ByteArrayInputStream is = new ByteArrayInputStream(file);
		PdfReader reader = new PdfReader(is);
		ByteArrayOutputStream out  = new ByteArrayOutputStream();
		PdfEncryptor.encrypt(reader, out, USER_PASSWORD.getBytes(), OWNER_PASSWORD.getBytes(), 11, false);

	   
	    return out.toByteArray();

}
	
}
