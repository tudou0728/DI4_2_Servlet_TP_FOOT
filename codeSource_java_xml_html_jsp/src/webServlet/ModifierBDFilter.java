package webServlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//filter: juste certaines personnes peut modifier la BD.
public class ModifierBDFilter implements Filter
{
			private ServletContext ctx;
			
			public void init(FilterConfig cfg) throws ServletException 
			{
				ctx = cfg.getServletContext();
			}
			
			public void destroy() {}

			public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
			{
				HttpServletRequest httpRequest = (HttpServletRequest)request;
				HttpServletResponse httpResponse = (HttpServletResponse)response;
				HttpSession session = httpRequest.getSession();
				String authorise = (String)session.getAttribute("user");
				if ("authorisierModifierBD".equals(authorise))
				{
					chain.doFilter(request, response);
				}
				else
				{
					httpResponse.sendRedirect(ctx.getContextPath()+"/nonAUthenModifierBD.html");
				}
			}

}
