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
import model.ArticleContent;
import model.ArticleDAOImpl;
import model.ArticlePicture;

@WebServlet(
		urlPatterns = {"/NewArticle"},
		initParams = {
				@WebInitParam(name = "HouseInfo_Path", value = "HouseInfo.html")
		}
		)
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String HouseInfo_Path;
	
	@Override
	public void init() throws ServletException{
		HouseInfo_Path = getInitParameter("HouseInfo_Path");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HouseInfoDAOImpl dao = new HouseInfoDAOImpl();
//		
//		try {
//			List<ArticleBean> houseInfoList;
//			String search = request.getParameter("search");
//			
//			if (request.getParameter("search") != "" && request.getParameter("search") != null) {
//				houseInfoList = dao.queryHouseInfoByH_type(search);
//			} else {
//				houseInfoList = dao.queryHouseInfo();
//			}
			
//			request.setAttribute("houseInfoList", houseInfoList);
//			
//			Gson gson = new Gson();
//			String str = gson.toJson(houseInfoList);
//			
//			PrintWriter out = response.getWriter();
//	        response.setContentType("application/json");
//	        response.setCharacterEncoding("UTF-8");
//	        out.print(str);
//	        out.flush();
//	        
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		request.getRequestDispatcher(HouseInfo_Path).forward(request, response);
//		response.sendRedirect("/news/newArticle.html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleBean article = new ArticleBean();
		
		List<ArticleContent> articleContentList = new ArrayList<ArticleContent>();
		
		ArticlePicture articlePicture = new ArticlePicture();
		List<ArticlePicture> articlePictureList = new ArrayList<ArticlePicture>();
		
		//article
		String articleNo = Long.toString(Math.round(Math.random() * 1000));
		article.setArticleNo(articleNo);
		article.setAuthor("author");
		article.setPostTime(new Date());
		article.setTitle(request.getParameter("title"));
		article.setCategory(request.getParameter("category"));
		
		//articleContent
		String content = request.getParameter("content");
		String[] paragraphList = content.split("\n");
		String contentNo = Long.toString(Math.round(Math.random() * 1000));
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
		articlePicture.setPictureNo(Long.toString(Math.round(Math.random() * 1000)));
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
		
		response.sendRedirect("/news/newArticle.html");
	}

}
