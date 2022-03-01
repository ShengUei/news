package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
		urlPatterns = {"", "/Article"},
		initParams = {
				@WebInitParam(name = "ArticleList_Path", value = "articleList.html"),
				@WebInitParam(name = "Article_Path", value = "article.html")
		}
		)
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String ArticleList_Path;
	private String Article_Path;
	
	@Override
	public void init() throws ServletException{
		ArticleList_Path = getInitParameter("ArticleList_Path");
		Article_Path = getInitParameter("Article_Path");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getRequestURI());
		
		HttpSession session = request.getSession();
		
		if (request.getRequestURI().equals("/news/")) {
			response.sendRedirect(ArticleList_Path);
		} else if (request.getRequestURI().equals("/news/Article")) {
			session.setAttribute("articleNo", request.getParameter("articleNo"));
			response.sendRedirect(Article_Path);
		}
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
