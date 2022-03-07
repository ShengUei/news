package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ArticleBean;
import model.ArticleContent;
import model.ArticleDAOImpl;
import model.ArticlePicture;
import model.MemberBean;

@MultipartConfig(location = "C:\\eclipse-workspace\\SideProject1-workspace\\news\\src\\main\\webapp\\images")
@WebServlet(
		urlPatterns = {"/CreateArticle"},
		initParams = {
				@WebInitParam(name = "CreateArticleSuccess_Path", value = "index.html"),
				@WebInitParam(name = "CreateArticleFailure_Path", value = "createArticle.html")
		}
		)
public class CreateArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String CreateArticleSuccess_Path;
	private String CreateArticleFailure_Path;
	private ArticleDAOImpl articleDAO;
	
	@Override
	public void init() throws ServletException{
		CreateArticleSuccess_Path = getInitParameter("CreateArticleSuccess_Path");
		CreateArticleFailure_Path = getInitParameter("CreateArticleFailure_Path");
		articleDAO = (ArticleDAOImpl) getServletContext().getAttribute("articleDAO");
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ArticleBean article = new ArticleBean();
		
		List<ArticleContent> articleContentList = new ArrayList<ArticleContent>();
		
		//article
		String articleNo = (Long.toString(Math.round(Math.random() * 1000) + 1000));
		article.setArticleNo(articleNo);
		
		MemberBean member = (MemberBean) session.getAttribute("member");
		
		article.setAuthor(member.getAccount());
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
		Collection<Part> pictures = request.getParts();
		Object[] array = pictures.stream().filter(part -> part.getName().startsWith("pictures")).toArray();
		String submittedFileName;
		String ext;
		String pictureNo = Long.toString(Math.round(Math.random() * 1000) + 1000);
		ArticlePicture articlePicture;
		List<ArticlePicture> articlePictureList = new ArrayList<ArticlePicture>();
		
		for (int i = 0; i < array.length; i++) {
			articlePicture = new ArticlePicture();
			
			submittedFileName = ((Part) array[i]).getSubmittedFileName();
			ext = submittedFileName.substring(submittedFileName.lastIndexOf("."));
			
			((Part) array[i]).write(String.format("%s%s", Instant.now().toEpochMilli(), ext));
			
			articlePicture.setPictureNo(pictureNo);
			articlePicture.setIndex_pic(i + 1);
			articlePicture.setPicturePath(String.format("%s%s", Instant.now().toEpochMilli(), ext));
			
			articlePictureList.add(articlePicture);
		}
		
		article.setPictureList(articlePictureList);
		
		//InsertData
		try {
			articleDAO.insertData(article);
			response.sendRedirect(CreateArticleSuccess_Path);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(CreateArticleFailure_Path);
		}
		
	}

}
