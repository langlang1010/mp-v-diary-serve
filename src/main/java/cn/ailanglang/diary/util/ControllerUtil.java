package cn.ailanglang.diary.util;


import java.util.HashMap;
import java.util.Map;

public class ControllerUtil {
    // 状态
    public static final String STATUS = "status";
    public static final String STATUS_404 = "404";
    public static final String STATUS_SUCCESS = "200";
    public static final String STATUS_SERVER_ERROR = "500";
    public static final String STATUS_IDENTITY_ERROR = "401";
    public static final String STATUS_REFUSE_ERROR = "403";

    // 说明
    public static final String MESSAGE = "message";

    // 数据
    public static final String DATA = "data";

    // 数据内容
    private Object data_object;
    private String data_status;
    private String data_message;

    public Object getData_object() {
        return data_object;
    }

    public void setData_object(Object data_object) {
        this.data_object = data_object;
    }

    public String getData_status() {
        return data_status;
    }

    public void setData_status(String data_status) {
        this.data_status = data_status;
    }

    public String getData_message() {
        return data_message;
    }

    public void setData_message(String data_message) {
        this.data_message = data_message;
    }

    public Map<String,Object> getJsonData() {
        Map<String,Object> map = new HashMap<>(3);
        map.put(STATUS,data_status);
        map.put(MESSAGE,data_message);
        map.put(DATA,data_object);
        return map;
    }
}
