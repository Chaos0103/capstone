package capstone.hospital.interceptor;

import capstone.hospital.domain.Admin;
import capstone.hospital.domain.Doctor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("관리자 접근 권한 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        Object loginMember = session.getAttribute("loginMember");

        if (loginMember.getClass() != Admin.class) {
            log.info("접근 권한이 없음");
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
