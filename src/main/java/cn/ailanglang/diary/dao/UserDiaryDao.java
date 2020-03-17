package cn.ailanglang.diary.dao;

import cn.ailanglang.diary.entity.UserDiary;

/**
 * @author smileyan
 */
public interface UserDiaryDao {

    /**
     * @param userDiary
     * @return
     */
    int insertUserDiary(UserDiary userDiary);

    /**
     * 查询用户日记总和
     * @param userid
     * @return
     */
    int countDiary(Long userid);
}
