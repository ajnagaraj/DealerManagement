package com.powerhaus.brookleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.powerhaus.brookleaf")
public class DealerManagementWebConfiguration extends WebMvcConfigurerAdapter {
    private static final String TEMPLATE_RESOLVER_PREFIX = "/WEB-INF/templates/";
    private static final String TEMPLATE_RESOLVER_SUFFIX = ".html";
    private static final String TEMPLATE_MODE = "HTML5";
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        
        return viewResolver;
    }
    
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        
        return templateEngine;
    }
    
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new SpringResourceTemplateResolver();
        
        templateResolver.setPrefix(TEMPLATE_RESOLVER_PREFIX);
        templateResolver.setSuffix(TEMPLATE_RESOLVER_SUFFIX);
        templateResolver.setTemplateMode(TEMPLATE_MODE);
        
        return templateResolver;
    }
}
