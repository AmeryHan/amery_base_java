package dubbo.config;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/12/12 8:20 PM
 * 4
 */
@Service
public class ConsumerDemo {

    @Reference
    private UserService userService;

    public void print() {
        userService.listUser("ahan");
    }
}
