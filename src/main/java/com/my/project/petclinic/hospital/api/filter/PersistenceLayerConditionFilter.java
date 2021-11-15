package com.my.project.petclinic.hospital.api.filter;

import com.my.project.petclinic.hospital.persistence.config.PersistenceLayerBooleanCondition;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class PersistenceLayerConditionFilter implements Filter {

    @Autowired
    private PersistenceLayerBooleanCondition condition;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String isJdbc = request.getHeader("isJdbc");
        condition.setJdbc(Boolean.parseBoolean(isJdbc));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
