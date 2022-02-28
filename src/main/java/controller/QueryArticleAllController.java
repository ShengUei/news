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
		urlPatterns = {"/ArticleList"},
		initParams = {
				@WebInitParam(name = "ArticleList_Path", value = "articleList.html")
		}
		)
public class QueryArticleAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String ArticleList_Path;
	
	@Override
	public void init() throws ServletException{
		ArticleList_Path = getInitParameter("ArticleList_Path");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleBean> articleList;
		ArticleDAOImpl dao = new ArticleDAOImpl();
		
		try {
			articleList = dao.queryAll();
			
			Gson gson = new Gson();
			String str = gson.toJson(articleList);
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			out.print(str);
			
//			response.sendRedirect(ArticleList_Path);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

}
