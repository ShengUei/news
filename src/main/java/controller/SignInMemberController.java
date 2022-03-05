package controller;

import java.io.IOException;
import java.sql.SQLException;

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
		urlPatterns = {"/SignInMember", "/SignOutMember"},
		initParams = {
				@WebInitParam(name = "SignInSuccess_Path", value = "index.html"),
				@WebInitParam(name = "SignInFailure_Path", value = "signIn.html"),
				@WebInitParam(name = "SignOut_Path", value = "index.html"),
		}
		)
public class SignInMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String SignInSuccess_Path;
	private String SignInFailure_Path;
	private String SignOut_Path;
	private boolean isMember;
	private MemberDAOImpl memberDAO;
	
	@Override
	public void init() throws ServletException{
		SignInSuccess_Path = getInitParameter("SignInSuccess_Path");
		SignInFailure_Path = getInitParameter("SignInFailure_Path");
		SignOut_Path = getInitParameter("SignOut_Path");
		memberDAO = (MemberDAOImpl) getServletContext().getAttribute("memberDAO");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(SignOut_Path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.changeSessionId();
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		try {
			MemberBean member = memberDAO.queryByName(account);
			
			if ((password.hashCode() + Integer.parseInt(member.getSalt())) == Integer.parseInt(member.getHashed_pwd())) {
				session.setAttribute("member", member);
				isMember = true;
				session.setAttribute("isMember", isMember);
				response.sendRedirect(SignInSuccess_Path);
			} else {
				response.sendRedirect(SignInFailure_Path);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
