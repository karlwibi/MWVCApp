/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.filters;


import java.io.IOException;
import javax.faces.context.FacesContext;
 
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

/**
 *
 * @author kawibi
 */
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpres = (HttpServletResponse) response;
    
        String userName = httpreq.getUserPrincipal().getName();
         
        System.out.println("Yeeey! Get me here and find me in the database: " + userName);
 
        chain.doFilter(request, response); 
    }

    @Override
    public void destroy() {
       
    }
    
}
