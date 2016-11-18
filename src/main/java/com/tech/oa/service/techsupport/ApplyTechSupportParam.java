package com.tech.oa.service.techsupport;

/**
 * Created by chengli on 2016/11/17.
 */
public class ApplyTechSupportParam {
    private int id;
    private String applyUserId;
    private String content;

    public ApplyTechSupportParam(String applyUserId, String content) {
        this.applyUserId = applyUserId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplyUserId() {
        return applyUserId;
    }


    public String getContent() {
        return content;
    }
}
