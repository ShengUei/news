package model;

import java.io.Serializable;
import java.util.List;

public class ArticleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String articleNo;
	private String author;
	private Long postTime;
	private Long lastUpdateTime;
	private String category;
	private String title;
	private List<ArticleContent> contentList;
	private List<ArticlePicture> pictureList;
	
	public ArticleBean() {
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getPostTime() {
		return postTime;
	}

	public void setPostTime(Long postTime) {
		this.postTime = postTime;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ArticleContent> getContentList() {
		return contentList;
	}

	public void setContentList(List<ArticleContent> contentList) {
		this.contentList = contentList;
	}

	public List<ArticlePicture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<ArticlePicture> pictureList) {
		this.pictureList = pictureList;
	}

}
