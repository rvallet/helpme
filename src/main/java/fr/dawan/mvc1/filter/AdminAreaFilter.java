package fr.dawan.mvc1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.mvc1.beans.User;

@WebFilter("/admin/*")
public class AdminAreaFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			if (!httprequest.getSession().getAttribute("status").equals(User.StatusUser.ADMIN.toString())) {
				httpResponse.sendError(403, "Vous n'êtes pas autorisé à accéder à cette page");
				return;
			} else
				chain.doFilter(httprequest, httpResponse);
			return;
		} catch (NullPointerException e) {
			httpResponse.sendError(403, "Vous n'êtes pas autorisé à accéder à cette page");
			return;
		}
	}

}