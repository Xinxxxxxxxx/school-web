package gx.action;

import gx.service.ExcelBook;
import gx.service.MysqlConn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class gxExcelOutsp extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private ServletConfig config; 
	public final void init(ServletConfig config)throws ServletException{
		this.config=config;
	}
	public gxExcelOutsp() {
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

String path="C:/库存信息.xls";
        
		MysqlConn db = new MysqlConn();
    	ResultSet rs = null;
        ExcelBook book = new ExcelBook();   
        rs = db.doQuery("select * from gxshopinfo", new Object[]{});
     
	    book.excelOut(path,rs, new Object[]{"ID","商品名称", "单位","条形码","进价","卖价","黄金会员折扣","白金会员折扣","钻石会员折扣","库存","添加时间","备注"});   
	    System.out.println("导出成功");
	    SmartUpload myload= new SmartUpload();
	    try{
	    	myload.initialize(config,request,response);
	    	myload.downloadFile(path);
	    }catch (Exception e) {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
