import cn.ailanglang.diary.DiaryApplication;
import cn.ailanglang.diary.entity.Diary;
import cn.ailanglang.diary.entity.UserInfo;
import cn.ailanglang.diary.service.DiaryService;
import cn.ailanglang.diary.service.UserInfoService;
import cn.ailanglang.diary.util.StatisticBean;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author smileyan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiaryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiaryServiceTest {
    @Autowired
    private DiaryService diaryService;

    @Test
    public void test() {
        Diary diary = new Diary();
        diary.setContent("Hello World");
        diary.setGmtCreate(new Date());
        diary.setGmtModified(new Date());
    }

    @Test
    public void test2() {
        List<Diary> diaryList = diaryService.listDiary((long) 24,1);
        for (Diary diary: diaryList) {
            System.out.println(diary.getContent());
            System.out.println(diary.getState());
        }
    }

    @Test
    public void test3() {
        Diary diary = new Diary();
        diary.setPkDiaryid((long) 15);
        diary.setContent("要加的项目（item1 … itemN）会按照从左到右的顺序添加到数组。如果某一项为数组，那么添加其内容到 array1 的末尾。如果该项目不是数组，就将其作为单个的数组元素添加到数组的末尾。");
        diary.setWeather("1");
        diary.setMood("1");
        diary.setGmtModified(new Date());
        diaryService.updateDiary(diary);
    }

    @Test
    public void test4() {
        List<Diary> list = diaryService.listDiaryByDate((long) 24,1,"2019-03-09");
        for (Diary diary:list) {
            System.out.println(diary.getContent());
        }
    }

    @Test
    public void test5() {
        System.out.println(diaryService.deleteDiary((long) 37));
    }

    @Test
    public void test6() {
        StatisticBean statisticBean = diaryService.statisticDiary((long) 25);
        System.out.println("deleted=="+statisticBean.getDeleted());
        System.out.println("sum=="+statisticBean.getSum());
        System.out.println("mood(0) == "+statisticBean.getMood0());
        System.out.println("mood(1) == "+statisticBean.getMood1());
        System.out.println("mood(2) == "+statisticBean.getMood2());
        System.out.println("mood(3) == "+statisticBean.getMood3());
        System.out.println("weather(0) == "+statisticBean.getWeather0());
        System.out.println("weather(1) == "+statisticBean.getWeather1());
        System.out.println("weather(2) == "+statisticBean.getWeather2());
        System.out.println("weather(3) == "+statisticBean.getWeather3());
        System.out.println("weather(4) == "+statisticBean.getWeather4());
        System.out.println("weather(5) == "+statisticBean.getWeather5());
        System.out.println("weather(6) == "+statisticBean.getWeather6());
        System.out.println("weather(7) == "+statisticBean.getWeather7());
    }

}
