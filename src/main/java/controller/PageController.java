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
		urlPatterns = {"", "/Article", "/Category", "/SignIn", "/SignOut", "/SignUp", "/ArticleManagement", "/CreateNewArticle"},
		initParams = {
				@WebInitParam(name = "index_Path", value = "index.html"),
				@WebInitParam(name = "Article_Path", value = "article.html"),
				@WebInitParam(name = "Category_Path", value = "category.html"),
				@WebInitParam(name = "SignIn_Path", value = "signIn.html"),
				@WebInitParam(name = "SignOut_Path", value = "/SignOutMember"),
				@WebInitParam(name = "SignUp_Path", value = "signUp.html"),
				@WebInitParam(name = "ArticleManagement_Path", value = "articleManagement.html"),
				@WebInitParam(name = "CreateNewArticle_Path", value = "createNewArticle.html")
		}
		)
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String index_Path;
	private String Article_Path;
	private String Category_Path;
	private String SignIn_Path;
	private String SignOut_Path;
	private String SignUp_Path;
	private String ArticleManagement_Path;
	private String CreateNewArticle_Path;
	
	@Override
	public void init() throws ServletException{
		index_Path = getInitParameter("index_Path");
		Article_Path = getInitParameter("Article_Path");
		Category_Path = getInitParameter("Category_Path");
		SignIn_Path = getInitParameter("SignIn_Path");
		SignOut_Path = getInitParameter("SignOut_Path");
		SignUp_Path = getInitParameter("SignUp_Path");
		ArticleManagement_Path = getInitParameter("ArticleManagement_Path");
		CreateNewArticle_Path = getInitParameter("CreateNewArticle_Path");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getRequestURI());
		
		HttpSession session = request.getSession();
		
		if (request.getRequestURI().equals("/news/")) {
			response.sendRedirect(index_Path);
		} else if (request.getRequestURI().equals("/news/Article")) {
			if (request.getParameter("articleNo") != null) {
				session.setAttribute("articleNo", request.getParameter("articleNo"));
				response.sendRedirect(Article_Path);
			} else {
				response.sendRedirect(index_Path);
			}
		} else if (request.getRequestURI().equals("/news/Category")) {
			if (request.getParameter("category") != null) {
				session.setAttribute("category", request.getParameter("category"));
				response.sendRedirect(Category_Path);
			} else {
				response.sendRedirect(index_Path);
			}
		} else if (request.getRequestURI().equals("/news/SignIn")) {
			response.sendRedirect(SignIn_Path);
		} else if (request.getRequestURI().equals("/news/SignOut")) {
			request.getRequestDispatcher(SignOut_Path).forward(request, response);
		} else if (request.getRequestURI().equals("/news/SignUp")) {
			response.sendRedirect(SignUp_Path);
		} else if (request.getRequestURI().equals("/news/ArticleManagement")) {
			if ((boolean)session.getAttribute("isMember") == true) {
				response.sendRedirect(ArticleManagement_Path);
			} else {
				response.sendRedirect(SignIn_Path);
			}
		} else if (request.getRequestURI().equals("/news/CreateNewArticle")) {
			if ((boolean)session.getAttribute("isMember") == true) {
				response.sendRedirect(CreateNewArticle_Path);
			} else {
				response.sendRedirect(SignIn_Path);
			}
		}
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
