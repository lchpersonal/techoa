package com.tech.oa.service.techsupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengli on 2016/11/17.
 */
@Service
public class TechSupportService {

    @Autowired
    private TechSupportMapper techSupportMapper;

    /** 新增技术支持申请 */
    public int add(String applyUserId, String content) {
        return 0;
    }

    /** 评分 */
    public void mark(int id, int mark) {

    }

    /** 修改申请 */
    public void modify(int id, String content) {

    }

    public void updateProcessId(int id, String processId) {

    }

    public void handleNewApplyTask(int id, String signUserId, int signResult) {

    }

    public List<TechSupport> queryByProcessIds(List<String> processIds) {
        return null;
    }

}
