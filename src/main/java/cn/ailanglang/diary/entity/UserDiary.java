package cn.ailanglang.diary.entity;

/**
 * @author smileyan
 */
public class UserDiary {
    private Long fkUserid;
    private Long fkDiaryid;

    public Long getFkUserid() {
        return fkUserid;
    }

    public void setFkUserid(Long fkUserid) {
        this.fkUserid = fkUserid;
    }

    public Long getFkDiaryid() {
        return fkDiaryid;
    }

    public void setFkDiaryid(Long fkDiaryid) {
        this.fkDiaryid = fkDiaryid;
    }
}
