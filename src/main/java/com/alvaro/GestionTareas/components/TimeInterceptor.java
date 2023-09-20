package com.alvaro.GestionTareas.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
			throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		log.info(request.getRequestURI() + " " + (endTime - startTime) + "ms");
	} 
}
