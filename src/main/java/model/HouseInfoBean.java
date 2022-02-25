package model;

import java.io.Serializable;

public class HouseInfoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String h_hosueNo;
//	private String acouunt;
	private String h_title;
	private String h_address;
	private int h_type;
	private String h_about;
//	private HouseOffersBean houseOffersBean;
//	private HouseRulesBean houseRulesBean;
//	private HousePhotosBean housePhotosBean;
	
	public HouseInfoBean() {
	}

	public String getH_hosueNo() {
		return h_hosueNo;
	}

	public void setH_hosueNo(String h_hosueNo) {
		this.h_hosueNo = h_hosueNo;
	}

	public String getH_title() {
		return h_title;
	}

	public void setH_title(String h_title) {
		this.h_title = h_title;
	}

	public String getH_address() {
		return h_address;
	}

	public void setH_address(String h_address) {
		this.h_address = h_address;
	}

	public int getH_type() {
		return h_type;
	}

	public void setH_type(int h_type) {
		this.h_type = h_type;
	}

	public String getH_about() {
		return h_about;
	}

	public void setH_about(String h_about) {
		this.h_about = h_about;
	}

}
