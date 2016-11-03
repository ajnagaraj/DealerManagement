package com.powerhaus.brookleaf.config;

import com.powerhaus.brookleaf.converter.DealerToDealerFormConverter;
import com.powerhaus.brookleaf.converter.ErrorsToDealerErrorConverter;
import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.error.DealerError;
import com.powerhaus.brookleaf.form.DealerForm;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

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
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
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
    
    @Bean
    public FactoryBean conversionService(Converter<Errors, DealerError> errorsDealerErrorConverter,
                                         Converter<Dealer, DealerForm> dealerToDealerFormConverter) {
        ConversionServiceFactoryBean conversionServiceFactory = new ConversionServiceFactoryBean();
    
        Set<?> converters = new HashSet<>(asList(errorsDealerErrorConverter, dealerToDealerFormConverter));
        conversionServiceFactory.setConverters(converters);
    
        return conversionServiceFactory;
    }
}
