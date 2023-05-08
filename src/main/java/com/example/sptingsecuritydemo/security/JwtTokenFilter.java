package com.example.sptingsecuritydemo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

		try {
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (JwtAuthenticationException ex) {
			SecurityContextHolder.clearContext();
			((HttpServletResponse) response).sendError(ex.getHttpStatus().value());
			throw new JwtAuthenticationException("Jwt token is expired or invalid");
		}
		chain.doFilter(request, response);
	}

}
