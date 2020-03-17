package cn.ailanglang.diary.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

/**
 * @author smileyan
 */
public class User {
    private Long pkUserid;
    private String password;
    private String ukPhone;
    private Date gmtCreate;
    private Date gmtModified;
    private String role;

    public Long getPkUserid() {
        return pkUserid;
    }

    public void setPkUserid(Long pkUserid) {
        this.pkUserid = pkUserid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUkPhone() {
        return ukPhone;
    }

    public void setUkPhone(String ukPhone) {
        this.ukPhone = ukPhone;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
