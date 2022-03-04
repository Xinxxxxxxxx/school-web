package gx.action;

import gx.dao.gxshopDAO;
import gx.entity.gxshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gxUpdatesp extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public gxUpdatesp() {
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
		int id=Integer.parseInt(request.getParameter("sid"));
		gxshopDAO hdao=new gxshopDAO();
		gxshop sp=hdao.QueryById(id);
		request.getSession().setAttribute("sp", sp);
		response.sendRedirect("gxupdatesp.jsp");
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
		String gxnc = request.getParameter("nc");
		String gxdw = request.getParameter("dw");
		double gxjj = Double.parseDouble(request.getParameter("jj"));
		double gxmj = Double.parseDouble(request.getParameter("mj"));
		double gxhj = Double.parseDouble(request.getParameter("hj"));
		double gxbj = Double.parseDouble(request.getParameter("bj"));
		double gxzs = Double.parseDouble(request.getParameter("zs"));
		String gxbz = request.getParameter("bz");
		int gxtxm = Integer.parseInt(request.getParameter("txm"));
		
		gxshop sp=new gxshop();
		gxshopDAO hdao=new gxshopDAO();
		sp=(gxshop)request.getSession().getAttribute("sp");
		
		sp.setGxsname(gxnc);
		sp.setGxdw(gxdw);
		sp.setGxjj(gxjj);
		sp.setGxmj(gxmj);
		sp.setGxhjcount(gxhj);
		sp.setGxbjcount(gxbj);
		sp.setGxzscount(gxzs);
		sp.setGxmore(gxbz);
		sp.setGxtxm(gxtxm);
		
		int res =hdao.UpdatespById(sp);
		if(res>0){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>alert('修改成功！');location.href='gxQueryshopinfo';</script>");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>alert('修改失败！');history.go(-1);</script>");
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
