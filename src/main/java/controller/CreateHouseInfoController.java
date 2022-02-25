package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HouseInfoBean;
import model.HouseInfoDAOImpl;

@WebServlet(
		urlPatterns = {"/CreateHouseInfo"},
		initParams = {
				@WebInitParam(name = "CreateHouseInfo_Path", value = "/JSP/CreateHouseInfo.jsp"),
				@WebInitParam(name = "HouseInfo_Path", value = "HouseInfo")
		}
		)
public class CreateHouseInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String CreateHouseInfo_Path;
	private String HouseInfo_Path;
	
	@Override
	public void init() throws ServletException{
		CreateHouseInfo_Path = getInitParameter("CreateHouseInfo_Path");
		HouseInfo_Path = getInitParameter("HouseInfo_Path");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(CreateHouseInfo_Path).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HouseInfoBean houseInfo = new HouseInfoBean();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		houseInfo.setH_hosueNo(sdf.format(date.getTime()) + "H");
		
		houseInfo.setH_title(request.getParameter("h_title"));
		houseInfo.setH_address(request.getParameter("h_address"));
		String h_type = request.getParameter("h_type");
		houseInfo.setH_type(Integer.parseInt(h_type));
		houseInfo.setH_about(request.getParameter("h_about"));
		
		HouseInfoDAOImpl dao = new HouseInfoDAOImpl();
		
		try {
			dao.insertHouseInfo(houseInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(HouseInfo_Path);
	}

}
