package com.tech.oa.env;

import com.tech.oa.filter.AccessFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MybatisConfig.class, ActivitiConfig.class, MvcConfig.class};  //web上下文
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        container.addFilter("AccessFilter", AccessFilter.class).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, this.getServletName());
        super.onStartup(container);
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}