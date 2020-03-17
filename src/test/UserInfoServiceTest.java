import cn.ailanglang.diary.DiaryApplication;
import cn.ailanglang.diary.entity.UserInfo;
import cn.ailanglang.diary.service.UserInfoService;
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
public class UserInfoServiceTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPkUserid((long) 1);
        userInfo.setPkUserid((long) 1);
        userInfo.setGmtCreate(new Date());
        userInfo.setGmtModified(new Date());
        userInfo.setOpenid("abc");
        System.out.println(userInfoService.insertUserInfo(userInfo));
//        TestCase.assertEquals(true,userInfoService.insertUserInfo(userInfo));
    }

    @Test
    public void test2() {
        Long userid = userInfoService.getUseridByOpenid("a23bc");
        System.out.println(userid);
    }

    @Test
    public void test3() {
        UserInfo userInfo = userInfoService.getUserInfoByUserid((long) 1);
        System.out.println(userInfo.getOpenid());
    }

}
