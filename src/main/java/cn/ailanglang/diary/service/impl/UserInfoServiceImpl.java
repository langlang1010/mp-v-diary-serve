package cn.ailanglang.diary.service.impl;

import cn.ailanglang.diary.dao.UserInfoDao;
import cn.ailanglang.diary.entity.UserInfo;
import cn.ailanglang.diary.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author smileyan
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public boolean insertUserInfo(UserInfo userInfo) {
        if(userInfoDao.insertUserInfo(userInfo)>0) {
            return true;
        }
        return false;
    }

    @Override
    public Long getUseridByOpenid(String openid) {
        return userInfoDao.getUseridByOpenid(openid);
    }

    @Override
    public UserInfo getUserInfoByUserid(Long userid) {
        return userInfoDao.getUserInfoByUserid(userid);
    }
}
