package model;

import java.io.Serializable;

public class ArticleContentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contentNo;
	private String articleNo;
	private String paragraph;
	
	public ArticleContentBean() {
	}

	public String getContentNo() {
		return contentNo;
	}

	public void setContentNo(String contentNo) {
		this.contentNo = contentNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

}
