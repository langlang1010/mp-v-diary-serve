package cn.ailanglang.diary.entity;

import java.util.Date;

/**
 * @author smileyan
 */
public class Diary {
    private Long pkDiaryid;
    private String content;
    private String weather;
    private String mood;
    private String state;
    private Date gmtCreate;
    private Date gmtModified;

    public Long getPkDiaryid() {
        return pkDiaryid;
    }

    public void setPkDiaryid(Long pkDiaryid) {
        this.pkDiaryid = pkDiaryid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
