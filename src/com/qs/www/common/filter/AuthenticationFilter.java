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
import javax.servlet.http.HttpSession;

import com.qs.www.main.model.dto.AuthorityDTO;
import com.qs.www.main.model.service.MainService;
import com.qs.www.member.model.dto.MemberInfoDTO;
import com.qs.www.member.model.dto.RoleDTO;
import com.qs.www.mng.employee.model.service.MngEmployeeService;

@WebFilter(urlPatterns = "/mng/*")
public class AuthenticationFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		
		String uri = hrequest.getRequestURI();
		String intent = uri.replace(hrequest.getContextPath(), "");
		
		/* 세션에 권한이 있는지 확인하여 허용된 url에만 접근 가능하도록 설정한다. */
		HttpSession requestSession = hrequest.getSession();
		
		MemberInfoDTO memberInfo = (MemberInfoDTO) requestSession.getAttribute("memberInfo");
		String roleCode = memberInfo.getRole().getRoleCode();
		
		boolean isAuthorized = false;
		
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
//		MainService mainService = new MainService();
//		MngEmployeeService mngEmployeeService = new MngEmployeeService();
//		
//		List<RoleDTO> roleList = mngEmployeeService.selectRoleList();
//		List<AuthorityDTO> roleAuthorityList = new ArrayList(); 
//		
//		for(RoleDTO role : roleList) {
//			
//		}
//		
//		List<String> accessMenu = new ArrayList<>();
//		for(AuthorityDTO roleAuthority : roleAuthorityList) {
//			String menu = roleAuthority.getMenuUri().split("/")[2];
//			
//			if(!accessMenu.contains(menu)) {
//				accessMenu.add(menu);
//			}
//		}
	}
}
