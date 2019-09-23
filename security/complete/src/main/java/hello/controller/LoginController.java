package hello.controller;

import hello.aspect.WebLog;
import hello.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: BigDaddy
 * @Date: 2019/9/16 23:31
 * @Description:
 */
@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/user/login")
    @WebLog(description = "请求了用户登陆接口")
    public User userLogin(@RequestBody User user) {
        logger.info("user login.....");
        return user;
    }
}
