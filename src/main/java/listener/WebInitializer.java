package listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import model.ArticleDAOImpl;
import model.MemberDAOImpl;


@WebListener
public class WebInitializer implements ServletContextListener {
	
	private DataSource dataSource;

    public void contextInitialized(ServletContextEvent sce)  {
    	try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/SideProject1_News");
			MemberDAOImpl memberDAO = new MemberDAOImpl(dataSource);
			ArticleDAOImpl articleDAO = new ArticleDAOImpl(dataSource);
			
			ServletContext context = sce.getServletContext();
			context.setAttribute("memberDAO", memberDAO);
			context.setAttribute("articleDAO", articleDAO);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	
}
