package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;
import model.MemberDAOImpl;

@WebServlet(
		urlPatterns = {"/CreateMember"},
		initParams = {
				@WebInitParam(name = "SignUpSuccess_Path", value = "articleList.html"),
				@WebInitParam(name = "SignUpFailure_Path", value = "signUpMember.html")
		}
		)
public class CreateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String SignUpSuccess_Path;
	private String SignUpFailure_Path;
	
	@Override
	public void init() throws ServletException{
		SignUpSuccess_Path = getInitParameter("SignUpSuccess_Path");
		SignUpFailure_Path = getInitParameter("SignUpFailure_Path");
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberBean member = new MemberBean();
		
		member.setUsername(request.getParameter("username"));
		member.setAccount(request.getParameter("account"));
		
		String password = request.getParameter("password");
		int salt = ThreadLocalRandom.current().nextInt();
		
		member.setSalt(String.valueOf(salt));
		member.setHashed_pwd(String.valueOf(salt + password.hashCode()));
		
		MemberDAOImpl dao = new MemberDAOImpl();
		
		try {
			dao.insertData(member);
			response.sendRedirect(SignUpSuccess_Path);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(SignUpFailure_Path);
		}
		
	}

}
