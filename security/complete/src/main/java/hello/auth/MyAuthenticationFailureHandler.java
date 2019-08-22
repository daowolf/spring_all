package hello.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: BigDaddy
 * @Date: 2019/8/22 23:38
 * @Description: 认证失败拦截器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse response, AuthenticationException authentication)
            throws IOException, ServletException {

        logger.info(authentication.getMessage()+"---登录失败");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.getWriter().println("<script>alert('"+ authentication.getMessage()+"');</script>");

    }
}
