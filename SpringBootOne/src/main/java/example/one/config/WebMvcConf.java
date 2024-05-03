package example.one.config;

import example.one.interceptor.LatencyInterceptor;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.time.Duration;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {
    // 添加全局拦截器
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new LatencyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");
    }

    // 添加全局跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/user/**")
                        .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET","POST","PUT","DELETE");
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver("locale");
        resolver.setCookieMaxAge(Duration.ofDays(1));
        return resolver;
    }
}
