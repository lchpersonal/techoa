package com.tech.oa.controller.techsupport;

import com.baitian.fourb.common.result.FbResult;
import com.baitian.fourb.common.result.ResultCodes;
import com.tech.oa.facade.TechSupportFacadeService;
import com.tech.oa.service.activiti.techsupport.ActivitiTechSupportService;
import com.tech.oa.service.techsupport.TechSupportService;
import com.tech.oa.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chengli on 2016/11/17.
 */
@Controller
@RequestMapping("/techsupport")
@Transactional(rollbackFor = Exception.class)
public class TechSupportController {
    @Autowired
    private TechSupportService techSupportService;
    @Autowired
    private ActivitiTechSupportService activitiTechSupportService;
    @Autowired
    private TechSupportFacadeService techSupportFacadeService;

    private final Logger logger = LoggerFactory.getLogger(TechSupportController.class);

    @RequestMapping("/apply")
    public FbResult applyTechsupport(HttpServletRequest request, String content) {
        if (StringUtils.isEmpty(content)) {
            return FbResult.ERROR(ResultCodes.PARAM_EMPTY);
        }
        String userId = SessionUtil.getUserId(request);
        try {
            int id = techSupportService.add(userId, content);
            String processId = activitiTechSupportService.startNewProcess(id, userId);
            techSupportService.updateProcessId(id, processId);
            return FbResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
            return FbResult.ERROR(ResultCodes.EXCEPTION);
        }
    }

    @RequestMapping("/modifyApply")
    public FbResult modifyApply(HttpServletRequest request, String content, int techsupportId, String taskId) {
        if (StringUtils.isEmpty(content)) {
            return FbResult.ERROR(ResultCodes.PARAM_EMPTY);
        }
        String userId = SessionUtil.getUserId(request);
        try {
            techSupportService.modify(techsupportId, content);
            activitiTechSupportService.completeTheTask(taskId, userId);
            return FbResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
            return FbResult.ERROR(ResultCodes.EXCEPTION);
        }
    }

    @RequestMapping("/queryNewApply")
    public FbResult queryNewApply(HttpServletRequest request) {
        String userId = SessionUtil.getUserId(request);
        return FbResult.SUCCESS(techSupportFacadeService.queryTTechSupportTask(userId));
    }

    @RequestMapping("/handleNewApplyTask")
    public FbResult handleNewApplyTask(String taskId, int techsupportId, int handleStatus, HttpServletRequest request) {
        String userId = SessionUtil.getUserId(request);
        activitiTechSupportService.handleNewApplyTask(taskId, userId, handleStatus);
        techSupportService.handleNewApplyTask(techsupportId, userId, handleStatus);
        return FbResult.SUCCESS();
    }

    @RequestMapping("/commit")
    public FbResult commit(String taskId, HttpServletRequest request) {
        String userId = SessionUtil.getUserId(request);
        activitiTechSupportService.completeTheTask(taskId, userId);
        return FbResult.SUCCESS();
    }

    @RequestMapping("/mark")
    public FbResult mark(String taskId, int techsupportId, int mark, HttpServletRequest request) {
        String userId = SessionUtil.getUserId(request);
        activitiTechSupportService.completeTheTask(taskId, userId);
        techSupportService.mark(techsupportId, mark);
        return FbResult.SUCCESS();
    }
}
