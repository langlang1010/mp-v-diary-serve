package cn.ailanglang.diary.service;

import cn.ailanglang.diary.entity.UserInfo;

/**
 * @author smileyan
 */
public interface UserInfoService {
    /**
     * @param userInfo
     * @return
     */
    boolean insertUserInfo(UserInfo userInfo);

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
