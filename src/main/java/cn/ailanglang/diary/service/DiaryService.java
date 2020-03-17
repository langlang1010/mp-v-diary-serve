package cn.ailanglang.diary.service;

import cn.ailanglang.diary.entity.Diary;
import cn.ailanglang.diary.util.StatisticBean;

import java.util.List;

/**
 * @author smileyan
 */
public interface DiaryService {
    // 删除的状态表示
    String DELETED_STATE = "deleted";
    /**
     * 添加日记
     * @param diary
     * @return
     */
    Long insertDiary(Diary diary,Long userid);


    /**
     * @param userid
     * @param page
     * @return
     */
    List<Diary> listDiary(Long userid,int page);


    /**
     * @param diary
     * @return
     */
    int updateDiary(Diary diary);


    /**
     * @param userid
     * @param page
     * @param endDate
     * @return
     */
    List<Diary> listDiaryByDate(Long userid, int page, String endDate);

    /**
     *
     * @param diaryid
     * @return
     */
    int deleteDiary(Long diaryid);

    /**
     * @param userid
     * @return
     */
    StatisticBean statisticDiary(Long userid);
}
