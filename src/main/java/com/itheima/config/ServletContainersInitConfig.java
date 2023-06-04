package com.itheima.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.lang.annotation.Annotation;
import java.util.EnumSet;

public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {

    //加载配置文件
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMVCConfiguration.class);
        return ctx;
    }

    //映射
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("UTF-8");

        FilterRegistration.Dynamic registration = servletContext.addFilter("characterEncodingFilter", cef);
        registration.addMappingForUrlPatterns(EnumSet.of
                (DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE),
                false,
                "/*");
    }
}
