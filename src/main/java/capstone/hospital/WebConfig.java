package capstone.hospital;

import capstone.hospital.argumentresolver.LoginArgumentResolver;
import capstone.hospital.interceptor.AdminCheckInterceptor;
import capstone.hospital.interceptor.DoctorCheckInterceptor;
import capstone.hospital.interceptor.LoginCheckInterceptor;
import capstone.hospital.interceptor.NurseCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/join/**", "/login/**", "/logout", "/css/**", "/error", "/*.ico", "/assets/**", "/images/**", "/js/**", "/check/**", "*/assets/**");

        registry.addInterceptor(new DoctorCheckInterceptor())
                .order(2)
                .addPathPatterns("/doctor/**")
                .excludePathPatterns("/", "/join/**", "/login/**", "/logout", "/css/**", "/error", "/*.ico", "/assets/**", "/images/**", "/js/**", "/check/**", "*/assets/**");

        registry.addInterceptor(new NurseCheckInterceptor())
                .order(2)
                .addPathPatterns("/nurse/**")
                .excludePathPatterns("/", "/join/**", "/login/**", "/logout", "/css/**", "/error", "/*.ico", "/assets/**", "/images/**", "/js/**", "/check/**", "*/assets/**");

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/", "/join/**", "/login/**", "/logout", "/css/**", "/error", "/*.ico", "/assets/**", "/images/**", "/js/**", "/check/**", "*/assets/**");
    }
}
