package com.qs.www.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qs.www.main.model.dto.AuthorityDTO;

@WebFilter(urlPatterns = "/mng/*")
public class AuthenticationFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		
		String uri = hrequest.getRequestURI();
		String intent = uri.replace(hrequest.getContextPath(), "");
		
		/* 세션에 권한이 있는지 확인하여 허용된 url에만 접근 가능하도록 설정한다. */
		HttpSession requestSession = hrequest.getSession();
		
		@SuppressWarnings("unchecked")
		List<AuthorityDTO> memberAuthority = (List<AuthorityDTO>) requestSession.getAttribute("memberAuthority");
		List<String> menuUri = new ArrayList<>();
		
		boolean isAuthorized = false;
		
		if(memberAuthority != null) {
			for(AuthorityDTO authority : memberAuthority) {
				System.out.println("Authority : " + authority.getMenuUri());
				if(authority.getMenuUri().equals(intent)) {
					isAuthorized = true;
				}
			}
		}
		
		if(isAuthorized) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendError(403);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}
