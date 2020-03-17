package cn.ailanglang.diary.service.impl;

import cn.ailanglang.diary.dao.UserDao;
import cn.ailanglang.diary.entity.User;
import cn.ailanglang.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author smileyan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Long insertUser(User user) {
        userDao.insertUser(user);
        return user.getPkUserid();
    }

    @Override
    public User getUserById(Long userid) {
        return userDao.getUserById(userid);
    }


}
