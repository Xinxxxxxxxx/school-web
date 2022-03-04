package gx.action;

import gx.dao.gxHyDAO;
import gx.entity.gxHy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gxQueryHy extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public gxQueryHy() {
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
	
		
		String tj = "";
		if(request.getParameter("tj")!=null)
			tj = new String(request.getParameter("tj").getBytes("ISO-8859-1"),"UTF-8");
		gxHyDAO hdo = new gxHyDAO();
		
		int pageNow = 0;
		int pageSize = 4;
		int pageCount = 0;
		int rowCount = 0;
		int start = 0;
		if(request.getParameter("page")!=null)
			pageNow = Integer.parseInt(request.getParameter("page"));
		start = pageNow*pageSize;
		rowCount = hdo.getRowCount("select count(*)from gxvip where gxid like '%"+tj+"%' or gxvname like '%"+tj+"%' or gxvphone like  '%"+tj+"%'",
				  new Object[]{});
		if(rowCount%pageSize==0)
			pageCount = rowCount/pageSize;
		else
			pageCount = rowCount/pageSize+1;
          List<gxHy> somehy = hdo.QueryHy("select *from gxvip where gxid like '%"+tj+"%' or gxvname like '%"+tj+"%' or gxvphone like  '%"+tj+"%' limit ?,?",
        		  new Object[]{start,pageSize});
          request.getSession().setAttribute("somehy",somehy);
          request.getSession().setAttribute("tj",tj);
          request.getSession().setAttribute("rowCount",rowCount);
          request.getSession().setAttribute("pageCount",pageCount);
          request.getSession().setAttribute("pageNow",pageNow);
          response.sendRedirect("gxhyzl.jsp");
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
