package com.bdi.sb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ViewsController {
	
	
	@GetMapping("/app/**")//사용자쪽
	public String goAppPage(HttpServletRequest request) {
		return request.getRequestURI();
	}
	
	@GetMapping("/venue/**")//가맹점쪽
	public String goVenuePage(HttpServletRequest request) {
		return request.getRequestURI();
	}

	@GetMapping("/admin/**")//관리자쪽
	public String goAdminPage(HttpServletRequest request) {
		return request.getRequestURI();
	}
}
