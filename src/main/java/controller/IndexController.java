package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"", "/Index"},
		initParams = {
				@WebInitParam(name = "Index_Path", value = "/JSP/Index.jsp")
		}
		)
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String Index_Path;
	
	@Override
	public void init() throws ServletException{
		Index_Path = getInitParameter("Index_Path");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Index_Path).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
