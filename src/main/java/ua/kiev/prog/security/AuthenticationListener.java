package ua.kiev.prog.security;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import ua.kiev.prog.Actions;
import ua.kiev.prog.User;

@Service
public class AuthenticationListener implements ApplicationListener <AbstractAuthenticationEvent>{
	
	JDBCAccess access;
	
        @Override
        public void onApplicationEvent(AbstractAuthenticationEvent appEvent)
        {
            if (appEvent instanceof AuthenticationSuccessEvent)
            {
                AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
                UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
                String userName = userDetails.getUsername();
                access = new JDBCAccess();
                access.updateAttempts(0, userName);
            }

            if (appEvent instanceof AuthenticationFailureBadCredentialsEvent)
            {
                        AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;
                        String userName = (String) event.getAuthentication().getPrincipal();
                        access = new JDBCAccess();
                       int attempts = 0;
					try {
						attempts = access.getAttemptsByName(userName);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                        
                        if(!userName.equals("superadmin")){
                        	attempts++; 
                        	access.updateAttempts(attempts, userName);
                        }
                        if(!userName.equals("superadmin") && attempts>6){
                        	access.updateAuthorizedFalse(userName);
                        	String eMail = null;
                        	try {
								eMail = access.getEmailByName(userName);
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	Date date = new Date();
                        	DateFormat df = new SimpleDateFormat("MM.dd.yyyy'�' HH:mm:ss z");
                        	String d = df.format(date);
                        	
String text = "Возможно была попытка несанкционированного доступа "+d+". Ваш профиль в сервисе документов был заблокирован по причине неправильного ввода пароля более 3-х раз, для восстановления доступа обратитесь к администратору. В случе если Вы подозреваете что было место попытки несанкционированного доступа, рекомендовано зарегистрироваться под другим логином после чего уведомить администратора";
String headLine = "Возможная попытка несанкционированного доступа";
Charset.forName("UTF-8").encode(text);
Charset.forName("UTF-8").encode(headLine);
                        	
                        	Email.sendEmailMessage("documsservice@gmail.com", eMail, headLine, text);
                        	
                        	
                        }
            }
        }
        
}     