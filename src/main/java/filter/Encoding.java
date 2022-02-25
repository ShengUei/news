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
		urlPatterns = {"/*"},
		dispatcherTypes = {
				DispatcherType.FORWARD,
				DispatcherType.INCLUDE,
				DispatcherType.REQUEST,
				DispatcherType.ERROR,
				DispatcherType.ASYNC
		},
		initParams = {
				@WebInitParam(name = "Encoding", value = "UTF-8")
		}
		)
public class Encoding extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	private String encoding;
	
	@Override
	public void init() throws ServletException{
		encoding = getInitParameter("Encoding");
	}
	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
		
	}

}
