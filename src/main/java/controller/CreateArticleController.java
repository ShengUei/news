package controller;

import java.io.IOException;
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

import model.ArticleBean;
import model.ArticleContent;
import model.ArticleDAOImpl;
import model.ArticlePicture;

@WebServlet(
		urlPatterns = {"/CreateArticle"},
		initParams = {
				@WebInitParam(name = "CreateArticle_Path", value = "newArticle.html")
		}
		)
public class CreateArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String CreateArticle_Path;
	
	@Override
	public void init() throws ServletException{
		CreateArticle_Path = getInitParameter("CreateArticle_Path");
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleBean article = new ArticleBean();
		
		List<ArticleContent> articleContentList = new ArrayList<ArticleContent>();
		
		ArticlePicture articlePicture = new ArticlePicture();
		List<ArticlePicture> articlePictureList = new ArrayList<ArticlePicture>();
		
		//article
		String articleNo = (Long.toString(Math.round(Math.random() * 1000) + 1000));
		article.setArticleNo(articleNo);
		article.setAuthor("accountTest");
		article.setPostTime(new Date().getTime());
		article.setTitle(request.getParameter("title"));
		article.setCategory(request.getParameter("category"));
		
		//articleContent
		String content = request.getParameter("content");
		String[] paragraphList = content.split("[^\\p{P}]\r\n");
		String contentNo = Long.toString(Math.round(Math.random() * 1000) + 1000);
		ArticleContent articleContent;
		
		for (int i = 0; i < paragraphList.length; i++) {
			articleContent = new ArticleContent();
			
			articleContent.setContentNo(contentNo);
			articleContent.setIndex_para(i + 1);
			articleContent.setParagraph(paragraphList[i]);
			
			articleContentList.add(articleContent);
		}
		
		article.setContentList(articleContentList);
		
		//articlePicture
		articlePicture.setPictureNo(Long.toString(Math.round(Math.random() * 1000 + 1000)));
		articlePicture.setIndex_pic(1);
		articlePicture.setPicturePath("picturePath");
		
		articlePictureList.add(articlePicture);
		
		article.setPictureList(articlePictureList);
		
		//InsertData
		ArticleDAOImpl dao = new ArticleDAOImpl();
		try {
			dao.insertData(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(CreateArticle_Path);
	}

}
