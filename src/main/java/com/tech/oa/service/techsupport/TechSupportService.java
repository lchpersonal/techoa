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
        ApplyTechSupportParam param = new ApplyTechSupportParam(applyUserId, content);
        techSupportMapper.add(param);
        return param.getId();
    }

    /** 评分 */
    public void mark(int id, int mark) {
        techSupportMapper.mark(id, mark);
    }

    /** 修改申请 */
    public void modify(int id, String content) {
        techSupportMapper.modify(id, content);
    }

    public void updateProcessId(int id, String processId) {
        techSupportMapper.updateProcessId(id, processId);
    }

    public void handleNewApplyTask(int id, String signUserId, int signResult) {
        techSupportMapper.handleNewApplyTask(id, signUserId, signResult);
    }

    public List<TechSupport> queryByProcessIds(List<String> processIds) {
        return techSupportMapper.queryByProcessIds(processIds);
    }

}
