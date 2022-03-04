package gx.service;

import java.sql.*;

public class MysqlConn {
	private String Driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/gxshop?useUnicode=true&characterEncoding=gbk&mysqlEncoding=utf8";
	private String user = "root";
	private String password = "root";
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    public void getConnect(){
    	try{
    		Class.forName(Driver);
		    conn = DriverManager.getConnection(url, user, password);
    	}catch(Exception e){
    		System.out.println("connect fail!");
    		e.printStackTrace();
    	}
    }
    public ResultSet doQuery(String sql, Object[] param){
    	this.getConnect();
    	try{
    		pstmt = conn.prepareStatement(sql);
    		for(int i=0; i<param.length; i++)
    			pstmt.setObject(i+1, param[i]);
    		rs = pstmt.executeQuery();
    	}catch(Exception e){
    		System.out.println("doquery fail!");
    		e.printStackTrace();
    	}
    	return this.rs;
    }
    public int doExecute(String sql, Object[] param){
    	int res = 0;
    	this.getConnect();
    	try{
    		pstmt = conn.prepareStatement(sql);
    		for(int i=0; i<param.length; i++)
    			pstmt.setObject(i+1, param[i]);
    		res = pstmt.executeUpdate();
    	}catch(Exception e){
    		System.out.println("doquery fail!");
    		e.printStackTrace();
    	}
    	return res;
    }
    public void close(){
    	try{
    		if(rs!=null)
    			rs.close();
    		if(pstmt!=null)
    			pstmt.close();
    		if(conn!=null)
    			conn.close();
    	}catch(Exception e){
    		System.out.println("close fail!");
    		e.printStackTrace();
    	}
    }
}
