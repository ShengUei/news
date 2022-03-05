package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.ArticleBean;
import model.ArticleDAOImpl;

@WebServlet(
		urlPatterns = {"/QueryArticleByNumber"})
public class QueryArticleByNumberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleBean article;
		ArticleDAOImpl articleDAO = (ArticleDAOImpl) getServletContext().getAttribute("articleDAO");
		
		HttpSession session = request.getSession();
		
		try {
			article = articleDAO.queryByNumber((String) session.getAttribute("articleNo"));
			
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
