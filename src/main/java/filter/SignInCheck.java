package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
		urlPatterns = {"/createArticle.html", "/CreateArticle"},
		dispatcherTypes = {
				DispatcherType.FORWARD,
				DispatcherType.INCLUDE,
				DispatcherType.REQUEST,
				DispatcherType.ERROR,
				DispatcherType.ASYNC
		},
		initParams = {
				@WebInitParam(name = "SignInFailure_Path", value = "signIn.html")
		}
		)
public class SignInCheck extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	private String SignInFailure_Path;
	private boolean isMember;
	
	@Override
	public void init() throws ServletException{
		SignInFailure_Path = getInitParameter("SignInFailure_Path");
	}
	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request.getSession().getAttribute("isMember") != null) {
			isMember = (boolean) request.getSession().getAttribute("isMember");
		} else {
			isMember = false;
		}
		
		if (isMember) {
			chain.doFilter(request, response);
			
		} else {
			request.getRequestDispatcher(SignInFailure_Path).forward(request, response);
		}
		
	}

}