package com.my.project.petclinic.hospital.api.config;

import com.my.project.petclinic.hospital.api.filter.PersistenceLayerConditionInterceptor;
import com.my.project.petclinic.hospital.persistence.config.PersistenceLayerBooleanCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private PersistenceLayerBooleanCondition condition;

    @Bean
    public PersistenceLayerConditionInterceptor getPersistenceInterceptor(){
        return new PersistenceLayerConditionInterceptor(condition);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPersistenceInterceptor());
    }
}
