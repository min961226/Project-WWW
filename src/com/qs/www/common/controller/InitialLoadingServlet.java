package com.qs.www.common.controller;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.qs.www.common.config.ConfigLocation;

@WebServlet(urlPatterns = {}, loadOnStartup = 1)
public class InitialLoadingServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) {
		
		ConfigLocation.mybatisConfigLocation = config.getServletContext().getInitParameter("mybatis-config-location");
	}
}
