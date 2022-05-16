package capstone.hospital;

import capstone.hospital.argumentresolver.LoginArgumentResolver;
import capstone.hospital.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Array;
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
                "/assets/**", "/images/**", "/js/**", "/check/**", "/file/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new StaffCheckInterceptor())
                .order(2)
                .addPathPatterns("/staff")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new DoctorCheckInterceptor())
                .order(3)
                .addPathPatterns("/doctor/**")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new NurseCheckInterceptor())
                .order(3)
                .addPathPatterns("/nurse/**")
                .excludePathPatterns(whitePath);

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(3)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(whitePath);
    }
}
