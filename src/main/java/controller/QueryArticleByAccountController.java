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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.ArticleBean;
import model.ArticleDAOImpl;
import model.MemberBean;

@WebServlet(
		urlPatterns = {"/QueryArticleByAccount"})
public class QueryArticleByAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ArticleBean> article;
		ArticleDAOImpl articleDAO = (ArticleDAOImpl) getServletContext().getAttribute("articleDAO");
		
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("member");
		
		try {
			article = articleDAO.queryByAccount(member.getAccount());
			
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
