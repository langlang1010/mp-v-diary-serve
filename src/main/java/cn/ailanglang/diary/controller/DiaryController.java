package cn.ailanglang.diary.controller;

import cn.ailanglang.diary.entity.Diary;
import cn.ailanglang.diary.service.DiaryService;
import cn.ailanglang.diary.util.ControllerUtil;
import cn.ailanglang.diary.util.StatisticBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/diary")
@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    @Value("${page_num}")
    private int page_num;

    @PostMapping("/add")
    private Map<String,Object> add(String content,String mood,String weather,Long userid,String session_key) {
        ControllerUtil controllerUtil = new ControllerUtil();
        Diary diary = new Diary();
        diary.setGmtModified(new Date());
        diary.setGmtCreate(new Date());
        diary.setMood(mood);
        diary.setContent(content);
        diary.setWeather(weather);
        if(this.diaryService.insertDiary(diary,userid)>0) {
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
            controllerUtil.setData_message("成功");
        }
        return controllerUtil.getJsonData();
    }

    @GetMapping("/list")
    private Map<String,Object> listDiary(Long userid, int page,String session_key) {
        ControllerUtil controllerUtil = new ControllerUtil();

        List<Diary> list = diaryService.listDiary((long) userid,page);

        if(list.size()==0) {
            controllerUtil.setData_object(null);
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
            controllerUtil.setData_message("no more");
            return controllerUtil.getJsonData();
        }
        if(list.size() < page_num) {
            controllerUtil.setData_message("no more");
        }else {
            controllerUtil.setData_message("success");
        }
        controllerUtil.setData_object(list);
        controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        return controllerUtil.getJsonData();
    }

    @PostMapping("/update")
    private Map<String,Object> update(Long userid, Long diaryid, String content,String mood, String weather,String session_key) {
        ControllerUtil controllerUtil = new ControllerUtil();
        Diary diary = new Diary();
        diary.setPkDiaryid(diaryid);
        diary.setGmtModified(new Date());
        diary.setMood(mood);
        diary.setWeather(weather);
        diary.setContent(content);
        if(diaryService.updateDiary(diary)>0){
            controllerUtil.setData_message("修改成功");
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
            controllerUtil.setData_object(new String("success"));
            return controllerUtil.getJsonData();
        }
        controllerUtil.setData_message("修改失败");
        controllerUtil.setData_status(ControllerUtil.STATUS_REFUSE_ERROR);
        return controllerUtil.getJsonData();
    }

    @GetMapping("/datelist")
    private Map<String,Object> listDiaryByDate(Long userid, int page, String date, String session_key) {
        ControllerUtil controllerUtil = new ControllerUtil();
        List<Diary> list = diaryService.listDiaryByDate(userid,page,date);
        if(list.size() == 0) {
            controllerUtil.setData_object(null);
            controllerUtil.setData_message("no more");
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
            return controllerUtil.getJsonData();
        }
        controllerUtil.setData_object(list);
        controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        if(list.size() < page_num) {
            controllerUtil.setData_message("no more");
        } else{
            controllerUtil.setData_message("success");
        }
        return controllerUtil.getJsonData();
    }

    @PostMapping("/delete")
    private Map<String,Object> deleteDiary(Long userid, Long diaryid, String session_key) {
        ControllerUtil controllerUtil = new ControllerUtil();
        if(diaryService.deleteDiary(diaryid) > 0) {
            controllerUtil.setData_object(new String("success"));
            controllerUtil.setData_message("deleted");
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        }else {
            controllerUtil.setData_message("error");
            controllerUtil.setData_object(null);
            controllerUtil.setData_status(ControllerUtil.STATUS_IDENTITY_ERROR);
        }
        return controllerUtil.getJsonData();
    }

    @GetMapping("/statistic")
    private Map<String,Object> statisticDiary(Long userid,String session_key) {
        ControllerUtil controllerUtil = new ControllerUtil();
        StatisticBean statisticBean = diaryService.statisticDiary(userid);
        if(statisticBean.getSum()==0) {
            controllerUtil.setData_object(null);
            controllerUtil.setData_message("null");
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
            return controllerUtil.getJsonData();
        }
        controllerUtil.setData_object(statisticBean);
        controllerUtil.setData_message("success");
        controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        return controllerUtil.getJsonData();
    }
}
