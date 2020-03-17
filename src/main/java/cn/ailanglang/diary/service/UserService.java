package cn.ailanglang.diary.service;

import cn.ailanglang.diary.entity.User;

/**
 * @author smileyan
 */
public interface UserService {

    /**
     * @param user
     * @return 主键值
     */
    Long insertUser(User user);

    /**
     * @param userid
     * @return
     */
    User getUserById(Long userid);

}
