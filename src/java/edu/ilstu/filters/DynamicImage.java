/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.filters;

import edu.ilstu.helper.Property;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kawibi
 */
public class DynamicImage implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest httpreq = (HttpServletRequest) request;
            HttpServletResponse httpres = (HttpServletResponse) response;

       
            // Get image file.
            String file = httpreq.getParameter("file");
            String sessionID = httpreq.getParameter("sessionID");
            String onlineClassID = httpreq.getParameter("onlineClassID");
            System.out.println("in the image filter\n the file name:" + file);
            byte[] bytes;
            File file2 = new File(Property.getSavePath()+ onlineClassID + "/session_" + sessionID + "/slides/", file);
            // Get image contents.
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file2))) {
                // Get image contents.
                bytes = new byte[in.available()];
                in.read(bytes);
            }

            // Write image contents to response.
            httpres.getOutputStream().write(bytes);

            
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void destroy() {

    }

}
