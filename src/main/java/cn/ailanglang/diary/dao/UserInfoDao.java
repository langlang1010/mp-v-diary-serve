package cn.ailanglang.diary.dao;

import cn.ailanglang.diary.entity.UserInfo;

/**
 * @author smileyan
 */
public interface UserInfoDao {

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * @param openid
     * @return
     */
    Long getUseridByOpenid(String openid);

    /**
     * @param userid
     * @return
     */
    UserInfo getUserInfoByUserid(Long userid);
}
