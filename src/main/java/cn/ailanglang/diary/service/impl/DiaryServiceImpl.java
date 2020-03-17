package cn.ailanglang.diary.service.impl;

import cn.ailanglang.diary.dao.DiaryDao;
import cn.ailanglang.diary.entity.Diary;
import cn.ailanglang.diary.entity.UserDiary;
import cn.ailanglang.diary.service.DiaryService;
import cn.ailanglang.diary.service.UserDiaryService;
import cn.ailanglang.diary.util.StatisticBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author smileyan
 */
@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryDao diaryDao;
    @Autowired
    private UserDiaryService userDiaryService;

    @Value("${page_num}")
    private int page_num;
    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public Long insertDiary(Diary diary,Long userid) {
        if(diaryDao.insertDiary(diary)>0) {
            Long diaryid =  diary.getPkDiaryid();
            UserDiary userDiary = new UserDiary();
            userDiary.setFkUserid(userid);
            userDiary.setFkDiaryid(diaryid);
            userDiaryService.insertUserDiary(userDiary);
            return diaryid;
        }
        return (long)-1;
    }

    @Override
    public List<Diary> listDiary(Long userid, int page) {
        int page1;
        page1 = (page - 1) * page_num;
        return diaryDao.listDiary(userid,page1,page_num);
    }

    @Override
    public int updateDiary(Diary diary) {
        return diaryDao.updateDiary(diary);
    }

    @Override
    public List<Diary> listDiaryByDate(Long userid, int page, String endDate) {
        int startPage;
        startPage = (page - 1) * page_num;
        return diaryDao.listDiaryByDate(userid,startPage,page_num,endDate);
    }

    @Override
    public int deleteDiary(Long diaryid) {
        Diary diary = new Diary();
        diary.setPkDiaryid(diaryid);
        diary.setState(DELETED_STATE);
        return diaryDao.deleteDiary(diary);
    }

    @Override
    public StatisticBean statisticDiary(Long userid) {
        return diaryDao.statisticDiary(userid);
    }
}
