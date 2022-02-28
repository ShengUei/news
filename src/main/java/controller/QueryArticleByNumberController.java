package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ArticleBean;
import model.ArticleDAOImpl;

@WebServlet(
		urlPatterns = {"/QueryArticleByNumber", "/QueryArticleByNumber?*"},
		initParams = {
				@WebInitParam(name = "QueryArticleByNumber_Path", value = "article.html")
		}
		)
public class QueryArticleByNumberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private String QueryArticleByNumber_Path;
//	
//	@Override
//	public void init() throws ServletException{
//		QueryArticleByNumber_Path = getInitParameter("QueryArticleByNumber_Path");
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleBean article;
		ArticleDAOImpl dao = new ArticleDAOImpl();
		
		try {
			article = dao.queryByNumber(request.getParameter("articleNo"));
			
			Gson gson = new Gson();
			String str = gson.toJson(article);
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			out.print(str);
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

}
