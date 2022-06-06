package capstone.hospital.interceptor;

import capstone.hospital.domain.Doctor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class DoctorCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("의사 접근 권한 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        Object loginMember = session.getAttribute("loginMember");

        if (loginMember.getClass() != Doctor.class) {
            log.info("접근 권한이 없음");
            response.sendRedirect("/");
            return false;
        }

        if (((Doctor) loginMember).getApprovalAdmin() == null) {
            log.info("승인되지 않은 직원");
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
