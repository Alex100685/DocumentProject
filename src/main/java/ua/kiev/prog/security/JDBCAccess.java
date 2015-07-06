package ua.kiev.prog.security;

import java.sql.*;

public class JDBCAccess {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  // static final String DB_URL = "jdbc:mysql://127.10.247.130:3306/docservice";
  // static final String USER = "adminriLNIdl";
  // static final String PASS = "HJab8-r9MxMa";
   
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
   
   
   
   
   
   
   
}
