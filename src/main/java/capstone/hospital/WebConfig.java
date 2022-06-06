package capstone.hospital;

import capstone.hospital.argumentresolver.LoginArgumentResolver;
import capstone.hospital.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> whitePath = Arrays.asList("/", "/join/**", "/login/**", "/logout", "/css/**", "/error", "/*.ico",
                "/assets/**", "/images/**", "/js/**", "/check/**", "/file/**", "/main/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new DoctorCheckInterceptor())
                .order(2)
                .addPathPatterns("/doctor/**")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new NurseCheckInterceptor())
                .order(2)
                .addPathPatterns("/nurse/**")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(whitePath);
    }
}
