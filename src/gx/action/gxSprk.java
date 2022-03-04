package gx.action;

import gx.service.MysqlConn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class gxSprk extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public gxSprk() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MysqlConn db = new MysqlConn();
		ResultSet rs = null;
		try{
		rs=db.doQuery("select *from gxshopls where gxrole=0", new Object[]{});
		while(rs.next()){
			db.doExecute("update gxshopinfo set gxkc=gxkc+? where gxtxm=?",new Object[]{rs.getInt("gxkc"),rs.getString("gxtxm")});			
		}
		db.doExecute("delete from gxshopls where gxrole=0", new Object[]{});
		response.sendRedirect("gxQueryspls");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String txm=request.getParameter("txm");
		MysqlConn db = new MysqlConn();
		ResultSet rs = null;
		ResultSet rs1 = null;
		try{
			rs=db.doQuery("select * from gxshopinfo where gxtxm=?", new Object[]{txm});
		    if(rs.next()){
			   rs1=db.doQuery("select *from gxshopls where gxtxm=? and gxrole=0", new Object[]{txm});
		       if(rs1.next()){
		    	   db.doExecute("update gxshopls set gxkc=gxkc+1 where gxtxm=? and gxrole=0",  new Object[]{txm});
		       }
		       else{
		    	   db.doExecute("insert into gxshopls values(?,?,?,?,?,?,?,?,?,1,?,0)",
		    			   new Object[]{rs.getInt("gxsid"),rs.getString("gxsname"),rs.getString("gxdw"),rs.getInt("gxtxm"),
		    			   rs.getDouble("gxjj"),rs.getDouble("gxmj"),rs.getDouble("gxhjcount"),rs.getDouble("gxbjcount"),
		    			   rs.getDouble("gxzscount"),rs.getString("gxmore")});
		    }
		response.sendRedirect("gxQueryspls");
		}else{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>alert('没有该商品!'); history.go(-1);</script>");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
