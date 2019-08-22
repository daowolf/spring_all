package hello.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auther: BigDaddy
 * @Date: 2019/8/22 23:35
 * @Description:
 */
@Component
public class MyUserDetailsServer implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("admin".equals(username)){

            throw new RuntimeException("admin禁止登录");
        }
        return new User(username, "$2a$10$ofPkBDUezOJp6Sik63Q/0.QlU8a1itEyzldjSXqfn2nDPqXjN0Ljm", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
