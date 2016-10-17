package com.powerhaus.brookleaf.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Represents a DispatcherServlet configuration.
 */
public class DealerManagementWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { DealerManagementWebConfiguration.class };
    }
    
    @Override
    protected String[] getServletMappings() {
        
        return new String[] { "/" };
    }
}
