package hello.controller;

/**
 * @Auther: BigDaddy
 * @Date: 2019/9/16 23:31
 * @Description:
 */
@Controller
public class LoginController {
    @PostMapping("/user/login")
    @WebLog(description = "请求了用户登陆接口")
    public User userLogin(@RequestBody User user){
        logger.info("user login.....");
        return user;
    }
}
