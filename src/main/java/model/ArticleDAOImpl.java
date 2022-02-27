package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class ArticleDAOImpl implements GenericDAO<ArticleBean>{
	
	private Connection conn;
	
	public ArticleDAOImpl() {
		this.conn = ConnectionFactory.getConnection();
	}

	@Override
	public List<ArticleBean> queryAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
		
		preState.setString(1, article.getArticleNo());
		preState.setString(2, "Author");
		preState.setDate(3, new java.sql.Date(article.getPostTime().getTime()));
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
