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
		urlPatterns = {"/UpdateHouseInfo"},
		initParams = {
				@WebInitParam(name = "UpdateHouseInfo_Path", value = "/JSP/UpdateHouseInfo.jsp"),
				@WebInitParam(name = "HouseInfo_Path", value = "HouseInfo")
		}
		)
public class UpdateHouseInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String UpdateHouseInfo_Path;
	private String HouseInfo_Path;
	
	@Override
	public void init() throws ServletException{
		UpdateHouseInfo_Path = getInitParameter("UpdateHouseInfo_Path");
		HouseInfo_Path = getInitParameter("HouseInfo_Path");
	}
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleBean houseInfo = new ArticleBean();
		
		houseInfo.setH_hosueNo(request.getParameter("h_hosueNo"));
		houseInfo.setH_title(request.getParameter("h_title"));
		houseInfo.setH_address(request.getParameter("h_address"));
		String h_type = request.getParameter("h_type");
		houseInfo.setH_type(Integer.parseInt(h_type));
		houseInfo.setH_about(request.getParameter("h_about"));
		
		request.setAttribute("houseInfo", houseInfo);
		
		request.getRequestDispatcher(UpdateHouseInfo_Path).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleBean houseInfo = new ArticleBean();
		
		houseInfo.setH_hosueNo(request.getParameter("h_hosueNo"));
		houseInfo.setH_title(request.getParameter("h_title"));
		houseInfo.setH_address(request.getParameter("h_address"));
		String h_type = request.getParameter("h_type");
		houseInfo.setH_type(Integer.parseInt(h_type));
		houseInfo.setH_about(request.getParameter("h_about"));
		
		HouseInfoDAOImpl dao = new HouseInfoDAOImpl();
		
		try {
			dao.updateHouseInfo(houseInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(HouseInfo_Path);
	}

}
