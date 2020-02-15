package com.bob.common.config;

import com.bob.common.interceptor.WebRequestInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by lh on 2018/8/13.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${spring.jackson.date-format}")
    private String dateFormat;

    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @Autowired
    private WebRequestInterceptor webRequestInterceptor;

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        //由于Long型序列换成json时会丢失精度,所以将所有的long转成string
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        objectMapper.setDateFormat(dateFormat);
        TimeZone timeZone = TimeZone.getTimeZone(this.timeZone);
        objectMapper.setTimeZone(timeZone);
        converters.add(converter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webRequestInterceptor)
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
