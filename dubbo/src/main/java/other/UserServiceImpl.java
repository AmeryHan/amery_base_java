package other;

import org.apache.dubbo.config.annotation.Service;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/12/11 3:58 PM
 * 4
 */
@Service(version = "1.0.0", timeout = 20000, validation = "true")
public class UserServiceImpl implements UserService {

    @Override
    public void listUser(String userName) {
        System.out.println("call from provider" + userName);
    }
}
