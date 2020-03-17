package cn.ailanglang.diary.service.impl;

import cn.ailanglang.diary.dao.UserDiaryDao;
import cn.ailanglang.diary.entity.UserDiary;
import cn.ailanglang.diary.service.UserDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author smileyan
 */
@Service
public class UserDiaryServiceImpl implements UserDiaryService {
    @Autowired
    private UserDiaryDao userDiaryDao;

    @Override
    public boolean insertUserDiary(UserDiary userDiary) {
        return userDiaryDao.insertUserDiary(userDiary) > 0;
    }

    @Override
    public int countDiary(Long userid) {
        return userDiaryDao.countDiary(userid);
    }
}
