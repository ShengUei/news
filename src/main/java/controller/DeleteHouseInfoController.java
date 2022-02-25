package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticleBean;
import model.HouseInfoDAOImpl;

@WebServlet(
		urlPatterns = {"/DeleteHouseInfo"},
		initParams = {
				@WebInitParam(name = "HouseInfo_Path", value = "HouseInfo")
		}
		)
public class DeleteHouseInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String HouseInfo_Path;
	
	@Override
	public void init() throws ServletException{
		HouseInfo_Path = getInitParameter("HouseInfo_Path");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleBean houseInfo = new ArticleBean();
		
		houseInfo.setH_hosueNo(request.getParameter("h_hosueNo"));
		
		HouseInfoDAOImpl dao = new HouseInfoDAOImpl();
		
		try {
			dao.deleteHouseInfo(houseInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(HouseInfo_Path);
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

}
