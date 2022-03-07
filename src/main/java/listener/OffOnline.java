package listener;

import java.sql.SQLException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.MemberBean;
import model.MemberDAOImpl;

@WebListener
public class OffOnline implements HttpSessionListener {
	private MemberDAOImpl memberDAO;
	private MemberBean member;

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
    	memberDAO = (MemberDAOImpl) session.getServletContext().getAttribute("memberDAO");
    	member = (MemberBean) session.getAttribute("member");
    	
    	String account = member.getAccount();
    	System.out.println("sessionDestroyed, account = " + account);
    	
    	try {
			memberDAO.updateOnline(account, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
}
