package ua.kiev.prog.security;

import java.sql.*;
import java.text.SimpleDateFormat;

import ua.kiev.prog.LoginRecord;
import ua.kiev.prog.User;

public class JDBCAccess {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//  static final String DB_URL = "jdbc:mysql://127.10.247.130:3306/docservice";
//  static final String USER = "adminriLNIdl";
//  static final String PASS = "HJab8-r9MxMa";
   
   static final String DB_URL = "jdbc:mysql://localhost:3306/mazur";
   static final String USER = "root";
   static final String PASS = "root";
   
   public void updateAttempts(int attemptNum, String name) {
   Connection conn = null;
   PreparedStatement stmt = null;
   try{
	   
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      String sql = "UPDATE users set attempts="+attemptNum+" WHERE login='"+name+"'";
      stmt = conn.prepareStatement(sql); 
      stmt.executeUpdate();  
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
}
   
   public int getAttemptsByName(String name) throws ClassNotFoundException, SQLException{
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   String sql = "SELECT attempts FROM users WHERE login = '"+name+"'";
	   stmt = conn.prepareStatement(sql);
	   ResultSet rs = stmt.executeQuery(sql);
	   int attempts = 0;
	   while(rs.next()){
		   attempts  = rs.getInt("attempts");
	   }
	   	rs.close();
	      stmt.close();
	      conn.close(); 
	return attempts;  
   }
   
   public LoginRecord getLoginRecordByUserName(String name) throws ClassNotFoundException, SQLException{
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   String sql = "SELECT id FROM users WHERE login = '"+name+"'";
	   stmt = conn.prepareStatement(sql);
	   ResultSet rs = stmt.executeQuery(sql);
	   LoginRecord lr = new LoginRecord();
	   lr.setUserName(name);
	   SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z");
	   lr.setDate(sdf.format(new java.util.Date()));
	   while(rs.next()){
		   lr.setUserId(rs.getString("id"));
	   }
	   	rs.close();
	      stmt.close();
	      conn.close(); 
	return lr;  
   }
   
   
   
   
   
   public String getEmailByName(String name) throws ClassNotFoundException, SQLException{
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   String sql = "SELECT email FROM users WHERE login = '"+name+"'";
	   stmt = conn.prepareStatement(sql);
	   ResultSet rs = stmt.executeQuery(sql);
	   String eMail = null;
	   while(rs.next()){
		   eMail  = rs.getString("email");
	   }
	   	rs.close();
	      stmt.close();
	      conn.close(); 
	return eMail;
   }
   
   
   
   
   
   public void updateAuthorizedFalse(String name){
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      String sql = "UPDATE users set authorized = 0 WHERE login='"+name+"'";
	      stmt = conn.prepareStatement(sql);
	      stmt.executeUpdate();  
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	
   }
   
   public void updateAuthorizedTrue(String name){
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      String sql = "UPDATE users set authorized = 1 WHERE login='"+name+"'";
	      stmt = conn.prepareStatement(sql);
	      stmt.executeUpdate();  
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	
   }
   
   public boolean getAuthorizedByName(String name) throws ClassNotFoundException, SQLException{
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   String sql = "SELECT authorized FROM users WHERE login = '"+name+"'";
	   stmt = conn.prepareStatement(sql);
	   ResultSet rs = stmt.executeQuery(sql);
	   boolean authorized = false;
	   while(rs.next()){
		   authorized  = rs.getBoolean("authorized");
	   }
	   	rs.close();
	      stmt.close();
	      conn.close(); 
	return authorized;
   }

public void addNewLoginRecord(LoginRecord lr) {
	Connection conn = null;
	   PreparedStatement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      String sql = "INSERT INTO login_records (date, user_name, user_id) VALUES ('"+lr.getDate()+"', '"+lr.getUserName()+"', '"+lr.getUserId()+"')";
	      stmt = conn.prepareStatement(sql);
	      stmt.executeUpdate();  
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	
}

public String getLastRecordDateByName(String name) throws ClassNotFoundException, SQLException {
	Connection conn = null;
	   PreparedStatement stmt = null;
	   Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   String sql = "SELECT date FROM login_records where user_name = '"+name+"' ORDER BY id DESC LIMIT 1";
	   stmt = conn.prepareStatement(sql);
	   ResultSet rs = stmt.executeQuery(sql);
	   String date = null;
	   while(rs.next()){
		   date  = rs.getString("date");
	   }
	   	rs.close();
	      stmt.close();
	      conn.close(); 
	return date;
}
   
   
   
   
   
}
