package cn.ailanglang.diary.service;

import cn.ailanglang.diary.entity.UserDiary;

/**
 * @author smileyan
 */
public interface UserDiaryService {

    /**
     * @param userDiary
     * @return
     */
    boolean insertUserDiary(UserDiary userDiary);

    /**
     * @param userid
     * @return
     */
    int countDiary(Long userid);
}