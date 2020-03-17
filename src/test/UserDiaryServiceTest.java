import cn.ailanglang.diary.DiaryApplication;
import cn.ailanglang.diary.entity.Diary;
import cn.ailanglang.diary.entity.UserDiary;
import cn.ailanglang.diary.entity.UserInfo;
import cn.ailanglang.diary.service.DiaryService;
import cn.ailanglang.diary.service.UserDiaryService;
import cn.ailanglang.diary.service.UserInfoService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

/**
 * @author smileyan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiaryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDiaryServiceTest {
    @Autowired
    private UserDiaryService userDiaryService;
    @Test
    public void test() {
        UserDiary userDiary = new UserDiary();
        userDiary.setFkDiaryid((long) 1);
        userDiary.setFkUserid((long) 1);
        System.out.println(userDiaryService.insertUserDiary(userDiary));
    }

    @Test
    public void test2() {
        System.out.println("count == "+ userDiaryService.countDiary((long) 25));
    }
}
