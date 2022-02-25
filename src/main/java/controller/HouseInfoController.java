package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HouseInfoBean;
import model.HouseInfoDAOImpl;

@WebServlet(
		urlPatterns = {"/HouseInfo"},
		initParams = {
				@WebInitParam(name = "HouseInfo_Path", value = "/JSP/HouseInfo.jsp")
		}
		)
public class HouseInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String HouseInfo_Path;
	
	@Override
	public void init() throws ServletException{
		HouseInfo_Path = getInitParameter("HouseInfo_Path");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HouseInfoDAOImpl dao = new HouseInfoDAOImpl();
		
		try {
			List<HouseInfoBean> houseInfoList;
			String search = request.getParameter("search");
			
			if (request.getParameter("search") != "" && request.getParameter("search") != null) {
				houseInfoList = dao.queryHouseInfoByH_type(search);
			} else {
				houseInfoList = dao.queryHouseInfo();
			}
			
			request.setAttribute("houseInfoList", houseInfoList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(HouseInfo_Path).forward(request, response);
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
