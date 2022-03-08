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
import javax.servlet.http.HttpSession;

import model.MemberBean;
import model.MemberDAOImpl;

@WebServlet(
		urlPatterns = {"/CreateMember"},
		initParams = {
				@WebInitParam(name = "SignUpSuccess_Path", value = "index.html"),
				@WebInitParam(name = "SignUpFailure_Path", value = "signUp.html")
		}
		)
public class CreateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String SignUpSuccess_Path;
	private String SignUpFailure_Path;
	private boolean isMember;
	private MemberDAOImpl memberDAO;
	
	@Override
	public void init() throws ServletException{
		SignUpSuccess_Path = getInitParameter("SignUpSuccess_Path");
		SignUpFailure_Path = getInitParameter("SignUpFailure_Path");
		memberDAO = (MemberDAOImpl) getServletContext().getAttribute("memberDAO");
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
		member.setEncrypt_pwd(String.valueOf(salt + password.hashCode()));
		
		try {
			memberDAO.insertData(member);
			
			HttpSession session = request.getSession(true);
			request.changeSessionId();
			session.setAttribute("member", member);
			isMember = true;
			session.setAttribute("isMember", isMember);
			
			response.sendRedirect(SignUpSuccess_Path);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(SignUpFailure_Path);
		}
		
	}

}
