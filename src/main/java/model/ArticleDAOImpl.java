package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnectionFactory;

public class ArticleDAOImpl implements GenericDAO<ArticleBean>{
	
	private Connection conn;
	
	public ArticleDAOImpl() {
		this.conn = ConnectionFactory.getConnection();
	}

	@Override
	public List<ArticleBean> queryAll() throws SQLException {
		List<ArticleBean> articleList = new ArrayList<ArticleBean>();
		ArticleBean article;
		
		List<ArticleContent> contentList = new ArrayList<ArticleContent>();
		ArticleContent content;
		
		List<ArticlePicture> pictureList = new ArrayList<ArticlePicture>();
		ArticlePicture picture;
		
		String sqlStr = "SELECT * FROM article";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		ResultSet rs = preState.executeQuery();
		
		while (rs.next()) {
			article = new ArticleBean();
			
			article.setArticleNo(rs.getString("articleNo"));
			article.setAuthor(rs.getString("author"));
			
			try {
				article.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("postTime")).getTime());
			} catch (ParseException | SQLException e) {
				e.printStackTrace();
			}
			
			article.setTitle(rs.getString("title"));
			article.setCategory(rs.getString("category"));
			
			//articleContent
			sqlStr = "SELECT * FROM articleContent WHERE contentNo = ?";
			
			preState = conn.prepareStatement(sqlStr);
			
			preState.setString(1, rs.getString("contentNo"));
			
			ResultSet rsContent = preState.executeQuery();
			
			while (rsContent.next()) {
				content = new ArticleContent();
				
				content.setContentNo(rsContent.getString("contentNo"));
				content.setIndex_para(rsContent.getInt("index_para"));
				content.setParagraph(rsContent.getString("paragraph"));
				
				contentList.add(content);
			}
			
			article.setContentList(contentList);
			
			rsContent.close();
			
			//articlePicture
			sqlStr = "SELECT * FROM articlePicture WHERE pictureNo = ?;";
			
			preState = conn.prepareStatement(sqlStr);
			
			preState.setString(1, rs.getString("pictureNo"));
			
			ResultSet rsPicture = preState.executeQuery();
			
			while (rsPicture.next()) {
				picture = new ArticlePicture();
				
				picture.setPictureNo(rsPicture.getString("pictureNo"));
				picture.setIndex_pic(rsPicture.getInt("index_pic"));
				picture.setPicturePath(rsPicture.getString("picturePath"));
				
				pictureList.add(picture);
			}
			
			article.setPictureList(pictureList);
			
			rsPicture.close();
			
			articleList.add(article);
		}
		
		
		rs.close();
		preState.close();
		
		return articleList;
	}
	
	@Override
	public ArticleBean queryByNumber(String articleNo) throws SQLException {
		ArticleBean article = new ArticleBean();
		
		List<ArticleContent> contentList = new ArrayList<ArticleContent>();
		ArticleContent content;
		
		List<ArticlePicture> pictureList = new ArrayList<ArticlePicture>();
		ArticlePicture picture;
		
		String sqlStr = "SELECT *"
				+ " FROM article a JOIN articleContent c ON a.contentNo = c.contentNo"
				+ " WHERE articleNo = ?;";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, articleNo);
		
		ResultSet rs = preState.executeQuery();
		
		int count = 0;
		while (rs.next()) {
			if (count == 0) {
				article.setArticleNo(rs.getString("articleNo"));
				article.setAuthor(rs.getString("author"));
				
				try {
					article.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("postTime")).getTime());
				} catch (ParseException | SQLException e) {
					e.printStackTrace();
				}
				
				article.setTitle(rs.getString("title"));
				article.setCategory(rs.getString("category"));
			}
			
			content = new ArticleContent();
			
			content.setContentNo(rs.getString("contentNo"));
			content.setIndex_para(rs.getInt("index_para"));
			content.setParagraph(rs.getString("paragraph"));
			
			contentList.add(content);
			count++;
		}
		
		article.setContentList(contentList);
		
		sqlStr = "SELECT *"
				+ " FROM article a JOIN articlePicture p ON a.pictureNo = p.pictureNo"
				+ " WHERE articleNo = ?;";
		
		preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, articleNo);
		
		rs = preState.executeQuery();
		
		while (rs.next()) {
			picture = new ArticlePicture();
			
			picture.setPictureNo(rs.getString("pictureNo"));
			picture.setIndex_pic(rs.getInt("index_pic"));
			picture.setPicturePath(rs.getString("picturePath"));
			
			pictureList.add(picture);
		}
		
		article.setPictureList(pictureList);
		
		rs.close();
		preState.close();
		
		return article;
	}

	@Override
	public void insertData(ArticleBean article) throws SQLException {
		
		List<ArticleContent> contentList = article.getContentList();
		List<ArticlePicture> pictureList = article.getPictureList();
		
		//article
		String sqlStr = "INSERT INTO"
				+ " article(articleNo, author, postTime, title, category, contentNo, pictureNo)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		preState.setString(1, article.getArticleNo());
		preState.setString(2, article.getAuthor());
		preState.setString(3, dateFormatter.format(new Date(article.getPostTime())));
		preState.setString(4, article.getTitle());
		preState.setString(5, article.getCategory());
		preState.setString(6, contentList.get(0).getContentNo());
		preState.setString(7, pictureList.get(0).getPictureNo());
		
		preState.executeUpdate();
		
		//articleContent
		sqlStr = "INSERT INTO"
				+ " articleContent(contentNo, index_para, paragraph)"
				+ " VALUES(?, ?, ?);";
		
		preState = conn.prepareStatement(sqlStr);
		
		for (int i = 0; i < contentList.size(); i++) {
			preState.setString(1, contentList.get(i).getContentNo());
			preState.setInt(2, contentList.get(i).getIndex_para());
			preState.setString(3, contentList.get(i).getParagraph());
			
			preState.addBatch();
		}
		
		preState.executeBatch();
		
		//articlePicture
		sqlStr = "INSERT INTO"
				+ " articlePicture(pictureNo, index_pic, picturePath)"
				+ " VALUES(?, ?, ?);";
		
		preState = conn.prepareStatement(sqlStr);
		
		for (int i = 0; i < pictureList.size(); i++) {
			preState.setString(1, pictureList.get(i).getPictureNo());
			preState.setInt(2, pictureList.get(i).getIndex_pic());
			preState.setString(3, pictureList.get(i).getPicturePath());
			
			preState.addBatch();
		}
		
		preState.executeBatch();
		
		preState.close();
	}

	@Override
	public void updateData(ArticleBean javaBean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteData(String number) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
