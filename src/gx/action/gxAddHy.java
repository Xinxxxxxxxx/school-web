package gx.action;

import gx.dao.gxHyDAO;

import gx.entity.gxHy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gxAddHy extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public gxAddHy() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		 request.setCharacterEncoding("UTF-8");
		 String gxvphone = request.getParameter("dh");
		 String gxvname = request.getParameter("nc");
		 String gxvaddr = request.getParameter("dz");
		 String gxjb = request.getParameter("jb");
		 gxHy hy = new gxHy();
		 gxHyDAO hdao = new gxHyDAO();
		 hy.setGxphone(gxvphone);
		 hy.setGxvname(gxvname);
		 hy.setGxaddr(gxvaddr);
		 hy.setGxjb(gxjb);
		 int res = hdao.AddHy(hy);
		 if(res>0){
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().println("<script>alert('添加成功！');location.href='gxaddhy.jsp';</script>");
		 }else{
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().println("<script>alert('添加失败！');history.go(-1);</script>");
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
