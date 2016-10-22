package com.powerhaus.brookleaf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.powerhaus.brookleaf.entity",
        "com.powerhaus.brookleaf.repository",
        "com.powerhaus.brookleaf.service"})
public class DealerManagementRootConfiguration {
    
    
}
