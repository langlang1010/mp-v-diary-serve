package cn.ailanglang.diary.dao;

import cn.ailanglang.diary.entity.Diary;
import cn.ailanglang.diary.util.StatisticBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author smileyan
 */
public interface DiaryDao {
    /**
     * @param diary
     * @return
     */
    int insertDiary(Diary diary);

    /**
     * list 日记，8篇一页
     * @param userid
     * @return
     */
    List<Diary> listDiary(@Param("userid")Long userid, @Param("page1") int page1,@Param("page2") int page2);

    /**
     * @param diary
     * @return
     */
    int updateDiary(Diary diary);

    /**
     * 查询endDate之前的日记
     * @param userid
     * @param startPage
     * @param pageNum
     * @param endDate 2019-05-05
     * @return
     */
    List<Diary> listDiaryByDate(@Param("userid") Long userid,@Param("startPage") int startPage,
                                @Param("pageNum") int pageNum, @Param("endDate") String endDate);

    /**
     * @param diary
     * @return
     */
    int deleteDiary(Diary diary);


    /**
     * 查询用户的心情
     * @param userid
     * @return
     */
    StatisticBean statisticDiary(Long userid);
}
