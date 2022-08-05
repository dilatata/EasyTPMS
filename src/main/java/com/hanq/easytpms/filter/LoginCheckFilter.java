package com.hanq.easytpms.filter;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.PatternMatchUtils;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*", "/swagger-ui/*","/swagger-resources", "/swagger-resources/*", "/v3/api-docs", "/h2-console", "/h2-console/*" };


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("=================== logCheck filter doFilter ===========");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (isEmpty(httpRequest)){
            log.info("request is null");
        }
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        try {
            log.info("login check filter start :  {} filter start", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("login check login :  {} check ok", requestURI);
                HttpSession session = httpRequest.getSession(false);

//                if (isEmpty(session) || isEmpty(session.getAttribute("userInfo"))) { // login 기능 없이 sessionid 공유 정도 확인하기
                if (isEmpty(session)) {

                    log.info(("=====================session is empty=========================="));
                    log.info("undefined user request :  {} check no", requestURI);
                    httpResponse.sendRedirect(""+requestURI);
                    return;
                }
                log.info(session.getAttribute("userInfo").toString());
            }

            chain.doFilter(request, response);

            addSameSite(httpResponse , "None");

        } catch (Exception e) {
            throw e; // 예외 로깅 가능하지만, 톰캣까지 예외를 보내야 함
        }finally {
            log.info("login check filter end :  {} filter end", requestURI);
        }

    }

    private void addSameSite(HttpServletResponse response, String sameSite) {

        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) { // there can be multiple Set-Cookie attributes
            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=" + sameSite));
                firstHeader = false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=" + sameSite));
        }

    }

    /**
     * 화이트 리스트는 인증 체크 X
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }

    public abstract class SessionConst {
        public static final String LOGIN_MEMBER = "loginMember";

    }

}
