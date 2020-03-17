package cn.ailanglang.diary.controller;

import cn.ailanglang.diary.entity.User;
import cn.ailanglang.diary.entity.UserInfo;
import cn.ailanglang.diary.service.UserInfoService;
import cn.ailanglang.diary.service.UserService;
import cn.ailanglang.diary.util.ControllerUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smileyan
 */
@Api(value = "用于登录")
@RequestMapping("/login")
@RestController
public class LoginController {

    private static final Log LOGGER = LogFactory.getLog(LoginController.class);

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String appsecret;

    private LoginHelper loginHelper;

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/test")
    private Map<String,Object> hello(){
        ControllerUtil controllerUtil = new ControllerUtil();
        controllerUtil.setData_message("测试");
        controllerUtil.setData_object(new Date());
        controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        return controllerUtil.getJsonData();
    }

    @PostMapping("/add")
    private Map<String,String> insertUser(String password,String phonenum) {
        Map<String,String > map = new HashMap<>(2);

        User user = new User();
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        user.setUkPhone(phonenum);
        user.setPassword(password);

        // 特别要注意这个地方
        Long id = userService.insertUser(user);
        map.put("insert的返回值",id+"");
        map.put("user.getPkuserid",user.getPkUserid()+"");
        return map;
    }

    @ApiOperation(value = "微信登录入口，可以获得openid与session_key")
    @ApiParam(value = "code由微信端生成，用于微信登录")
    @GetMapping("/wechat/index")
    private Map<String,Object> login(String code) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code";
        final int SUCCESS = 200;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == SUCCESS) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String session_key = jsonObject.get("session_key")+"";
        String openid = jsonObject.get("openid")+"";

        ControllerUtil controllerUtil = new ControllerUtil();
        Long userid_sql = userInfoService.getUseridByOpenid(openid);
        // 设置loginHelper
        loginHelper = new LoginHelper();
        loginHelper.setOpenid(openid);
        loginHelper.setSession_key(session_key);
        loginHelper.setUserid(userid_sql+"");

        // 1. 根据openid查询是否是注册了
        if( userid_sql == null) {
            LOGGER.info("Register");
            User user = new User();
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            // 写入数据库
            long userid = this.userService.insertUser(user);
            loginHelper.setUserid(userid+"");

            // 进行注册
            controllerUtil.setData_message("register");
            controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
            controllerUtil.setData_object(loginHelper);
            return controllerUtil.getJsonData();
        }

        // 已经注册过
        controllerUtil.setData_message(userid_sql+"");
        controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        controllerUtil.setData_object(loginHelper);
        return controllerUtil.getJsonData();
    }

    @ApiOperation(value = "注册，其中json是用户信息")
    @PostMapping("/wechat/register")
    public Map<String,Object> register(String json, String userid, String openid) {
        ControllerUtil controllerUtil = new ControllerUtil();
        LOGGER.info("json="+json);
        LOGGER.info("userid="+userid);
        LOGGER.info("openid="+openid);
        if (userid == null || userid.equals("") || userid.trim().length() == 0) {
            controllerUtil.setData_message("没有登录!");
            controllerUtil.setData_status(ControllerUtil.STATUS_IDENTITY_ERROR);
            return controllerUtil.getJsonData();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openid);
        long user_id = Integer.parseInt(userid);
        userInfo.setPkUserid(user_id);
        userInfo.setGmtModified(new Date());
        userInfo.setGmtCreate(new Date());
        userInfo.setNickname(jsonObject.get("nickName")+"");
        userInfo.setAvatarUrl(jsonObject.get("avatarUrl")+"");
        userInfo.setCity(jsonObject.get("city")+"");
        userInfo.setCountry(jsonObject.get("country")+"");
        userInfo.setProvince(jsonObject.get("province")+"");
        userInfo.setGender(jsonObject.get("gender")+"");

        this.userInfoService.insertUserInfo(userInfo);
        controllerUtil.setData_status(ControllerUtil.STATUS_SUCCESS);
        controllerUtil.setData_message("注册成功");
        LOGGER.info("Userinfo: "+userInfo);
        return controllerUtil.getJsonData();
    }

    class LoginHelper {
        private String userid;
        private String openid;
        private String session_key;

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }
    }
}
