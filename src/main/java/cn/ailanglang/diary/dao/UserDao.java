package cn.ailanglang.diary.dao;

import cn.ailanglang.diary.entity.User;

/**
 * @author smileyan
 */
public interface UserDao {
    /**
     * 根据id查询user
     * @param userid
     * @return TestUser
     */
    User getUserById(Long userid);


    /**
     * 添加用户
     * @param user
     * @return 用户的id
     */
    int insertUser(User user);
}
