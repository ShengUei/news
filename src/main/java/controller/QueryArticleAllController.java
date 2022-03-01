package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ArticleBean;
import model.ArticleDAOImpl;

@WebServlet(
		urlPatterns = {"/ArticleList"})
public class QueryArticleAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

}
