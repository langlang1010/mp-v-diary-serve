import cn.ailanglang.diary.DiaryApplication;
import cn.ailanglang.diary.entity.User;
import cn.ailanglang.diary.service.UserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author smileyan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiaryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setGmtModified(new Date());
        user.setGmtCreate(new Date());
        userService.insertUser(user);
//        TestCase.assertEquals("123",userService.getUserById((long)1).getPassword());
//        TestCase.assertEquals(null,userService.getUserById((long) 0));
    }
}
