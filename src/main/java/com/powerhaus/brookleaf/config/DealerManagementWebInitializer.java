package com.powerhaus.brookleaf.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Represents a DispatcherServlet configuration.
 */
public class DealerManagementWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        
        return new Class[] { DealerManagementRootConfiguration.class };
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        
        return new Class[] { DealerManagementWebConfiguration.class };
    }
    
    @Override
    protected String[] getServletMappings() {
        
        return new String[] { "/" };
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("spring.profiles.default", "development");
    }
}
