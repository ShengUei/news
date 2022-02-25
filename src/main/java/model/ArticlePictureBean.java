package model;

import java.io.Serializable;

public class ArticlePictureBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String pictureNo;
	private String articleNo;
	private String picturePath;
	
	public ArticlePictureBean() {
	}

	public String getPictureNo() {
		return pictureNo;
	}

	public void setPictureNo(String pictureNo) {
		this.pictureNo = pictureNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}
