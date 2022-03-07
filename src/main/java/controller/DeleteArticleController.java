package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ArticleBean;
import model.ArticleDAOImpl;
import model.MemberBean;

@WebServlet(
		urlPatterns = {"/DeleteArticle"},
		initParams = {
				@WebInitParam(name = "index_Path", value = "index.html"),
				@WebInitParam(name = "ArticleManagement_Path", value = "/ArticleManagement")
		}
		)
public class DeleteArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String index_Path;
	private String ArticleManagement_Path;
	private ArticleDAOImpl articleDAO;
	
	@Override
	public void init() throws ServletException{
		index_Path = getInitParameter("index_Path");
		ArticleManagement_Path = getInitParameter("ArticleManagement_Path");
		articleDAO = (ArticleDAOImpl) getServletContext().getAttribute("articleDAO");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String articleNo = (String) session.getAttribute("articleNo");
		
		MemberBean member = (MemberBean) session.getAttribute("member");
		String account = member.getAccount();
		
		try {
			ArticleBean article = articleDAO.queryAccountByNumber(articleNo);
			
			if (account.equals(article.getAuthor())) {
				articleDAO.deleteData(articleNo);
				response.sendRedirect(ArticleManagement_Path);
			} else {
				response.sendRedirect(index_Path);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			
//	}

}
